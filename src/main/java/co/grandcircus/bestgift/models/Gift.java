package co.grandcircus.bestgift.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gift {

	private int listing_id;
	private int user_id;
	private int category_id;
	private String title;
	private String description;
	private double price;
	private int quantity;
	private String url;
	private ArrayList<Tags> tags;
	private ArrayList<TaxonomyPath> taxonomy_path;

	public Gift() {
		super();
	}

	public Gift(int listing_id, int user_id, int category_id, String title, String description, double price,
			int quantity, String url, ArrayList<Tags> tags, ArrayList<TaxonomyPath> taxonomy_path) {
		super();
		this.listing_id = listing_id;
		this.user_id = user_id;
		this.category_id = category_id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.url = url;
		this.tags = tags;
		this.taxonomy_path = taxonomy_path;
	}

	public int getListing_id() {
		return listing_id;
	}

	public void setListing_id(int listing_id) {
		this.listing_id = listing_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<Tags> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}

	public ArrayList<TaxonomyPath> getTaxonomy_path() {
		return taxonomy_path;
	}

	public void setTaxonomy_path(ArrayList<TaxonomyPath> taxonomy_path) {
		this.taxonomy_path = taxonomy_path;
	}

	@Override
	public String toString() {
		return "Gift [listing_id=" + listing_id + ", user_id=" + user_id + ", category_id=" + category_id + ", title="
				+ title + ", description=" + description + ", price=" + price + ", quantity=" + quantity + ", url="
				+ url + ", tags=" + tags + ", taxonomy_path=" + taxonomy_path + "]";
	}
}
