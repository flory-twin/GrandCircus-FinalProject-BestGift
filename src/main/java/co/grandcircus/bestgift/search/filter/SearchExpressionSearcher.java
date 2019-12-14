package co.grandcircus.bestgift.search.filter;

import java.util.List;

import co.grandcircus.bestgift.models.etsy.Gift;
import co.grandcircus.bestgift.models.etsy.GiftResult;
import co.grandcircus.bestgift.search.Operator;
import co.grandcircus.bestgift.search.SearchExpression;

public class SearchExpressionSearcher extends Searcher<SearchExpression>{
	public SearchExpressionSearcher(GiftResult response, SearchExpression toSearchBy) {
		super(response, toSearchBy);
		
	}
	
	public SearchExpressionSearcher(List<Gift> gifts, SearchExpression toSearchBy) {
		super(gifts, toSearchBy);
	}
	
	// What we know will be true about the SearchExpression (because of how we set up the constructors):
	//  1. The first keyword will ALWAYS be set.
	//  2. If an Operator is set, then either (2a) the second keyword is set, or (2b) the interior search expression is set.
	@Override
	public List<Gift> findMatchingGifts(int maxNumberToFind) {
		if (whatToSearchOn.getOperator() == null) {
			// Case 1
			//  To evaluate this case, just check whether the keyword matches.
			return new KeywordSearcher(
					lastRoundOfGifts, whatToSearchOn.getKeyword1())
						.findMatchingGifts(maxNumberToFind);
		} else {
			// Case 2
			//  To evaluate this case:
			//   Get a list of Gifts matching Keyword 1.
			//   Get a second list of Gifts--either by (a) matching on Keyword 2, or (b) by matching the SearchExpression stored inside this object.
			//   Apply the Operator to these two lists.
			
			// A note on limiting: I'm going to pull numberToFind^2 members from each of the child lists. That should give us enough .
			int maxKidsToMatch = (int) Math.pow((double) maxNumberToFind, 2.0);
			List<Gift> giftsMatchingKeyword1 = new KeywordSearcher(lastRoundOfGifts, whatToSearchOn.getKeyword1()).findMatchingGifts(maxKidsToMatch);
			List<Gift> secondListOfGifts = null;
			
			if (whatToSearchOn.getKeyword2() != null) {
				// Case 2a: Keyword 1 set, operator set, Keyword 2 set.
				//   Get a list of Gifts matching keyword 2 so we can evaluate the operator.
				secondListOfGifts = new KeywordSearcher(
						lastRoundOfGifts, 
						whatToSearchOn.getKeyword2())
							.findMatchingGifts(maxKidsToMatch);
			} else {
				// Case 2b
				//  Evaluate the BaseExpression to get a list of results.
				secondListOfGifts = new SearchExpressionSearcher(
						lastRoundOfGifts,
						whatToSearchOn.getBaseExpression())
							.findMatchingGifts(maxNumberToFind);
			}
			
			//   For either variant of Case 2, apply the Operator to these two lists.
			if (whatToSearchOn.getOperator() == Operator.OR) {
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
			else if (whatToSearchOn.getOperator() == Operator.AND) {
				// If the operator is AND, return:
				//  - Results matching both search terms.
				
				// TODO later!
				return giftsMatchingKeyword1;
			}
		}
		
		// If we're here, we've impossibly fallen through all the other logic.
		return null;
	}

	@Override
	public List<Gift> findMatchingGifts() {
		// TODO Auto-generated method stub
		return null;
	}
}
