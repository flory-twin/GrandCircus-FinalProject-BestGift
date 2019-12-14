package co.grandcircus.bestgift.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	
	ArrayList<ImageItem> results;

	public ArrayList<ImageItem> getResults() {
		return results;
	}

	public void setResults(ArrayList<ImageItem> results) {
		this.results = results;
	}

	public Image(ArrayList<ImageItem> results) {
		super();
		this.results = results;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Image [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	

}
