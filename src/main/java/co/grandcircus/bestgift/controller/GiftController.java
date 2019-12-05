package co.grandcircus.bestgift.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.models.GiftResult;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;
	
	RestTemplate rt = new RestTemplate();
	
	@RequestMapping("/")
	public ModelAndView viewGifts() {
		ModelAndView mv = new ModelAndView("index");
		
		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
		
//		String result = rt.getForObject(url, String.class);
		GiftResult result = rt.getForObject(url, GiftResult.class);
		
		System.out.println(result);
		
		mv.addObject("giftresult", result.getResults().get(0));
		return mv;
	}
}
