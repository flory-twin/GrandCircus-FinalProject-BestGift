package co.grandcircus.bestgift.search.filter;

import java.util.ArrayList;
import java.util.List;

import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.GiftResult;
import co.grandcircus.bestgift.search.Keyword;

public class KeywordSearcher extends Searcher<Keyword> {
	public KeywordSearcher(GiftResult response, Keyword toSearchBy) {
		super(response, toSearchBy);
	}
	
	public KeywordSearcher(List<Gift> gifts, Keyword toSearchBy) {
		super(gifts, toSearchBy);
	}
	
	/**
	 * Search our list of Gifts for those Gifts matching the given Keyword.
	 * @param k
	 * @return
	 */
	@Override
	public List<Gift> findMatchingGifts() {
		List<Gift> nextRoundOfGifts = new ArrayList<>();
		
		// Go through the list of available gifts; 
		for (Gift nextGift : lastRoundOfGifts) {
			GiftMatcher searcher = new GiftMatcher(nextGift);
			// If the next available Gift matches,
			if (searcher.matches(this.whatToSearchOn))
			{
				// Add that Gift to the list.
				nextRoundOfGifts.add(nextGift);
			}
		}
		
		// We're finished, so send the results back.
		return nextRoundOfGifts;
	}
	
	/**
	 * Like findMatchingGifts(Keyword), but limits the number of max results.
	 * @param lastRoundOfGifts
	 * @param k
	 * @param numberToFind
	 * @return
	 */
	@Override
	public List<Gift> findMatchingGifts(int maxNumberToFind) {
		List<Gift> nextRoundOfGifts = new ArrayList<>();
		
		boolean moreGiftsLeftToSearch = (lastRoundOfGifts.size() > 0);
		// Until we're out of Gifts or we have numberToFind matching Gifts,
		while (maxNumberToFind > 0 && moreGiftsLeftToSearch) {
			// Go through the list of available gifts; 
			for (Gift nextGift : lastRoundOfGifts) {
				GiftMatcher searcher = new GiftMatcher(nextGift);
				// If the next available Gift matches,
				if (searcher.matches(this.whatToSearchOn))
				{
					// Add that Gift to the list.
					nextRoundOfGifts.add(nextGift);
					// Since we've checked one more Gift off the list, reduce the number we still need to find.
					maxNumberToFind--;
				}
			}
		}
		
		// We're finished, so send the results back.
		return nextRoundOfGifts;
	}
}
