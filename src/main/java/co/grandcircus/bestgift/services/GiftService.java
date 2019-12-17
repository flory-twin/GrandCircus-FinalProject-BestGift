package co.grandcircus.bestgift.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.jparepos.GiftListRepository;
import co.grandcircus.bestgift.jparepos.GiftRepository;
import co.grandcircus.bestgift.jparepos.ImageRepository;
import co.grandcircus.bestgift.jparepos.KeywordRepository;
import co.grandcircus.bestgift.jparepos.SearchExpressionRepository;
import co.grandcircus.bestgift.jparepos.SearchHistoryRepository;
import co.grandcircus.bestgift.jparepos.TagRepository;
import co.grandcircus.bestgift.jparepos.UserRepo;
import co.grandcircus.bestgift.models.User;
import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.GiftResult;
import co.grandcircus.bestgift.models.etsy.Image;
import co.grandcircus.bestgift.models.etsy.info.Tag;
import co.grandcircus.bestgift.search.Keyword;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.tables.GiftList;
import co.grandcircus.bestgift.tables.SearchHistory;

@Component
public class GiftService {
	@Value("${etsy.key}")
	private String etsyKey;
	
	// Etsy has a limited amount of searches per minute
	// we included this item limit as we are pulling two different end points (item listing and for image)
	@Value("${etsy.itemlimit}")
	private int itemLimit;

	// Autowired all of the repositories in the gift service primarily handles all of the data
	// gift controller only handles the dispatching requests
	@Autowired
	GiftRepository gr;
	@Autowired
	GiftListRepository gl;
	@Autowired
	SearchExpressionRepository ser;
	@Autowired
	KeywordRepository kr;
	@Autowired
	SearchHistoryRepository shr;
	@Autowired
	UserRepo ur;
	@Autowired
	ImageRepository ir;
	@Autowired
	TagRepository tr;
	@Autowired
	HttpSession session;
	
	
	private String listingUrl = "https://openapi.etsy.com/v2/listings/active?api_key=";
	RestTemplate rt = new RestTemplate();

	/**
	 * Builds URL to get a list of Gifts from Etsy.
	 * 
	 * @return
	 */
	public String getGiftsUrl() {
		return "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey + "&limit=" + itemLimit;
	}

	/**
	 * Builds the Gift-specific image URL used in display.
	 * 
	 * @return
	 */
	public String getGiftImageUrl(Integer listing_id) {
		return "https://openapi.etsy.com/v2/listings/" + listing_id + "/images?api_key=" + etsyKey;
	}

	/**
	 * Using the passed SearchExpression, build the set of Etsy URL query parameters
	 * that we can use to narrow what we're searching on.
	 * 
	 * @param se
	 * @return
	 */
	private String getEtsySearchParameters(SearchExpression se) {
		String returnVal = "";

		// How to convert a SearchExpression to a URL:
		// We know the following will always be true about a SearchExpression:
		// 1. The first keyword will ALWAYS be set.
		// 2. If an Operator is set, then either (2a) the second keyword is set, or (2b)
		// the interior search expression is set.
		if (se.getOperator() == null) {
			// Case 1
			// To get this data from Etsy, just use the 1st keyword.
			returnVal = ("&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\"");
		} else {
			// Case 2
			// Case 2a. If the second keyword is set, the URL is easy.
			if (se.getKeyword2() != null) {
				returnVal = ("&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\" \""
						+ se.getKeyword2().getUrlEncodedValue() + "\"");
			} else {
				// Case 2b
				// We'll need to go through the BaseExpression in order to get -its- query
				// parameters...
				returnVal = getEtsySearchParameters(se.getBaseExpression());
				// Now add our keyword to the very front.
				returnVal = returnVal.replace("&keywords=",
						"&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\" ");
			}
		}

		return returnVal;
	}

	public Image getGiftImage(Integer listing_id) {
		return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
	}

	// TODO combine this with the getListofSearchGifts
	public GiftResult getListOfGifts() {
		GiftResult giftsToReturn = rt.getForObject(getGiftsUrl(), GiftResult.class);
		// Save gifts to DB.
		GiftList returnedList = saveGiftListToDatabase(giftsToReturn.getResults());

		// This adds the search expression to the database
		SearchExpression se = new SearchExpression(new Keyword(""));
		saveSearchExpressionToDatabase(se);
		shr.save(new SearchHistory(se, returnedList));
		return giftsToReturn;
	}

	/**
	 *  Uses the keywords in the passed search expression to get a list of gifts from etsy. 
	 *  Only listings marked as active by Etsy will return.
	 * @param se
	 * @return
	 */
	public GiftResult getListOfSearchedGifts(SearchExpression se) {
		String url = listingUrl + etsyKey + getEtsySearchParameters(se) + "&limit=" + itemLimit;
		GiftResult giftsToReturn = rt.getForObject(url, GiftResult.class);
		// TODO Save search expression here to create history log.
		// Save gifts to DB.
		GiftList returnedList = saveGiftListToDatabase(giftsToReturn.getResults());
		saveSearchExpressionToDatabase(se);
		SearchHistory search = new SearchHistory(se, returnedList);
		User loginUser = (User) session.getAttribute("user");
		search.setUser(loginUser);
		shr.save(search);
//		shr.save(new SearchHistory(se, returnedList));
		return giftsToReturn;
	}
	/**
	 * Stores last list of gifts from etsy in the session
	 * 
	 * @param latest
	 * @param session
	 */
	public void recacheResult(GiftResult latest, HttpSession session) {
		session.setAttribute("result", latest);
		session.setAttribute("currentGiftList", latest.getResults());
	}
/**
 * Stores all repositories in the session
 * this makes them accessible from all .jsp
 * 
 * @param session
 */
	public void recacheRepositories(HttpSession session) {
		session.setAttribute("gs", this);
		session.setAttribute("gr", gr);
		session.setAttribute("gl", gl);
		session.setAttribute("kr", kr);
		session.setAttribute("ser", ser);
		session.setAttribute("shr", shr);
		session.setAttribute("ur", ur);
//		User loginUser = (User) session.getAttribute("user");
//		session.setAttribute("user", loginUser);
	}

	/*
	 * Interact with increasingly complex save scenarios. The higher-level save
	 * scenarios save all their components themselves, so try not to call the
	 * primitives!!
	 */
	private Keyword saveKeywordToDatabase(Keyword k) {
		kr.save(k);
		return k;
	}
	/**
	 * if calling this function, do not call saveKeywordToDatabase
	 * @param se
	 */
	private void saveSearchExpressionToDatabase(SearchExpression se) {
		// How to convert a SearchExpression to a URL:
		// We know the following will always be true about a SearchExpression:
		// 1. The first keyword will ALWAYS be set.
		// 2. If an Operator is set, then either (2a) the second keyword is set, or (2b)
		// the interior search expression is set.

		saveKeywordToDatabase(se.getKeyword1());
		if (se.getOperator() == null) {
			// Operator not set,. so no second operand

		} else {
			// Case 2
			// Case 2a. Second keyword is set
			if (se.getKeyword2() != null) {
				saveKeywordToDatabase(se.getKeyword2());
			} else {
				// Case 2b
				// We'll need to go through the BaseExpression in order to get -its- query
				// parameters...
				saveSearchExpressionToDatabase(se.getBaseSE());
				// Now add our keyword to the very front.
			}
		}

		ser.save(se);
	}
	
	/**
	 * if calling this method do not call saveSearchExpressionToDatabase
	 * 
	 * @param sh
	 * @param session
	 * @return
	 */

	public SearchHistory saveSearchHistoryRecordToDatabase(SearchHistory sh, HttpSession session) {
		User loginUser = (User) session.getAttribute("user");
		sh.setUser(loginUser);
		ser.save(sh.getQuery());
		gl.save(sh.getSearchResult());
		shr.save(sh);
		session.setAttribute("lastSearchHistory", sh);
		return sh;
	}

	private void saveGiftsToDatabase(List<Gift> giftsToAdd) {
		for (Gift g : giftsToAdd) {
			for (Tag t : g.getTags()) {
				tr.save(t);
			}
			gr.save(g);
		}
	}
	
	// TODO: For some reason there are duplicate Gifts by listing ID in the DB. That shouldn't be possible, but regardless, all of those Gifts are logically the same Gift...
	public Gift getExistingGiftFromDb(Integer listingId) {
		return gr.findByListingId(listingId).get(0);
	}
	
	
	/**
	 * If calling this method do not call saveGiftsToDatabase
	 * @param giftsToAdd
	 * @return
	 */
	public GiftList saveGiftListToDatabase(List<Gift> giftsToAdd) {
		User loginUser = (User) session.getAttribute("user");
		
		saveGiftsToDatabase(giftsToAdd);
		
		GiftList list = new GiftList(giftsToAdd);
		list.setUser(loginUser);
		gl.save(list);
		
		return list;
	}
}
