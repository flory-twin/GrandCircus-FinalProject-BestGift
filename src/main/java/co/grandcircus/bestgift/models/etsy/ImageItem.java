package co.grandcircus.bestgift.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageItem {
	
	String url_570xN;

	public String getUrl_570xN() {
		return url_570xN;
	}

	public void setUrl_570xN(String url_570xN) {
		this.url_570xN = url_570xN;
	}

	public ImageItem(String url_570xN) {
		super();
		this.url_570xN = url_570xN;
	}

	public ImageItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ImageItem [url_570xN=" + url_570xN + "]";
	}
	
	
	

}
