package co.grandcircus.bestgift.models.dandelion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityExtractionResults {

	private int time;
	private List<Entity> annotations;
	private String lang;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Entity> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Entity> annotations) {
		this.annotations = annotations;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public EntityExtractionResults() {
		super();
	}

	public EntityExtractionResults(int time, List<Entity> annotations, String lang) {
		super();
		this.time = time;
		this.annotations = annotations;
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "EntityExtractionResults [time=" + time + ", annotations=" + annotations + ", lang=" + lang + "]";
	}

}
