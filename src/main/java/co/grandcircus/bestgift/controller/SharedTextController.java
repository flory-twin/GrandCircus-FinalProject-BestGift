package co.grandcircus.bestgift.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SharedTextController {
	
	@RequestMapping("/shared")
	public ModelAndView testSharedText() {
		List<String> tokenComp1 = new ArrayList<>();
		tokenComp1.add("rain in Spain");
		tokenComp1.add("rain");
		tokenComp1.add("stain");
		tokenComp1.add("plainly");
		List<String> tokenComp2 = new ArrayList<>();
		tokenComp2.add("main");
		tokenComp2.add("rain in Spain");
		tokenComp2.add("stain");
		
		return new ModelAndView("kevin-test-similarityExtraction", "shared", 
				extractSharedWords(
						tokenComp1, tokenComp2));
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
