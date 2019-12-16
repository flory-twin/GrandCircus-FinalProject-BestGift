package co.grandcircus.bestgift.models.dandelion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

	private String spot;
	private String title;
	private String label;

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Entity() {
		super();
	}

	public Entity(String spot, String title, String label) {
		super();
		this.spot = spot;
		this.title = title;
		this.label = label;
	}

	@Override
	public String toString() {
		return "Entity [spot=" + spot + ", title=" + title + ", label=" + label + "]";
	}

}
