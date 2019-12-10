package co.grandcircus.bestgift.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;

public class KeywordSearcher extends Searcher<Keyword> {
	public KeywordSearcher(GiftResult response) {
		super(response);
	}
	
	public KeywordSearcher(List<Gift> gifts) {
		super(gifts);
	}
	
	/**
	 * Search our list of Gifts for those Gifts matching the given Keyword.
	 * @param k
	 * @return
	 */
	@Override
	public List<Gift> findMatchingGifts(Keyword k) {
		List<Gift> nextRoundOfGifts = new ArrayList<>();
		
		// Go through the list of available gifts; 
		for (Gift nextGift : lastRoundOfGifts) {
			GiftMatcher searcher = new GiftMatcher(nextGift);
			// If the next available Gift matches,
			if (searcher.matches(k))
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
	public List<Gift> findMatchingGifts(Keyword k, int maxNumberToFind) {
		List<Gift> nextRoundOfGifts = new ArrayList<>();
		
		boolean moreGiftsLeftToSearch = (lastRoundOfGifts.size() > 0);
		// Until we're out of Gifts or we have numberToFind matching Gifts,
		while (maxNumberToFind > 0 && moreGiftsLeftToSearch) {
			// Go through the list of available gifts; 
			for (Gift nextGift : lastRoundOfGifts) {
				GiftMatcher searcher = new GiftMatcher(nextGift);
				// If the next available Gift matches,
				if (searcher.matches(k))
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
	
	// What we know will be true about the SearchExpression (because of how we set up the constructors):
	//  1. The first keyword will ALWAYS be set.
	//  2. If an Operator is set, then either (2a) the second keyword is set, or (2b) the interior search expression is set.
	public List<Gift> findMatchingGifts(SearchExpression s, int maxNumberToFind) {
		if (s.getOperator() == null) {
			// Case 1
			//  To evaluate this case, just check whether the keyword matches.
			return findMatchingGifts(s.getKeyword1());
		} else {
			// Case 2
			//  To evaluate this case:
			//   Get a list of Gifts matching Keyword 1.
			//   Get a second list of Gifts--either by (a) matching on Keyword 2, or (b) by matching the SearchExpression stored inside this object.
			//   Apply the Operator to these two lists.
			
			// A note on limiting: I'm going to pull numberToFind^2 members from each of the child lists. That should give us enough .
			int maxKidsToMatch = (int) Math.pow((double) maxNumberToFind, 2.0);
			List<Gift> giftsMatchingKeyword1 = findMatchingGifts(s.getKeyword1(), maxKidsToMatch);
			List<Gift> secondListOfGifts = null;
			
			if (s.getKeyword2() != null) {
				// Case 2a
				//   Get a list of Gifts matching keyword 2.
				secondListOfGifts = findMatchingGifts(
						new SearchExpression(s.getKeyword2()), maxKidsToMatch);
			} else {
				// Case 2b
				//  Evaluate the BaseExpression to get a list of results.
				secondListOfGifts = findMatchingGifts(
						s.getBaseExpression(), maxNumberToFind);
			}
			
			//   For either variant of Case 2, apply the Operator to these two lists.
			if (s.getOperator() == Operator.OR) {
				// If the operator is OR, return:
				//  - Results matching Keyword 1
				//  - Results matching Keyword 2
				//  - Results matching both keywords		
				
				// Append.
				List<Gift> allGifts = giftsMatchingKeyword1;
				allGifts.addAll(secondListOfGifts);
				
				// Remove IDs by duplicate.
				Searcher.removeDuplicatesByListingId(allGifts);
				
				// Return list.
				return allGifts;
			}
		}
		
		// If we're here, we've impossibly fallen through all the other logic.
		return null;
	}
}
