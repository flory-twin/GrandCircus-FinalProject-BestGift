package co.grandcircus.bestgift.models.etsy.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Materials {

	private String materials;

	public Materials() {
		super();
	}

	public Materials(String materials) {
		super();
		this.materials = materials;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	@Override
	public String toString() {
		return "Materials [materials=" + materials + "]";
	}

}
