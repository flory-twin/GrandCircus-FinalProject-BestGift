package co.grandcircus.bestgift;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;

@Component
public class GiftService {
	@Value("${etsy.key}")
	private String etsyKey;
	
	private String listingUrl = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
	
	RestTemplate rt = new RestTemplate();
	
	public GiftResult getListOfGifts() {
		return rt.getForObject(getGiftsUrl(), GiftResult.class);
	}
	
	public String getGiftsUrl() {
		return "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
	}
	
	public String getGiftImageUrl(String listing_id) {
		return "https://openapi.etsy.com/v2/listings/" + listing_id + "/images?api_key=" + etsyKey;
	}
	
	public Image getGiftImage(String listing_id) {
		//return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
		return new Image();
	}
<<<<<<< HEAD
	
	public GiftResult getListOfSearchedGifts(String keywords, float max_price) {
		return rt.getForObject(getSearchedGiftsUrl(keywords, max_price), GiftResult.class);
	}
	
	public String getSearchedGiftsUrl(String keywords, float max_price) {
		return listingUrl + "&keywords=" + keywords + "&max_price=" + max_price;
	}
=======
>>>>>>> c177afd54f8e1b9d64430f9ae1c149688a8d08dc
}
