package co.grandcircus.bestgift.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.models.dandelion.Entity;
import co.grandcircus.bestgift.models.dandelion.EntityExtractionResults;
import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.services.EntityExtractionService;
import co.grandcircus.bestgift.services.GiftService;
import co.grandcircus.bestgift.tables.GiftList;

@Controller
public class KeywordController {
	
	@Autowired
	EntityExtractionService es;
	
	List<List<String>> allKeywords;
	List<String> sharedKeywords;

	@RequestMapping("processFavoritesSelection")
	public ModelAndView calculateFavoritesAndStashInSession(
			@RequestParam(value = "checkbox", required = true) String shouldBeInFavsString, 
			@RequestParam(value = "listId", required = true) Integer id, 
			HttpSession session){
		
		// Fetch/modify the current list of favorites based on the new data.
		GiftList favorites;
		if (session.getAttribute("favorites") != null) {
			favorites = (GiftList) session.getAttribute("favorites");
		} else {
			favorites = new GiftList();
			allKeywords = new ArrayList<>();
		}
		
		boolean shouldBeInFavs = (shouldBeInFavsString.equals("on") ? true : false);
		// Now add or remove the favorite which has been toggled.
		if (shouldBeInFavs == false) {
			// If the Gift should not be in the favorites--but currently is--then remove it.
			if (favorites.getGifts().size() > 0) {
				for (Gift f : favorites.getGifts()) {
					if (f.getListingId() == id) {
						favorites.getGifts().remove(f);
					}
				}
			}		
		} else {
			// If the Gift should be in the favorites--and currently is not--then add it.
			boolean alreadyInFavs = false;
			if (favorites.getGifts().size() > 0) {
				for (Gift f : favorites.getGifts()) {
					if (f.getListingId() == id) {
						alreadyInFavs |= true;
					}
				}
			}
			
			if (!alreadyInFavs) {
				GiftService gs = (GiftService) session.getAttribute("gs");
				Gift toBeAdded = gs.getExistingGiftFromDb(id);
				favorites.getGifts().add(toBeAdded);
				
				// Also, get and add the keywords for this Gift.
				List<String> keywords = new ArrayList<String>();
				EntityExtractionResults eer = es.getResults(toBeAdded.getDescription().replaceAll("\\s", "+"));
				for (Entity e : eer.getAnnotations()) {
					keywords.add(e.getTitle());
				}
				allKeywords.add(keywords);
					
			}
		}
		
		// Now, calculate the set of keywords shared by all favorites. 
		//  --Beware that these are runtime (not persistent) Objects of type String.
		//    Type Keyword is reserved for those search params submitted to a search in the GiftController.--
		
		for (int i = 0; i < allKeywords.size(); i++) {
						// Use those KWs to calculate which KWs are shared between favorites. 
			//  Note that sharedKeywords holds a running list of shared KWs.
			if (i == 0) {
				sharedKeywords = allKeywords.get(i);
			} else {
				// Only try to calculate shared keywords if more than one favorite exists!
				sharedKeywords = extractSharedWords(sharedKeywords, allKeywords.get(i));
			}		
		}
		
		// Finally, put the list of favorites and the list of shared keywords into the session.
		session.setAttribute("favorites", favorites);
		session.setAttribute("sharedKeywords", sharedKeywords);
		
		// Finally finally, return to the main display.
		return new ModelAndView("listing-page");
	}
					
	private List<String> extractSharedWords(List<String> tokens1, List<String> tokens2) {
		List<String> sharedTokens = new LinkedList<>();
		
		// Iterate over the first description.
		// Check each tokenized word in the second description. If there's a match, pass it on for phrase matching.
		// Note: Use the collections as buffers for the duration.
		for (int i1 = 0; i1 < tokens1.size(); i1++) {
			String token1 = tokens1.get(i1);
			for (int i2 = 0; i2 < tokens2.size(); i2++) {
				String token2 = tokens2.get(i2);
				
				if (token1.equalsIgnoreCase(token2)) {
					sharedTokens.add(token2);
				}
			}
		}
		
		return sharedTokens;
	}
}
