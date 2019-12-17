package co.grandcircus.bestgift.models.etsy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.grandcircus.bestgift.models.etsy.info.Tags;

/**
 * This Model is both a JSON response component (within a GiftResult) and a
 * database table.
 * 
 *
 */
@Entity
@Table(name = "gift")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gift {

	/*
	 * -----------------------------------------------------------------------------
	 * - Database-specific model properties (not found in/deserializable from JSON)
	 * -----------------------------------------------------------------------------
	 * -
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gift_id")
	private Integer giftId;

	/*
	 * -----------------------------------------------------------------------------
	 * - JSON-specific model properties
	 * -----------------------------------------------------------------------------
	 * -
	 */
	@Column(name = "listing_id")
	@JsonProperty("listing_id")
	private Integer listingId;
	private String title;
	@Column(length = 15000)
	private String description;
	@Column(name = "currency_code")
	@JsonProperty("currency_code")
	private String currencyCode;
	private Double price;
	@OneToOne
	private Image image;
	private ArrayList<Tags> tags;

	public Gift() {
		super();
	}
	
	public Gift(Integer listingId, String title, String description, String currencyCode, Double price,
			Image image, ArrayList<Tags> tags) {
		this();
		this.listingId = listingId;
		this.title = title;
		this.description = description;
		this.currencyCode = currencyCode;
		this.price = price;
		this.image = image;
		this.tags = tags;
	}

	public Gift(Integer giftId, Integer listingId, String title, String description, String currencyCode, Double price,
			Image image, ArrayList<Tags> tags) {
		this(listingId, title, description, currencyCode, price, image, tags);
		this.giftId = giftId;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ArrayList<Tags> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Gift [giftId=" + giftId + ", listingId=" + listingId + ", title=" + title + ", description="
				+ description + ", currencyCode=" + currencyCode + ", price=" + price + ", tags=" + tags + "]";
	}

	/*
	 * private String state; private Integer user_id; private Integer category_id;
	 * private String title; private String description; private Double price;
	 * private String currency_code; private Integer quantity; private
	 * ArrayList<CategoryPath> category_path; private ArrayList<CategoryPathIds>
	 * category_path_ids; private ArrayList<Materials> materials; private String
	 * url; private Integer views; private String when_made; private
	 * ArrayList<Style> style; private String language; private Integer taxonomy_id;
	 * private ArrayList<TaxonomyPath> taxonomy_path; private boolean is_vintage;
	 * 
	 * 
	 * public Gift() { super(); }
	 * 
	 * public Gift(Integer listing_id, String state, Integer user_id, Integer
	 * category_id, String title, String description, Double price, String
	 * currency_code, Integer quantity, ArrayList<Tags> tags,
	 * ArrayList<CategoryPath> category_path, ArrayList<CategoryPathIds>
	 * category_path_ids, ArrayList<Materials> materials, String url, Integer views,
	 * String when_made, ArrayList<Style> style, String language, Integer
	 * taxonomy_id, ArrayList<TaxonomyPath> taxonomy_path, boolean is_vintage) {
	 * super(); this.listing_id = listing_id; this.state = state; this.user_id =
	 * user_id; this.category_id = category_id; this.title = title; this.description
	 * = description; this.price = price; this.currency_code = currency_code;
	 * this.quantity = quantity; this.tags = tags; this.category_path =
	 * category_path; this.category_path_ids = category_path_ids; this.materials =
	 * materials; this.url = url; this.views = views; this.when_made = when_made;
	 * this.style = style; this.language = language; this.taxonomy_id = taxonomy_id;
	 * this.taxonomy_path = taxonomy_path; this.is_vintage = is_vintage; }
	 * 
	 * public Gift(Integer giftId, Integer listing_id, String state, Integer
	 * user_id, Integer category_id, String title, String description, Double price,
	 * String currency_code, Integer quantity, ArrayList<Tags> tags,
	 * ArrayList<CategoryPath> category_path, ArrayList<CategoryPathIds>
	 * category_path_ids, ArrayList<Materials> materials, String url, Integer views,
	 * String when_made, ArrayList<Style> style, String language, Integer
	 * taxonomy_id, ArrayList<TaxonomyPath> taxonomy_path, boolean is_vintage) {
	 * super(); this.listing_id = listing_id; this.state = state; this.user_id =
	 * user_id; this.category_id = category_id; this.title = title; this.description
	 * = description; this.price = price; this.currency_code = currency_code;
	 * this.quantity = quantity; this.tags = tags; this.category_path =
	 * category_path; this.category_path_ids = category_path_ids; this.materials =
	 * materials; this.url = url; this.views = views; this.when_made = when_made;
	 * this.style = style; this.language = language; this.taxonomy_id = taxonomy_id;
	 * this.taxonomy_path = taxonomy_path; this.is_vintage = is_vintage; }
	 * 
	 * public Integer getGiftId() { return this.giftId; }
	 * 
	 * public Integer getListing_id() { return listing_id; }
	 * 
	 * public void setListing_id(Integer listing_id) { this.listing_id = listing_id;
	 * }
	 * 
	 * public String getState() { return state; }
	 * 
	 * public void setState(String state) { this.state = state; }
	 * 
	 * public Integer getUser_id() { return user_id; }
	 * 
	 * public void setUser_id(Integer user_id) { this.user_id = user_id; }
	 * 
	 * public Integer getCategory_id() { return category_id; }
	 * 
	 * public void setCategory_id(Integer category_id) { this.category_id =
	 * category_id; }
	 * 
	 * public String getTitle() { return title; }
	 * 
	 * public void setTitle(String title) { this.title = title; }
	 * 
	 * 
	 * 
	 * public Double getPrice() { return price; }
	 * 
	 * public void setPrice(Double price) { this.price = price; }
	 * 
	 * public String getCurrency_code() { return currency_code; }
	 * 
	 * public void setCurrency_code(String currency_code) { this.currency_code =
	 * currency_code; }
	 * 
	 * public Integer getQuantity() { return quantity; }
	 * 
	 * public void setQuantity(Integer quantity) { this.quantity = quantity; }
	 * 
	 * public ArrayList<Tags> getTags() { return tags; }
	 * 
	 * public void setTags(ArrayList<Tags> tags) { this.tags = tags; }
	 * 
	 * public ArrayList<CategoryPath> getCategory_path() { return category_path; }
	 * 
	 * public void setCategory_path(ArrayList<CategoryPath> category_path) {
	 * this.category_path = category_path; }
	 * 
	 * public ArrayList<CategoryPathIds> getCategory_path_ids() { return
	 * category_path_ids; }
	 * 
	 * public void setCategory_path_ids(ArrayList<CategoryPathIds>
	 * category_path_ids) { this.category_path_ids = category_path_ids; }
	 * 
	 * public ArrayList<Materials> getMaterials() { return materials; }
	 * 
	 * public void setMaterials(ArrayList<Materials> materials) { this.materials =
	 * materials; }
	 * 
	 * public String getUrl() { return url; }
	 * 
	 * public void setUrl(String url) { this.url = url; }
	 * 
	 * public Integer getViews() { return views; }
	 * 
	 * public void setViews(Integer views) { this.views = views; }
	 * 
	 * public String getWhen_made() { return when_made; }
	 * 
	 * public void setWhen_made(String when_made) { this.when_made = when_made; }
	 * 
	 * public ArrayList<Style> getStyle() { return style; }
	 * 
	 * public void setStyle(ArrayList<Style> style) { this.style = style; }
	 * 
	 * public String getLanguage() { return language; }
	 * 
	 * public void setLanguage(String language) { this.language = language; }
	 * 
	 * public Integer getTaxonomy_id() { return taxonomy_id; }
	 * 
	 * public void setTaxonomy_id(Integer taxonomy_id) { this.taxonomy_id =
	 * taxonomy_id; }
	 * 
	 * public ArrayList<TaxonomyPath> getTaxonomy_path() { return taxonomy_path; }
	 * 
	 * public void setTaxonomy_path(ArrayList<TaxonomyPath> taxonomy_path) {
	 * this.taxonomy_path = taxonomy_path; }
	 * 
	 * public boolean isIs_vintage() { return is_vintage; }
	 * 
	 * public void setIs_vintage(boolean is_vintage) { this.is_vintage = is_vintage;
	 * }
	 * 
	 * @Override public String toString() { return "Gift [listing_id=" + listing_id
	 * + ", state=" + state + ", user_id=" + user_id + ", category_id=" +
	 * category_id + ", title=" + title + ", description=" + description +
	 * ", price=" + price + ", currency_code=" + currency_code + ", quantity=" +
	 * quantity + ", tags=" + tags + ", category_path=" + category_path +
	 * ", category_path_ids=" + category_path_ids + ", materials=" + materials +
	 * ", url=" + url + ", views=" + views + ", when_made=" + when_made + ", style="
	 * + style + ", language=" + language + ", taxonomy_id=" + taxonomy_id +
	 * ", taxonomy_path=" + taxonomy_path + ", is_vintage=" + is_vintage + "]"; }
	 */
}
