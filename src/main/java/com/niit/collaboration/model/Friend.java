package com.niit.collaboration.model;

import javax.persistence.Id;

public class Friend extends ErrorDomain {
	@Id
private String friendId;
private String userId;
private char status;
private char isOnline;
public String getFriendId() {
	return friendId;
}
public void setFriendId(String friendId) {
	this.friendId = friendId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}
public char getIsOnline() {
	return isOnline;
}
public void setIsOnline(char isOnline) {
	this.isOnline = isOnline;
}
}
