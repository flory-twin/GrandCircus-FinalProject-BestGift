package co.grandcircus.bestgift.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.GiftService;
import co.grandcircus.bestgift.jparepos.GiftListRepository;
import co.grandcircus.bestgift.jparepos.KeywordRepository;
import co.grandcircus.bestgift.jparepos.SearchExpressionRepository;
import co.grandcircus.bestgift.jparepos.SearchHistoryRepository;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;
import co.grandcircus.bestgift.search.Keyword;
import co.grandcircus.bestgift.search.filter.KeywordSearcher;
import co.grandcircus.bestgift.search.Operator;
import co.grandcircus.bestgift.search.SearchExpression;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;

	@Autowired
	GiftService gs;

	@RequestMapping("/")
	public ModelAndView routeFromIndex(HttpSession session) {
		gs.recacheRepositories(session);
		return viewGifts(session);
	}


	/**
	 * Routes traffic to the entry page, giftresults.jsp.
	 * 
	 * 
	 * @param session
	 * @return
	 */

	@RequestMapping("/gift-results")
	public ModelAndView viewGifts(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("startsearch");

		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
		
		gs.recacheRepositories(session);
		GiftResult result = gs.getListOfGifts();

		gs.recacheResult(result, session);


		return mv;

	}

	@RequestMapping("/etsy-results")
	public ModelAndView searchGifts(
			HttpSession session, 
			@RequestParam String keywords, 
			@RequestParam(required = false) String keywords2,
			@RequestParam(required = false) String keywords3,
			@RequestParam(required = false) String keywords4,
			@RequestParam(required = false) Double max_price) {
		// Just in case user navigated straight to this page...
		gs.recacheRepositories(session);
		
		//request.getParameter("product"+i+"SkusCnt"))
		
		ModelAndView mv = new ModelAndView("testthree");
		// Put search operators into repo
		Keyword k = new Keyword(keywords);
		// TODO for later: move all DB stuff into Service, or move it here, but not half and half
		SearchExpression searchExp = new SearchExpression(k);
		
		if (keywords2 != null && keywords2 != "") {
			SearchExpression inner2 = new SearchExpression(new Keyword(keywords2));
			if (keywords3 != null && keywords3 != "") {
				SearchExpression inner3 = new SearchExpression(new Keyword(keywords3));
				if (keywords4 != null && keywords4 != "") {
					inner3.setO(Operator.AND);
					inner3.setK2(new Keyword(keywords4));
				}
				inner2.setO(Operator.AND);
				inner2.setBaseSE(inner3);
			}
			searchExp.setO(Operator.AND);
			searchExp.setBaseSE(inner2);
		}
		
		
		// Perform actual search 
		// TODO: Refactor to take SearchExp
		
		GiftResult result = gs.getListOfSearchedGifts(searchExp);
		
		// Cache new results.
		gs.recacheResult(result, session);

		//mv.addObject("giftresult", result.getResults());

		return mv;

	}
	
	@RequestMapping("/etsy-results2")
	public ModelAndView SearchGifts(HttpSession session, @RequestParam String keywords, @RequestParam String keywords2) {
		// Just in case user navigated straight to this page...
		gs.recacheRepositories(session);
		
		ModelAndView mv = new ModelAndView("testthree");
		// Put search operators into repo
		Keyword k = new Keyword(keywords);
		Keyword k2 = new Keyword(keywords2);
		// TODO for later: move all DB stuff into Service, or move it here, but not half and half
		SearchExpression searchExp = new SearchExpression(k, Operator.AND, k2);
		
		
		// Perform actual search 
		// TODO: Refactor to take SearchExp
		GiftResult result = gs.getListOfSearchedGifts(searchExp);
		
		// Cache new results.
		gs.recacheResult(result, session);

		//mv.addObject("giftresult", result.getResults());

		return mv;

	}

	@RequestMapping("/image")
	public ModelAndView giftImages(String listing_id) {
		ModelAndView mv = new ModelAndView("TestOutPut");

		Image result = gs.getGiftImage(listing_id);

		mv.addObject("i", result);
		return mv;

	}

	@RequestMapping("/testtwo")
	public ModelAndView viewGiftstesttwo(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("startsearch");

		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;

		GiftResult result = null;
		if (session.getAttribute("result") == null) {
			result = gs.getListOfGifts();
			session.setAttribute("result", result);
		} else {
			result = (GiftResult) session.getAttribute("result");
		}

		if (session.getAttribute("currentGiftList") == null) {
			session.setAttribute("currentGiftList", result.getResults());
		}

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
	@RequestMapping("/testthree")
	public ModelAndView viewGiftstestthree(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("testthree");

		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;

		GiftResult result = null;
		if (session.getAttribute("result") == null) {
			result = gs.getListOfGifts();
			session.setAttribute("result", result);
		} else {
			result = (GiftResult) session.getAttribute("result");
		}

		if (session.getAttribute("currentGiftList") == null) {
			session.setAttribute("currentGiftList", result.getResults());
		}

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
	

	@RequestMapping("/search")
	public ModelAndView searchSingleKeyword(String kw1, HttpSession session) {
		gs.recacheRepositories(session);

		return new ModelAndView("giftresults");
	}
	

	@RequestMapping("/con")
	public ModelAndView viewGiftscongrad(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("congrad");

		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;

		GiftResult result = null;
		if (session.getAttribute("result") == null) {
			result = gs.getListOfGifts();
			session.setAttribute("result", result);
		} else {
			result = (GiftResult) session.getAttribute("result");
		}

		if (session.getAttribute("currentGiftList") == null) {
			session.setAttribute("currentGiftList", result.getResults());
		}

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
	
	
	
	@RequestMapping("/image/newSearch")
	public ModelAndView giftImagesNoUrl() {
		return new ModelAndView("TestOutPut");
	}



	@RequestMapping("/search-history")
	public ModelAndView showHistoryPage(HttpSession session, @RequestParam(required = false) Integer listId) {
		if (listId == null) {
			return new ModelAndView("searchhistory");
		} else {
			return new ModelAndView("searchhistory", "listId", listId);
		}
	}
	
}

