package co.grandcircus.bestgift.models.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryPath {

	private String category_path;

	public CategoryPath() {
		super();
	}

	public CategoryPath(String category_path) {
		super();
		this.category_path = category_path;
	}

	public String getCategory_path() {
		return category_path;
	}

	public void setCategory_path(String category_path) {
		this.category_path = category_path;
	}

	@Override
	public String toString() {
		return "CategoryPath [category_path=" + category_path + "]";
	}

}
