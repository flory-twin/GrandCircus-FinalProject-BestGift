package co.grandcircus.bestgift.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;
	
	RestTemplate rt = new RestTemplate();
	
	@RequestMapping("/gift-results")
	public ModelAndView viewGifts() {
		ModelAndView mv = new ModelAndView("giftresults");
		
		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
		
//		String result = rt.getForObject(url, String.class);
		
		GiftResult result = rt.getForObject(url, GiftResult.class);
		
		System.out.println(result);
		
		mv.addObject("giftresult", result.getResults().get(0));
		return mv;
	}
	
	@RequestMapping("/image")
	public ModelAndView giftImages(String listing_id) {
		ModelAndView mv = new ModelAndView("TestOutPut");
		
		String url = "https://openapi.etsy.com/v2/listings/" + listing_id + "/images?api_key=" + etsyKey;
		
//		String result = rt.getForObject(url, String.class);
		Image result = rt.getForObject(url, Image.class);
		
		
		mv.addObject("i", result);
		return mv;
		
	}
	
	@RequestMapping("/image/newSearch")
	public ModelAndView giftImagesNoUrl() {
		return new ModelAndView("TestOutPut");
	}
	
	
}
