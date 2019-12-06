package co.grandcircus.bestgift.models.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxonomyPath {

	private String taxonomy_path;

	public TaxonomyPath() {
		super();
	}

	public TaxonomyPath(String taxonomy_path) {
		super();
		this.taxonomy_path = taxonomy_path;
	}

	public String getTaxonomy_path() {
		return taxonomy_path;
	}

	public void setTaxonomy_path(String taxonomy_path) {
		this.taxonomy_path = taxonomy_path;
	}

	@Override
	public String toString() {
		return "TaxonomyPath [taxonomy_path=" + taxonomy_path + "]";
	}

}
