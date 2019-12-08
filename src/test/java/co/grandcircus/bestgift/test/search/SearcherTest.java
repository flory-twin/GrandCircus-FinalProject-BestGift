package co.grandcircus.bestgift.test.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.search.Searcher;

class SearcherTest {
	public static String testLabel = "SearcherTest";
	
	@Test
	void testRemoveDuplicatesByListingId() {
		String functionId = ".testRemoveDuplicatesByListingId(). ";
		
		List<Gift> firstList = new ArrayList<Gift>();
		
		String assertionMessage = "A list with no duplicates has the same size after duplicate weeding.";
		Gift listingId1 = new Gift(); 
		listingId1.setListingId(1);
		firstList.add(listingId1);
		
		int oldCount = firstList.size();
		Searcher.removeDuplicatesByListingId(firstList);
		
		assertEquals(oldCount, firstList.size(), 
				testLabel + functionId + assertionMessage);
		
		firstList = new ArrayList<Gift>();
		assertionMessage = "When a duplicate is present, the resulting list size is one less than before.";
		Gift listingId1Again = new Gift(); 
		listingId1Again.setListingId(1);
		firstList.add(listingId1Again);
		
		oldCount = firstList.size();
		Searcher.removeDuplicatesByListingId(firstList);
		
		assertEquals(oldCount, firstList.size(), 
				testLabel + functionId + assertionMessage);

		firstList = new ArrayList<Gift>();
		assertionMessage = "When a duplicate is present, the shared member is still in the output list."; 
		firstList.add(listingId1Again);
		
		oldCount = firstList.size();
		Searcher.removeDuplicatesByListingId(firstList);
		
		boolean idIsInList = false;
		for (Gift g : firstList) {
			if (g.getListingId() == listingId1.getListingId()) {
				idIsInList = true;
			}
		}
		
		assertTrue(idIsInList,  
				testLabel + functionId + assertionMessage);

		firstList = new ArrayList<Gift>();
		assertionMessage = "When a duplicate is present, it will be removed no matter where in the list it occurs.";
		Gift listingId2 = new Gift(); 
		listingId2.setListingId(2);
		Gift listingId256 = new Gift(); 
		listingId256.setListingId(256);
		
		firstList.add(listingId1);
		firstList.add(listingId256);
		firstList.add(listingId2);
		firstList.add(listingId1Again);
		
		oldCount = firstList.size();
		Searcher.removeDuplicatesByListingId(firstList);
		
		assertEquals(oldCount - 1, firstList.size(), 
				testLabel + functionId + assertionMessage);
		
		firstList = new ArrayList<Gift>();
		assertionMessage = "Multiple, out-of-order duplicates will all be removed.";
		Gift listingId15 = new Gift(); 
		listingId15.setListingId(15);
		
		firstList.add(listingId15);
		firstList.add(listingId256);
		firstList.add(listingId1Again);
		firstList.add(listingId15);
		firstList.add(listingId2);
		firstList.add(listingId1);
		
		oldCount = firstList.size();
		Searcher.removeDuplicatesByListingId(firstList);
		
		assertEquals(oldCount - 1 - 1, firstList.size(), 
				testLabel + functionId + assertionMessage);
	}

}
