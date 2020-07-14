package com.models;

public class User {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String userPass;
	private String userMobile;
	private String userDP;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDP() {
		return userDP;
	}
	public void setUserDP(String userDP) {
		this.userDP = userDP;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public User(int userId, String userName, String userEmail, String userPass, String userMobile, String userDP) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userMobile = userMobile;
		this.userDP = userDP;
	}
}
