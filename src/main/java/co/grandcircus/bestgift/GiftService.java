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
		return rt.getForObject(getGiftImageUrl(listing_id), Image.class);
	}
}
