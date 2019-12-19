package co.grandcircus.bestgift.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.models.datamuse.SynonymResponse;
import co.grandcircus.bestgift.models.datamuse.Word;
/**
 * This uses DataMuse service to find synonyms
 * @author Kevin Flory, Bryan Byrd, Kevin Chung
 *
 */
@Component
public class SynonymService {
	
	public static String synonymUrl = "https://api.datamuse.com/words?ml=";
	
	RestTemplate rt = new RestTemplate();
	
	public List<String> getSynonyms(String phrase){
		
		 Word[] response = rt.getForObject(synonymUrl + phrase, Word[].class);
		 
		 List<String> returnValues = new LinkedList<String>();
		 for (Word w : response) {
			 returnValues.add(w.getWord());
		 }
		 
		 return returnValues;
	}
}
