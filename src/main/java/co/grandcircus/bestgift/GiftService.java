package co.grandcircus.bestgift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;
import co.grandcircus.bestgift.jparepos.GiftRepository;

@Component
public class GiftService {
	@Value("${etsy.key}")
	private String etsyKey;
	
	@Autowired
	GiftRepository gr;
	
	private String listingUrl = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
	
	RestTemplate rt = new RestTemplate();
	
	public GiftResult getListOfGifts() {
		GiftResult giftsToReturn = rt.getForObject(getGiftsUrl(), GiftResult.class);
		for (Gift g : giftsToReturn.getResults()) {
			gr.save(g); 
		}
		return giftsToReturn;
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
	
	public GiftResult getListOfSearchedGifts(String keywords, float max_price) {
		GiftResult giftsToReturn = rt.getForObject(getSearchedGiftsUrl(keywords, max_price), GiftResult.class);
		for (Gift g : giftsToReturn.getResults()) {
			gr.save(g); 
		}
		return giftsToReturn;
	}
	
	public String getSearchedGiftsUrl(String keywords, float max_price) {
		return listingUrl + "&keywords=" + keywords + "&max_price=" + max_price;
	}
}
