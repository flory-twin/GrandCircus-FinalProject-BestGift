package co.grandcircus.bestgift.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.models.GiftResult;

public abstract class Searcher<T> {

	protected List<Gift> lastRoundOfGifts; 
	
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
	public abstract List<Gift> findMatchingGifts(T t);
	
	/**
	 * Like findMatchingGifts(Keyword), but limits the number of max results.
	 * @param lastRoundOfGifts
	 * @param k
	 * @param numberToFind
	 * @return
	 */
	public abstract List<Gift> findMatchingGifts(T t, int maxNumberToFind);

	public static void sortGiftListByListingId(List<Gift> toBeSorted) {
		toBeSorted.sort(
				(Gift g1, Gift g2) -> 
				{
					int id1 = g1.getListingId();
					int id2 = g2.getListingId();
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
				if (thisGift.getListingId() == nextGift.getListingId()) {
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
			list1ListingIds[index1] = g.getListingId();
		}
		
		int[] list2ListingIds = new int[list2.size()];
		int index2 = 0;
		sortGiftListByListingId(list2);
		for (Gift g : list2) {
			list2ListingIds[index2] = g.getListingId();
		}
		
		LinkedList<Integer> sharedIds = new LinkedList<Integer>();
		// Locate the comparison start point.
		
		
		return sharedIds;
	}
}
