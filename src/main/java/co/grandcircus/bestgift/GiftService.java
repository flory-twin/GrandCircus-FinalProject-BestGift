package co.grandcircus.bestgift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.jparepos.GiftListRepository;
import co.grandcircus.bestgift.jparepos.GiftRepository;
import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.tables.GiftList;

@Component
public class GiftService {
	@Value("${etsy.key}")
	private String etsyKey;

	@Autowired
	GiftRepository gr;
	@Autowired
	GiftListRepository gl;

	private String listingUrl = "https://openapi.etsy.com/v2/listings/active?api_key=";
	RestTemplate rt = new RestTemplate();

	public GiftResult getListOfGifts() {
		GiftResult giftsToReturn = rt.getForObject(getGiftsUrl(), GiftResult.class);
		saveGiftsToDatabase(giftsToReturn.getResults());
		saveGiftListToDatabase(giftsToReturn.getResults());
		return giftsToReturn;
	}

	public String getGiftsUrl() {
		return "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
	}

	public String getGiftImageUrl(String listing_id) {
		return "https://openapi.etsy.com/v2/listings/" + listing_id + "/images?api_key=" + etsyKey;
	}

	public Image getGiftImage(String listing_id) {
		// return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
		return new Image();
	}

	public GiftResult getListOfSearchedGifts(String keywords, Double max_price) {
		GiftResult giftsToReturn = rt.getForObject(getSearchedGiftsUrl(keywords, max_price), GiftResult.class);
		saveGiftsToDatabase(giftsToReturn.getResults());
		saveGiftListToDatabase(giftsToReturn.getResults());
		return giftsToReturn;
	}
	
	public String getSearchedGiftsUrl(String keywords, Double max_price) {
		keywords = keywords.replace(' ', '+');
		return listingUrl + etsyKey + "&keywords=\"" + keywords + "\"&max_price=" + max_price;
	} 
	public GiftResult getListOfSearchedGifts(SearchExpression se) {
		String url = listingUrl + etsyKey + getEtsySearchParameters(se);
		GiftResult giftsToReturn = rt.getForObject(url, GiftResult.class);
		// TODO Save search expression here to create history log.
		saveGiftsToDatabase(giftsToReturn.getResults());
		saveGiftListToDatabase(giftsToReturn.getResults());
		return giftsToReturn;
	}
	
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
	
	public String getSearchedGiftsUrl(String keywords, String keywords2) {
		keywords = keywords.replace(' ', '+');
		keywords2 = keywords2.replace(' ', '+');
		return listingUrl + etsyKey + "&keywords=\"" + keywords + "\" \"" + keywords2 +"\"";
	}
	
	public String getSearchedGiftsUrl(String keywords, float max_price) {
		return listingUrl + etsyKey + "&keywords=" + keywords + "&max_price=" + max_price;
	}
	
	public void saveGiftsToDatabase(List<Gift> giftsToSave) {
		for (Gift g : giftsToSave) {
			gr.save(g); 
		}
	}
	
	public void saveGiftListToDatabase(List<Gift> giftsToAdd) {
		gl.save(new GiftList(giftsToAdd));
	}
	
	public List<GiftList> getCompleteSearchHistory() {
		return gl.findAll();
	}
}
