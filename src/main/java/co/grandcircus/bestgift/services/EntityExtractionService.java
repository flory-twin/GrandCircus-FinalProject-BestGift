package co.grandcircus.bestgift.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bestgift.models.dandelion.EntityExtractionResults;

@Component
public class EntityExtractionService {

	public static String entityExtractionUrl = "https://api.dandelion.eu/datatxt/nex/v1/?token=e48c1e1cd43b4fe1a7f8e3e4fdeb6fb5&text=";
	
	RestTemplate rt = new RestTemplate();
	
	public EntityExtractionResults getResults(String description) {
		
		EntityExtractionResults response = rt.getForObject(entityExtractionUrl + description, EntityExtractionResults.class);
		// EntityExtractionResults response = rt.getForObject(entityExtractionUrl + description + "&top_entities=15", EntityExtractionResults.class);
		
		return response;
	}
}
