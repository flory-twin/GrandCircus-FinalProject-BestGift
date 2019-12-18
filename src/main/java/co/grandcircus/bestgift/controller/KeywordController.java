package co.grandcircus.bestgift.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.models.dandelion.Entity;
import co.grandcircus.bestgift.models.dandelion.EntityExtractionResults;
import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.info.Tag;
import co.grandcircus.bestgift.services.EntityExtractionService;
import co.grandcircus.bestgift.services.GiftService;
import co.grandcircus.bestgift.tables.GiftList;

@Controller
public class KeywordController {
	
	@Autowired
	EntityExtractionService es;
	@Autowired
	HttpSession session;

	// HTML only sends values for a checkbox when it's toggled on--not when it toggles off.
	@RequestMapping("processFavoritesSelection")
	public ModelAndView calculateFavoritesAndStashInSession(
			@RequestParam(value = "checkbox", required = false) String shouldBeInFavsString, 
			@RequestParam(value = "listId", required = true) Integer id, 
			HttpSession session){
		
		GiftService gs = (GiftService) session.getAttribute("gs");
		Gift toBeProcessed = gs.getExistingGiftFromDb(id);
		
		// Fetch/modify the current list of favorites based on the new data.
		GiftList favorites;
		Map<Integer, List<String>> keywordsMapByGiftId;
		if (session.getAttribute("favorites") != null) {
			favorites = (GiftList) session.getAttribute("favorites");
			keywordsMapByGiftId = (Map<Integer, List<String>>) session.getAttribute("keywords");
		} else {
			favorites = new GiftList();
			keywordsMapByGiftId = new HashMap<>();
		}
		
		boolean shouldBeInFavs = false;
		if (shouldBeInFavsString != null) {
			if (shouldBeInFavsString.equals("on")) {
				shouldBeInFavs = true;
			}
		}
		// Now add or remove the favorite which has been toggled.
		if (shouldBeInFavs == false) {
			removeFavorite(toBeProcessed, favorites, keywordsMapByGiftId);
		} else {
			addFavorite(toBeProcessed, favorites, keywordsMapByGiftId);
		}

		// Finally, return to the main display.
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
	
	private List<String> getUniqueWords(List<String> wordsToCheck) {
		// Check whether list has more than one member.
		if (wordsToCheck.size() > 1) {
			// Check 1st word against rest of list. If it's equal to any other word, remove it.
			String firstWord = wordsToCheck.get(0);
			// Second param to subList is EXCLUSIVE, not inclusive
			List<String> restOfList = wordsToCheck.subList(1, wordsToCheck.size());
			// Don't use contains--it checks by object identity, not string value.
			for (String s : restOfList) {
				if (s.equalsIgnoreCase(firstWord)) {
					// Call this function on the remaining portion of the list only.
					return getUniqueWords(restOfList);
				}
			}
			
			// If we're here, the first word is unique. Retain it, and check the rest of the list recursively.
			List<String> toBeReturned = new LinkedList<>();
			toBeReturned.add(firstWord);
			toBeReturned.addAll(getUniqueWords(restOfList));
			return toBeReturned;
		} else {
			// If the first word was the only member, return the list.
			return wordsToCheck;
		}
	}
	
	/**
	 * Take a Gift out of the favorites. Take its keywords out too.
	 * @param toBeRemoved
	 */
	private void removeFavorite(Gift toBeRemoved, GiftList favorites,
			Map<Integer, List<String>> keywords) {
		// If the Gift is currently in the favorites, then remove it.
		if (favorites.getGifts().size() > 0) {
			for (Gift f : favorites.getGifts()) {
				if (f.getListingId().equals(toBeRemoved.getListingId())) {
					favorites.getGifts().remove(f);
					// Remove its keywords too!
					if (keywords.containsKey(toBeRemoved.getListingId())) {
						keywords.remove(toBeRemoved.getListingId());
					}
				}
			}
		}
		
		recache(favorites, keywords);
	}
	
	private void addFavorite(Gift toBeAdded, GiftList favorites, 
			Map<Integer, List<String>> keywords) {
		// If the Gift should be in the favorites--and currently is not--then add it.
		boolean alreadyInFavs = false;
		if (favorites.getGifts().size() > 0) {
			for (Gift f : favorites.getGifts()) {
				if (f.getListingId().equals(toBeAdded.getListingId())) {
					alreadyInFavs |= true;
				}
			}
		}
		
		if (!alreadyInFavs) {
			favorites.getGifts().add(toBeAdded);
			
			// Also, get and add the extracted keywords for this Gift.
			List<String> giftKeywords = new ArrayList<String>();
			EntityExtractionResults eer = es.getResults(toBeAdded.getDescription().replaceAll("\\s", "+"));
			for (Entity e : eer.getAnnotations()) {
				// Check label, spot, and title. If they (caseless) differ, add the unique values.
				String label = e.getLabel();
				String spot = e.getSpot();
				String title = e.getTitle();
				
				List<String> values = new LinkedList();
				values.add(label);
				values.add(spot);
				values.add(title);
				
				giftKeywords.addAll(values);
			}
			
			// Also, add Etsy's tags to the keywords for this Gift.
			List<String> tagValues = new LinkedList<>();
			for (Tag t : toBeAdded.getTags()) {
				tagValues.add(t.getValue());
			}
			
			giftKeywords.addAll(tagValues);
			
			giftKeywords = getUniqueWords(giftKeywords);
			keywords.put(toBeAdded.getListingId(), giftKeywords);	
			
			recache(favorites, keywords);
		}
	}
	
	private void recache(GiftList favs, Map<Integer, List<String>> keywords) {
		session.setAttribute("favorites", favs);
		session.setAttribute("keywords", keywords);
	}
}
