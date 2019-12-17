package co.grandcircus.bestgift.models.etsy.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import co.grandcircus.bestgift.models.etsy.Gift;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Integer tagId;
	private String value;
	@ManyToOne
	private Gift gift;

	public String getValue() {
		return value;
	}

	public Gift getGift() {
		return gift;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String value) {
		this();
		this.value = value;
	}
	
	public Tag(String value, Gift gift) {
		this(value);
		this.gift = gift;
	}
	
	public Tag(Integer key, String aValue, Gift someGift) {
		this(aValue, someGift);
		this.tagId = key;
	}
}
