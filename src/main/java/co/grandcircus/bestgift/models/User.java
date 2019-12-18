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

	
	
	/*
	 * @OneToMany(mappedBy = "userId", orphanRemoval = true) private Gift
	 * wishListItems;
	 */



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



}
