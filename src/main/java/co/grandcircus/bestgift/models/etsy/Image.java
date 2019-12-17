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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_item_id")
	Integer id;
	@OneToMany
	List<ImageItem> results;
	@OneToOne
	Gift gift;
	
	/*
	 * ------------------------------------------------------------------------------
	 */
	public Image() {
		super();
	}
	
	public Image(ArrayList<ImageItem> results, Gift thatGift) {
		this();
		this.results = results;
		this.gift = thatGift;
	}

	public Image(Integer thatId, ArrayList<ImageItem> thoseResults, Gift thatGift) {
		this(thoseResults, thatGift);
		id = thatId;
	}
	
	/*
	 * ------------------------------------------------------------------------------
	 */	
	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<ImageItem> getResults() {
		return results;
	}

	public void setResults(List<ImageItem> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Image [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	

}
