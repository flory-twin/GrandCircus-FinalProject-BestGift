package co.grandcircus.bestgift.tables;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.grandcircus.bestgift.models.etsy.Gift;

@Entity
@Table(name = "giftlist")
public class GiftList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gift_list_id")
	private Integer giftListId;
	@OneToMany
	private List<Gift> gifts;
	
	/*
	 * ------------------------------------------------------------------------------
	 *  Constructors.
	 * ------------------------------------------------------------------------------
	 */
	
	public GiftList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GiftList(List<Gift> giftsToSet) {
		this();
		gifts = giftsToSet;
	}
	
	
	public GiftList(Integer existingListId, List<Gift> toSet) {
		this(toSet);
		giftListId = existingListId;
	}
	
	/*
	 * ------------------------------------------------------------------------------
	 *  G/S
	 * ------------------------------------------------------------------------------
	 */
	
	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}

	public Integer getGiftListId() {
		return giftListId;
	}
}
