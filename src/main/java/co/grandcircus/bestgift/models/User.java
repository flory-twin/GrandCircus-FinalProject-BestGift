package co.grandcircus.bestgift.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private int kWTableId;
	
	
	@OneToMany(mappedBy = "userId", orphanRemoval = true)
	private Gift wishListItems;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getkWTableId() {
		return kWTableId;
	}


	public void setkWTableId(int kWTableId) {
		this.kWTableId = kWTableId;
	}


	public Gift getWishListItems() {
		return wishListItems;
	}


	public void setWishListItems(Gift wishListItems) {
		this.wishListItems = wishListItems;
	}


	public User(int userId, String userName, int kWTableId, Gift wishListItems) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.kWTableId = kWTableId;
		this.wishListItems = wishListItems;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "User userId=" + userId + ", userName=" + userName + ", kWTableId=" + kWTableId + ", wishListItems="
				+ wishListItems;
	}
	
	

}
