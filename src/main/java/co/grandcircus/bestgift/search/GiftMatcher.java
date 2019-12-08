package co.grandcircus.bestgift.search;

import co.grandcircus.bestgift.models.Gift;

public class GiftMatcher {
	
	private Gift toEvaluate;

	public GiftMatcher(Gift toSearchOn) {
		super();
		this.toEvaluate = toSearchOn;
	}
	
	public boolean matchesIgnoreCase(String s) {
		return toEvaluate.getDescription()
				.toUpperCase().contains(
						s.toUpperCase());
	}
	
	public boolean matches(Keyword k) {
		return matchesIgnoreCase(k.getValue());
	}
//	
//	// What we know will be true about the SearchExpression (because of how we set up the constructors):
//	//  1. The first keyword will ALWAYS be set.
//	//  2. If an Operator is set, then either (2a) the second keyword is set, or (2b) the interior search expression is set.
//	public boolean matches(SearchExpression s) {
//		if (s.getOperator() == null) {
//			// Case 1
//			//  To evaluate this case, just check whether the keyword matches.
//			return findMatchingGifts(s.getKeyword1());
//		} else {
//			if (s.getKeyword2() != null) {
//				// Case 2a
//				//  To evaluate this case:
//				
//			} else {
//				// Case 2b
//			}
//		}
//		
//		return false;
//	}
}
