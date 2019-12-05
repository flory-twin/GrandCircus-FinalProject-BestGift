package co.grandcircus.bestgift.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tags {

	private String tags;

	public Tags() {
		super();
	}

	public Tags(String tags) {
		super();
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Tags [tags=" + tags + "]";
	}

}
