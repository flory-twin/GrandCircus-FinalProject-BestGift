package co.grandcircus.bestgift.controller;

import java.util.List;

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
import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;
import co.grandcircus.bestgift.models.Image;
import co.grandcircus.bestgift.search.Keyword;
import co.grandcircus.bestgift.search.KeywordSearcher;
import co.grandcircus.bestgift.search.Operator;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.search.Searcher;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;

	@Autowired
	GiftService gs;

	@Autowired 
	GiftListRepository gl;
	@Autowired
	SearchExpressionRepository ser;
	@Autowired
	KeywordRepository kr;
	
	@RequestMapping("/")
	public ModelAndView routeFromIndex(HttpSession session) {
		recacheRepositories(session);
		return viewGifts(session);
	}

	@RequestMapping("/gift-results")
	public ModelAndView viewGifts(HttpSession session) {
		Image imgResult;
		int listId;
		String imageUrl;
		ModelAndView mv = new ModelAndView("giftresults");

		String url = "https://openapi.etsy.com/v2/listings/active?api_key=" + etsyKey;
		
		recacheRepositories(session);
		
		GiftResult result = gs.getListOfGifts();
		session.setAttribute("result", result);		
		session.setAttribute("currentGiftList", result.getResults());
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

	@RequestMapping("/etsy-results")
	public ModelAndView SearchGifts(HttpSession session, @RequestParam String keywords, @RequestParam Double max_price) {
		// Just in case user navigated straight to this page...
		recacheRepositories(session);
		
		ModelAndView mv = new ModelAndView("TestOutPut");
		// Put search operators into repo
		Keyword k = addKeyword(keywords);
		// TODO for later: move all DB stuff into Service, or move it here, but not half and half
		SearchExpression searchExp = addSearchExpression(k);
		
		// Perform actual search 
		// TODO: Refactor to take SearchExp
		GiftResult result = gs.getListOfSearchedGifts(keywords, max_price);
		
		// Cache new results.
		this.recacheResult(result, session);

		//mv.addObject("giftresult", result.getResults());

		return mv;

	}
	
	@RequestMapping("/etsy-results2")
	public ModelAndView SearchGifts(HttpSession session, @RequestParam String keywords, @RequestParam String keywords2) {
		// Just in case user navigated straight to this page...
		recacheRepositories(session);
		
		ModelAndView mv = new ModelAndView("TestOutPut");
		// Put search operators into repo
		Keyword k = addKeyword(keywords);
		Keyword k2 = addKeyword(keywords2);
		// TODO for later: move all DB stuff into Service, or move it here, but not half and half
		SearchExpression searchExp = addSearchExpression(k, k2);
		
		
		// Perform actual search 
		// TODO: Refactor to take SearchExp
		GiftResult result = gs.getListOfSearchedGifts(keywords, keywords2);
		
		// Cache new results.
		this.recacheResult(result, session);

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

	@RequestMapping("/image/newSearch")
	public ModelAndView giftImagesNoUrl() {
		return new ModelAndView("TestOutPut");
	}

	@RequestMapping("/search")
	public ModelAndView searchSingleKeyword(String kw1, HttpSession session) {
		recacheRepositories(session);
		
		List<Gift> lastRoundOfGifts = ((List<Gift>) session.getAttribute("currentGiftList"));
		Keyword k = addKeyword(kw1);
		Searcher seekAmongGifts = new KeywordSearcher(lastRoundOfGifts, k);
		session.setAttribute("currentGiftList", seekAmongGifts.findMatchingGifts());

		return new ModelAndView("giftresults");
	}
	
	@RequestMapping("/search-history")
	public ModelAndView showHistoryPage(HttpSession session, @RequestParam(required = false) Integer listId) {
		if (listId == null) {
			return new ModelAndView("searchhistory");
		} else {
			return new ModelAndView("searchhistory", "listId", listId);
		}
	}
	
	private void recacheResult(GiftResult toCache, HttpSession session) {
		session.setAttribute("result", toCache);
		session.setAttribute("currentGiftList", toCache.getResults());
	}
	private void recacheRepositories(HttpSession session) {
		session.setAttribute("gs", gs);
		session.setAttribute("gl", gl);
	}
	
	private Keyword addKeyword(String value) {
		Keyword k = new Keyword(value);
		kr.save(k);
		return k;
	}
	
	private SearchExpression addSearchExpression(Keyword k) {
		SearchExpression searchExp = new SearchExpression(k);
		ser.save(searchExp);
		return searchExp;
	}
	
	private SearchExpression addSearchExpression(Keyword k1, Keyword k2) {
		SearchExpression searchExp = new SearchExpression(k1, Operator.AND, k2);
		ser.save(searchExp);
		return searchExp;
	}
}
