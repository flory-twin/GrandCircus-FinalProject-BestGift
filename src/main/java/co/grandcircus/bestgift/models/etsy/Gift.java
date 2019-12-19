package co.grandcircus.bestgift.models.etsy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.grandcircus.bestgift.models.etsy.info.Tag;

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
	@OneToMany
	private List<Tag> tags;

	public Gift() {
		super();
	}
	
	public Gift(Integer listingId, String title, String description, String currencyCode, Double price,
			Image image, List<Tag> tags) {
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
			Image image, List<Tag> tags) {
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Gift [giftId=" + giftId + ", listingId=" + listingId + ", title=" + title + ", description="
				+ description + ", currencyCode=" + currencyCode + ", price=" + price + ", tags=" + tags + "]";
	}

}
