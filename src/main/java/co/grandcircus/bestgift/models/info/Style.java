package co.grandcircus.bestgift.models.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Style {

	private String style;

	public Style() {
		super();
	}

	public Style(String style) {
		super();
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "Style [style=" + style + "]";
	}

}
