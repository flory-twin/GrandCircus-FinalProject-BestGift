package co.grandcircus.bestgift.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.grandcircus.bestgift.models.info.CategoryPath;
import co.grandcircus.bestgift.models.info.CategoryPathIds;
import co.grandcircus.bestgift.models.info.Materials;
import co.grandcircus.bestgift.models.info.Style;
import co.grandcircus.bestgift.models.info.Tags;
import co.grandcircus.bestgift.models.info.TaxonomyPath;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gift {

	private int listing_id;
	private String state;
	private int user_id;
	private int category_id;
	private String title;
	private String description;
	private double price;
	private String currency_code;
	private int quantity;
	private ArrayList<Tags> tags;
	private ArrayList<CategoryPath> category_path;
	private ArrayList<CategoryPathIds> category_path_ids;
	private ArrayList<Materials> materials;
	private String url;
	private int views;
	private String when_made;
	private ArrayList<Style> style;
	private String language;
	private int taxonomy_id;
	private ArrayList<TaxonomyPath> taxonomy_path;
	private boolean is_vintage;

	public Gift() {
		super();
	}

	public Gift(int listing_id, String state, int user_id, int category_id, String title, String description,
			double price, String currency_code, int quantity, ArrayList<Tags> tags,
			ArrayList<CategoryPath> category_path, ArrayList<CategoryPathIds> category_path_ids,
			ArrayList<Materials> materials, String url, int views, String when_made, ArrayList<Style> style,
			String language, int taxonomy_id, ArrayList<TaxonomyPath> taxonomy_path, boolean is_vintage) {
		super();
		this.listing_id = listing_id;
		this.state = state;
		this.user_id = user_id;
		this.category_id = category_id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.currency_code = currency_code;
		this.quantity = quantity;
		this.tags = tags;
		this.category_path = category_path;
		this.category_path_ids = category_path_ids;
		this.materials = materials;
		this.url = url;
		this.views = views;
		this.when_made = when_made;
		this.style = style;
		this.language = language;
		this.taxonomy_id = taxonomy_id;
		this.taxonomy_path = taxonomy_path;
		this.is_vintage = is_vintage;
	}

	public int getListing_id() {
		return listing_id;
	}

	public void setListing_id(int listing_id) {
		this.listing_id = listing_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<Tags> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}

	public ArrayList<CategoryPath> getCategory_path() {
		return category_path;
	}

	public void setCategory_path(ArrayList<CategoryPath> category_path) {
		this.category_path = category_path;
	}

	public ArrayList<CategoryPathIds> getCategory_path_ids() {
		return category_path_ids;
	}

	public void setCategory_path_ids(ArrayList<CategoryPathIds> category_path_ids) {
		this.category_path_ids = category_path_ids;
	}

	public ArrayList<Materials> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Materials> materials) {
		this.materials = materials;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getWhen_made() {
		return when_made;
	}

	public void setWhen_made(String when_made) {
		this.when_made = when_made;
	}

	public ArrayList<Style> getStyle() {
		return style;
	}

	public void setStyle(ArrayList<Style> style) {
		this.style = style;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getTaxonomy_id() {
		return taxonomy_id;
	}

	public void setTaxonomy_id(int taxonomy_id) {
		this.taxonomy_id = taxonomy_id;
	}

	public ArrayList<TaxonomyPath> getTaxonomy_path() {
		return taxonomy_path;
	}

	public void setTaxonomy_path(ArrayList<TaxonomyPath> taxonomy_path) {
		this.taxonomy_path = taxonomy_path;
	}

	public boolean isIs_vintage() {
		return is_vintage;
	}

	public void setIs_vintage(boolean is_vintage) {
		this.is_vintage = is_vintage;
	}

	@Override
	public String toString() {
		return "Gift [listing_id=" + listing_id + ", state=" + state + ", user_id=" + user_id + ", category_id="
				+ category_id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", currency_code=" + currency_code + ", quantity=" + quantity + ", tags=" + tags + ", category_path="
				+ category_path + ", category_path_ids=" + category_path_ids + ", materials=" + materials + ", url="
				+ url + ", views=" + views + ", when_made=" + when_made + ", style=" + style + ", language=" + language
				+ ", taxonomy_id=" + taxonomy_id + ", taxonomy_path=" + taxonomy_path + ", is_vintage=" + is_vintage
				+ "]";
	}

}
