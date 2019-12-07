package co.grandcircus.bestgift.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.GiftService;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;
	
	@Autowired
	GiftService gs;
	
	@RequestMapping("/gift-results")
	public ModelAndView viewGifts(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("giftresults");
		
		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
		
		GiftResult result = gs.getListOfGifts();
		session.setAttribute("result", result);
		
		session.setAttribute("gs", gs);
//		listId = result.getResults().get(0).getListing_id();
//		
//		imageUrl = "https://openapi.etsy.com/v2/listings/" + listId + "/images?api_key=" + etsyKey;
//		
//		imgResult = rt.getForObject(imageUrl, Image.class);
//		
//		mv.addObject("p", imgResult);		
//		mv.addObject("giftresult" , imgResult);
//		mv.addObject("giftresult" , result.getResults().get(0));
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping("/image")
	public ModelAndView giftImages(String listing_id) {
		ModelAndView mv = new ModelAndView("TestOutPut");
		
		Image result = gs.getGiftImage(listing_id);
		
		mv.addObject("i", result);
		return mv;
		
	}
	

	
	@RequestMapping("/image/newSearch")
	public ModelAndView giftImagesNoUrl() {
		return new ModelAndView("TestOutPut");
	}
	
	
}
