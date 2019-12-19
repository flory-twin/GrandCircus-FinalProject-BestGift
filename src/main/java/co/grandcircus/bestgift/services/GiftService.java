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
import co.grandcircus.bestgift.jparepos.SearchTermRepository;
import co.grandcircus.bestgift.jparepos.SearchExpressionRepository;
import co.grandcircus.bestgift.jparepos.SearchHistoryRepository;
import co.grandcircus.bestgift.jparepos.TagRepository;
import co.grandcircus.bestgift.jparepos.UserRepo;
import co.grandcircus.bestgift.models.User;
import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.GiftResult;
import co.grandcircus.bestgift.models.etsy.Image;
import co.grandcircus.bestgift.models.etsy.info.Tag;
import co.grandcircus.bestgift.search.SearchTerm;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.tables.GiftList;
import co.grandcircus.bestgift.tables.SearchHistory;
/**
 * Handle all operations directly related to a Gift or search.
 * 
 * Stores session attributes as follows:
 *   Repositories:  
 *     UserRepo ur
 *     TagRepository tr
 *     KeywordRepository kr
 *     GiftRepository gr
 *     GiftListRepository gl
 *     SearchExpressionRepository ser
 *     SearchHistoryRepository shr
 *   Services:
 *     GiftService gs: This service
 *     SynonymService dms: The Dandelion synonym webservice
 *   Convenience Objects:
 *     SearchHistory lastSearchHistory: The last search run by the logged-in user.
 *     List<Gift> currentGiftList: The results of the last search run by the logged-in user.
 * @author Kevin Flory, Bryan Byrd, Kevin Chung
 *
 */
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
	SearchTermRepository kr;
	@Autowired
	SearchHistoryRepository shr;
	@Autowired
	UserRepo ur;
	@Autowired
	ImageRepository ir;
	@Autowired
	TagRepository tr;
	@Autowired
	SynonymService dms;
	
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
		return listingUrl + etsyKey + "&limit=" + itemLimit;
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
			returnVal = ("&keywords=\"" + se.getSearchTerm1().getUrlEncodedValue() + "\"");
		} else {
			// Case 2
			// Case 2a. If the second keyword is set, the URL is easy.
			if (se.getSearchTerm2() != null) {
				returnVal = ("&keywords=\"" + se.getSearchTerm1().getUrlEncodedValue() + "\" \""
						+ se.getSearchTerm2().getUrlEncodedValue() + "\"");
			} else {
				// Case 2b
				// We'll need to go through the BaseExpression in order to get -its- query
				// parameters...
				returnVal = getEtsySearchParameters(se.getBaseExpression());
				// Now add our keyword to the very front.
				returnVal = returnVal.replace("&keywords=",
						"&keywords=\"" + se.getSearchTerm1().getUrlEncodedValue() + "\" ");
			}
		}

		return returnVal;
	}

	public Image getGiftImage(Integer listing_id) {
		return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
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
		// Save gifts to DB.
		GiftList returnedList = saveGiftListToDatabase(giftsToReturn.getResults());
		saveSearchExpressionToDatabase(se);
		SearchHistory search = new SearchHistory(se, returnedList);
		User loginUser = (User) session.getAttribute("user");
		search.setUser(loginUser);
		shr.save(search);
		recacheResult(search);
		return giftsToReturn;
	}
	
	/**
	 * Stores last list of gifts from etsy in the session
	 * 
	 * @param latest
	 * @param session
	 */
	public void recacheResult(SearchHistory history) {
		session.setAttribute("lastSearchHistory", history);
		session.setAttribute("currentGiftList", history.getSearchResult().getGifts());
	}
	
	/**
	 * Stores all repositories in the session
	 * this makes them accessible from all .jsp
	 * 
	 * @param session
	 */
	public void recacheRepositories() {
		session.setAttribute("gs", this);
		session.setAttribute("gr", gr);
		session.setAttribute("gl", gl);
		session.setAttribute("kr", kr);
		session.setAttribute("ser", ser);
		session.setAttribute("shr", shr);
		session.setAttribute("ur", ur);
		session.setAttribute("dms", dms);
	}
	
	/**
	 * For some reason there are duplicate Gifts by listing ID in the DB. 
	 * That shouldn't be possible, but regardless, to get around it,
	 * take only the first Gift found.
	 * 
	 * @param listingId
	 * @return
	 */
	public Gift getExistingGiftFromDb(Integer listingId) {
		return gr.findByListingId(listingId).get(0);
	}
	
	/*
	 * ------------------------------------------------------------------------------------
	 * The following methods interact with increasingly complex save scenarios. The higher-level save
	 * scenarios (saveSearchHistoryRecordToDatabase and saveGiftsToDatabase) save all their components 
	 * themselves, so try to call only those two methods!!!
	 * ------------------------------------------------------------------------------------
	 */
	
	/**
	 * Saves a Keyword to the database.
	 * @param k
	 * @return
	 */
	private SearchTerm saveKeywordToDatabase(SearchTerm k) {
		kr.save(k);
		return k;
	}
	
	/**
	 * Save a SearchExpression and its component Keywords to the database.
	 * If calling this function, do not call saveKeywordToDatabase!
	 * @param se
	 */
	private void saveSearchExpressionToDatabase(SearchExpression se) {
		// How to convert a SearchExpression to a URL:
		// We know the following will always be true about a SearchExpression:
		// 1. The first keyword will ALWAYS be set.
		// 2. If an Operator is set, then either (2a) the second keyword is set, or (2b)
		// the interior search expression is set.

		saveKeywordToDatabase(se.getSearchTerm1());
		if (se.getOperator() == null) {
			// Operator not set,. so no second operand

		} else {
			// Case 2
			// Case 2a. Second keyword is set
			if (se.getSearchTerm2() != null) {
				saveKeywordToDatabase(se.getSearchTerm2());
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
	 * Saves a SearchHistory and its component SearchExpression and GiftList of results
	 * to the database.
	 * 
	 * If calling this method do not call saveSearchExpressionToDatabase!
	 * 
	 * @param sh
	 * @param session
	 * @return
	 */
	public SearchHistory saveSearchHistoryRecordToDatabase(SearchHistory sh) {
		User loginUser = (User) session.getAttribute("user");
		sh.setUser(loginUser);
		ser.save(sh.getQuery());
		gl.save(sh.getSearchResult());
		shr.save(sh);
		session.setAttribute("lastSearchHistory", sh);
		return sh;
	}

	/**
	 * Saves a List<Gift> and the Gift's component Tags to the database.
	 * @param giftsToAdd
	 */
	private void saveGiftsToDatabase(List<Gift> giftsToAdd) {
		for (Gift g : giftsToAdd) {
			for (Tag t : g.getTags()) {
				tr.save(t);
			}
			gr.save(g);
		}
	}
	
	/**
	 * Saves a GiftList and its component Gifts to the database.
	 * If calling this method do not call saveGiftsToDatabase!
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
