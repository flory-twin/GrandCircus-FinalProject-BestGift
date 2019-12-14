package co.grandcircus.bestgift.models.etsy.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryPathIds {

	private int category_path_ids;

	public CategoryPathIds() {
		super();
	}

	public CategoryPathIds(int category_path_ids) {
		super();
		this.category_path_ids = category_path_ids;
	}

	public int getCategory_path_ids() {
		return category_path_ids;
	}

	public void setCategory_path_ids(int category_path_ids) {
		this.category_path_ids = category_path_ids;
	}

	@Override
	public String toString() {
		return "CategoryPathIds [category_path_ids=" + category_path_ids + "]";
	}

}
