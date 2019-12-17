package co.grandcircus.bestgift.tables;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.grandcircus.bestgift.models.User;
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
	@ManyToOne
	private User user;

	/*
	 * -----------------------------------------------------------------------------
	 * - Constructors.
	 * -----------------------------------------------------------------------------
	 * -
	 */

	public GiftList() {
		super();
		gifts = new LinkedList<Gift>();
		// TODO Auto-generated constructor stub
	}

	public GiftList(List<Gift> giftsToSet) {
		this();
		gifts = giftsToSet;
	}

	public GiftList(Integer giftListId, List<Gift> gifts, User user) {
		super();
		this.giftListId = giftListId;
		this.gifts = gifts;
		this.user = user;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * - G/S
	 * -----------------------------------------------------------------------------
	 * -
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

	public void setGiftListId(Integer giftListId) {
		this.giftListId = giftListId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
