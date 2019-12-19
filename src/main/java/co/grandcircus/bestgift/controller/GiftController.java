package co.grandcircus.bestgift.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.jparepos.SearchHistoryRepository;
import co.grandcircus.bestgift.jparepos.UserRepo;
import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.GiftResult;
import co.grandcircus.bestgift.search.SearchExpression;
import co.grandcircus.bestgift.services.DataMuseService;
import co.grandcircus.bestgift.services.GiftService;
import co.grandcircus.bestgift.tables.SearchHistory;

@Controller
public class GiftController {
	@Value("${etsy.key}")
	private String etsyKey;

	@Autowired
	GiftService gs;
	
	@Autowired
	DataMuseService dms;
	
	@Autowired
	SearchHistoryRepository shr;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/start-search")
	public ModelAndView viewGifts() {
		ModelAndView mv = new ModelAndView("startsearch");
		
		gs.recacheRepositories();
		
		return mv;

	}

	@RequestMapping("/etsy-results")
	public ModelAndView searchGifts(@RequestParam(value = "keywords1", required = true) String kw1,
			// The first parameter must be present, but the remaining parameters not need be
			// sent
			// in the request (required = false)
			@RequestParam(value = "keywords2", required = false) String kw2,
			@RequestParam(value = "keywords3", required = false) String kw3,
			@RequestParam(value = "keywords4", required = false) String kw4,
			@RequestParam(value = "keywords5", required = false) String kw5,
			@RequestParam(value = "keywords6", required = false) String kw6,
			@RequestParam(value = "keywords7", required = false) String kw7,
			@RequestParam(value = "keywords8", required = false) String kw8,
			@RequestParam(value = "keywords9", required = false) String kw9,
			@RequestParam(value = "keywords10", required = false) String kw10,
			@RequestParam(required = false) Double max_price) {
		List<String> keywords = new LinkedList<>();
		keywords.add(kw1);
		if (kw2 != null && !(kw2.equals(""))) {
			keywords.add(kw2);
		}
		if (kw3 != null && !(kw3.equals(""))) {
			keywords.add(kw3);
		}
		if (kw4 != null && !(kw4.equals(""))) {
			keywords.add(kw4);
		}
		if (kw5 != null && !(kw5.equals(""))) {
			keywords.add(kw5);
		}
		if (kw6 != null && !(kw6.equals(""))) {
			keywords.add(kw6);
		}
		if (kw7 != null && !(kw7.equals(""))) {
			keywords.add(kw7);
		}
		if (kw8 != null && !(kw8.equals(""))) {
			keywords.add(kw8);
		}
		if (kw9 != null && !(kw9.equals(""))) {
			keywords.add(kw9);
		}
		if (kw10 != null && !(kw10.equals(""))) {
			keywords.add(kw10);
		}
		
		// Turns keywords into a list of strings, then passes to private method
		return searchGiftsUsingList(keywords);
	}

	private ModelAndView searchGiftsUsingList(List<String> kws) {
		gs.recacheRepositories();

		// request.getParameter("product"+i+"SkusCnt"))

		ModelAndView mv = new ModelAndView("listing-page");
		SearchExpression searchExp = SearchExpression.createFromKeywords(kws);

		// Perform actual search
		// TODO: Refactor to take SearchExp

		GiftResult result = gs.getListOfSearchedGifts(searchExp);
		
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView searchSingleKeyword(String kw1) {
		gs.recacheRepositories();

		return new ModelAndView("giftresults");
	}

	@RequestMapping("/gift-history")
	public ModelAndView getGiftHistory(Integer historyLogId) {
		gs.recacheRepositories();
		SearchHistory giftHistory = shr.findById(historyLogId).orElse(null);
		gs.recacheResult(giftHistory);
		KeywordController temporaryController = new KeywordController();
		return temporaryController.clearFavoritesAndStashInSession();
	}
	
	@RequestMapping("/topaz")
	public ModelAndView topaz() {
		return new ModelAndView("topaz");
		
	}
}
