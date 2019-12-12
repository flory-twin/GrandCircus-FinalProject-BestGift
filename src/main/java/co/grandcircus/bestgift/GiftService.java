package co.grandcircus.bestgift;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.jparepos.GiftListRepository;
import co.grandcircus.bestgift.jparepos.GiftRepository;
import co.grandcircus.bestgift.jparepos.KeywordRepository;
import co.grandcircus.bestgift.jparepos.SearchExpressionRepository;
import co.grandcircus.bestgift.jparepos.SearchHistoryRepository;
import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;
import co.grandcircus.bestgift.search.Keyword;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.tables.GiftList;
import co.grandcircus.bestgift.tables.SearchHistory;

@Component
public class GiftService {
	@Value("${etsy.key}")
	private String etsyKey;


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

	private String listingUrl = "https://openapi.etsy.com/v2/listings/active?api_key=";
	RestTemplate rt = new RestTemplate();

	/**
	 * Builds URL to get a list of Gifts from Etsy.
	 * @return
	 */
	public String getGiftsUrl() {
		return "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
	}
	
	/**
	 * Builds the Gift-specific image URL used in display.
	 * @return
	 */
	public String getGiftImageUrl(String listing_id) {
		return "https://openapi.etsy.com/v2/listings/" + listing_id + "/images?api_key=" + etsyKey;
	}	
	/**
	 * Using the passed SearchExpression, build the set of Etsy URL query parameters that we can use to narrow what we're searching on.
	 * @param se
	 * @return
	 */
	private String getEtsySearchParameters (SearchExpression se) {
		String returnVal = "";
		
		// How to convert a SearchExpression to a URL:
		// We know the following will always be true about a SearchExpression:
		//  1. The first keyword will ALWAYS be set.
		//  2. If an Operator is set, then either (2a) the second keyword is set, or (2b) the interior search expression is set.
		if (se.getOperator() == null) {
			// Case 1
			//  To get this data from Etsy, just use the 1st keyword.
			returnVal = ("&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\"");
		} else {
			// Case 2
			//   Case 2a. If the second keyword is set, the URL is easy.
			if (se.getKeyword2() != null) {
				returnVal = ("&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\" \"" + se.getKeyword2().getUrlEncodedValue() + "\"");
			} else {
				// Case 2b
				//  We'll need to go through the BaseExpression in order to get -its- query parameters...
				returnVal = getEtsySearchParameters(se.getBaseExpression());
				// Now add our keyword to the very front.
				returnVal = returnVal.replace("&keywords=", "&keywords=\"" + se.getKeyword1().getUrlEncodedValue() + "\" ");
			}
		}
		
		return returnVal;
	}
		
	public Image getGiftImage(String listing_id) {
		//TODO add images to DB
		 return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
//		return new Image();
	}
	
	public GiftResult getListOfGifts() {
		GiftResult giftsToReturn = rt.getForObject(getGiftsUrl(), GiftResult.class);
		// Save gifts to DB.
		GiftList returnedList = saveGiftListToDatabase(giftsToReturn.getResults());
		
		// Add history record.
		//TODO 
		SearchExpression se = new SearchExpression(new Keyword(""));
		saveSearchExpressionToDatabase(se);
		shr.save(
				new SearchHistory(
						se, returnedList
						)
				);
		return giftsToReturn;
	}

	public GiftResult getListOfSearchedGifts(SearchExpression se) {
		String url = listingUrl + etsyKey + getEtsySearchParameters(se);
		GiftResult giftsToReturn = rt.getForObject(url, GiftResult.class);
		// TODO Save search expression here to create history log.
		// Save gifts to DB.
		GiftList returnedList = saveGiftListToDatabase(giftsToReturn.getResults());
		saveSearchExpressionToDatabase(se);
		shr.save(
				new SearchHistory(
						se, returnedList
						)
				);
		return giftsToReturn;
	}
	
	public List<GiftList> getCompleteSearchHistory() {
		return gl.findAll();
	}
	
	public void recacheResult(GiftResult toCache, HttpSession session) {
		session.setAttribute("result", toCache);
		session.setAttribute("currentGiftList", toCache.getResults());
	}
	
	public void recacheRepositories(HttpSession session) {
		session.setAttribute("gs", this);
		session.setAttribute("gr", gr);
		session.setAttribute("gl", gl);
		session.setAttribute("kr", kr);
		session.setAttribute("ser", ser);
		session.setAttribute("shr", shr);
	}

	/*
	 * Interact with increasingly complex save scenarios. The higher-level save scenarios save all their components themselves, so try not to call the primitives!!
	 */
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	private Keyword saveKeywordToDatabase(Keyword k) {;
		kr.save(k);
		return k;
	}
	
	private void saveSearchExpressionToDatabase(SearchExpression se) {
		// How to convert a SearchExpression to a URL:
		// We know the following will always be true about a SearchExpression:
		//  1. The first keyword will ALWAYS be set.
		//  2. If an Operator is set, then either (2a) the second keyword is set, or (2b) the interior search expression is set.
		
		saveKeywordToDatabase(se.getKeyword1());
		if (se.getOperator() == null) {
			// Operator not set,. so no second operand
			
		} else {
			// Case 2
			//   Case 2a. Second keyword is set
			if (se.getKeyword2() != null) {
				saveKeywordToDatabase(se.getKeyword2());
			} else {
				// Case 2b
				//  We'll need to go through the BaseExpression in order to get -its- query parameters...
				saveSearchExpressionToDatabase(se.getBaseSE());
				// Now add our keyword to the very front.
			}
		}
		
		ser.save(se);
	}
	
	public SearchHistory saveSearchHistoryRecordToDatabase(SearchHistory sh, HttpSession session) {
		ser.save(sh.getQuery());
		gl.save(sh.getSearchResult());
		shr.save(sh);
		session.setAttribute("lastSearchHistory", sh);
		return sh;
	}
	
	private void saveGiftsToDatabase(List<Gift> giftsToAdd) {
		for (Gift g : giftsToAdd) {
			gr.save(g); 
		}
	}
	
	public GiftList saveGiftListToDatabase(List<Gift> giftsToAdd) {
		saveGiftsToDatabase(giftsToAdd);
		
		GiftList list = new GiftList(giftsToAdd);
		gl.save(list);
		return list;
	}
}
