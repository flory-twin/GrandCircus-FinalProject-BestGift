package co.grandcircus.bestgift.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;

public class Searcher {

	private List<Gift> lastRoundOfGifts; 
	
	public Searcher(List<Gift> giftsToSearchAmong) {
		lastRoundOfGifts = giftsToSearchAmong;
	}
	public Searcher(GiftResult response) {
		lastRoundOfGifts = response.getResults();
	}
	
	/**
	 * Search our list of Gifts for those Gifts matching the given Keyword.
	 * @param k
	 * @return
	 */
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

	public static void sortGiftListByListingId(List<Gift> toBeSorted) {
		toBeSorted.sort(
				(Gift g1, Gift g2) -> 
				{
					int id1 = g1.getListing_id();
					int id2 = g2.getListing_id();
					if (id1 < id2) {
						return -1;
					} else if (id1 == id2) {
						return 0;
					} else {
						return 1;
					}
				});
	}
	
	// sort list
	// find first
	// if next is same, remove
	// ad infinitum
	
	// Consider replacing with List.removeAll(Predicate p)
	public static void removeDuplicatesByListingId(List<Gift> toBeWeeded) {
		if (toBeWeeded.size() > 0) {
			sortGiftListByListingId(toBeWeeded);
			
			// Starting at the 1st gift:
			int giftCounter = 0;
			// If there's a next gift,
			while (toBeWeeded.size() > (giftCounter + 1)) {
				// see whether it has the same ID as this gift.
				Gift thisGift = toBeWeeded.get(giftCounter);
				Gift nextGift = toBeWeeded.get(giftCounter + 1);
				if (thisGift.getListing_id() == nextGift.getListing_id()) {
					// If so, remove it.
					toBeWeeded.remove(giftCounter + 1);
					// The list just shrank; do NOT add one to the counter.
				} else {
					// The list did NOT shrink. DO add one to the counter.
					giftCounter++;
				}
			}
		}
	}
	
	public static LinkedList<Integer> findSharedListingIds(List<Gift> list1, List<Gift> list2) {
		int[] list1ListingIds = new int[list1.size()];
		int index1 = 0;
		sortGiftListByListingId(list1);
		for (Gift g : list1) {
			list1ListingIds[index1] = g.getListing_id();
		}
		
		int[] list2ListingIds = new int[list2.size()];
		int index2 = 0;
		sortGiftListByListingId(list2);
		for (Gift g : list2) {
			list2ListingIds[index2] = g.getListing_id();
		}
		
		LinkedList<Integer> sharedIds = new LinkedList<Integer>();
		// Locate the comparison start point.
		
		
		return sharedIds;
	}
}
