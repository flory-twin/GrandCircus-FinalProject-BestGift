package co.grandcircus.bestgift.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import co.grandcircus.bestgift.tables.GiftList;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	private String emailAddress;
	private String passWord;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public User() {
		super();
	}

	public User(Integer userId) {
		super();
		this.userId = userId;
	}

	public User(String emailAddress, String passWord) {
		super();
		this.emailAddress = emailAddress;
		this.passWord = passWord;
	}

	public User(Integer userId, String emailAddress, String passWord) {
		super();
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailAddress=" + emailAddress + ", passWord=" + passWord + "]";
	}

	/**
	 * private int kWTableId;
	 * 
	 * 
	 * // @OneToMany(mappedBy = "userId", orphanRemoval = true) private Gift
	 * wishListItems;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public int getkWTableId() { return kWTableId; }
	 * 
	 * 
	 * public void setkWTableId(int kWTableId) { this.kWTableId = kWTableId; }
	 * 
	 * 
	 * public Gift getWishListItems() { return wishListItems; }
	 * 
	 * 
	 * public void setWishListItems(Gift wishListItems) { this.wishListItems =
	 * wishListItems; }
	 * 
	 * 
	 * public User(int userId, String userName, int kWTableId, Gift wishListItems) {
	 * super(); this.userId = userId; this.userName = userName; this.kWTableId =
	 * kWTableId; this.wishListItems = wishListItems; }
	 * 
	 * 
	 * public User() { super(); // TODO Auto-generated constructor stub }
	 * 
	 * 
	 * @Override public String toString() { return "User userId=" + userId + ",
	 *           userName=" + userName + ", kWTableId=" + kWTableId + ",
	 *           wishListItems=" + wishListItems; }
	 * 
	 */

}
