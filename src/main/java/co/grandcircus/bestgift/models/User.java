package co.grandcircus.bestgift.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	private String emailAddress;
	private String passWord;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public User(int userId) {
		super();
		this.userId = userId;
	}

	public User(String emailAddress, String passWord) {
		super();
		this.emailAddress = emailAddress;
		this.passWord = passWord;
	}

	public User(int userId, String emailAddress, String passWord) {
		super();
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.passWord = passWord;
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
