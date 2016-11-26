package com.niit.collaboration.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDetailsDAO;

import com.niit.collaboration.model.UserDetails;

@RestController
public class UserController {
@Autowired 
UserDetailsDAO userDetailsDAO;
@Autowired
FriendDAO friendDAO;
@Autowired
UserDetails userDetails;

@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
public ResponseEntity<UserDetails> addNewUser(@RequestBody UserDetails userDetails) {
	userDetails.setStatus('N');
	userDetails.setIsOnline('N');
	userDetails.setRole("Role_User");
	if (userDetailsDAO.saveUser(userDetails)) {

		userDetails.setErrorCode("200");
		userDetails.setErrorMessage("Seccessfully registered!");
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	} else {

		UserDetails userDetails1 = new UserDetails();
		userDetails1.setUserId("notRegisted");
		userDetails1.setErrorCode("404");
		userDetails1.setErrorMessage("you're Not registered");
		if(userDetailsDAO.getUser(userDetails.getUserId())!=null){
			userDetails.setErrorCode("404");
			userDetails.setErrorMessage("user already exists with this Id:"+userDetails.getUserId());
			return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
		}
		return new ResponseEntity<UserDetails>(userDetails1, HttpStatus.OK);
	}

}


@RequestMapping(value = "/users", method = RequestMethod.GET)
public ResponseEntity<List<UserDetails>> getUserList() {
	List<UserDetails> userList = userDetailsDAO.getUserList();
	if (userList.isEmpty()) {
		userDetails.setErrorCode("404");
		userDetails.setErrorMessage("Users are not available");
		userList.add(userDetails);
	}
	return new ResponseEntity<List<UserDetails>>(userList, HttpStatus.OK);
}
@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
public ResponseEntity<UserDetails> getUser(@PathVariable(value = "userId") String userId) {
	userDetails = userDetailsDAO.getUser(userId);
	if (userDetails == null) {
		userDetails = new UserDetails(); // To avoid null pointer exception
		userDetails.setErrorCode("404");
		userDetails.setErrorMessage("User with this id doesnt exist " + userId);
	}
	return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
}


@RequestMapping(value= "/makeAdmin/{userId}", method= RequestMethod.PUT)
public ResponseEntity<UserDetails> makeAdmin(@PathVariable("userId") String userId){
	userDetails=userDetailsDAO.getUser(userId);
	if(userDetails==null){
		userDetails=new UserDetails();
		userDetails.setErrorCode("404");
		userDetails.setErrorMessage("user doesnt exist");
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.NOT_FOUND);
	}
	userDetails.setRole("Role_Admin");
	userDetailsDAO.updateUser(userDetails);
	userDetails.setErrorCode("200");
	userDetails.setErrorMessage("role updated as admin successfully");
	return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
}

























@RequestMapping(value="/accept/{id}", method=RequestMethod.GET)
public ResponseEntity<UserDetails> accept(@PathVariable("id") String id){
	userDetails=updateStatus(id,'A',"");
	if(userDetailsDAO.updateUser(userDetails)){
		userDetails.setErrorCode("200");
		userDetails.setErrorMessage("successfully updated");
	}else{
		userDetails.setErrorCode("404");
		userDetails.setErrorMessage("not able to update");

}
	return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
}

























private UserDetails updateStatus(String id, char status, String reason) {
	userDetails=userDetailsDAO.getUser(id);
	if(userDetails==null){
		userDetails.setErrorCode("404");
		userDetails.setErrorMessage("couldnt update status to"+ status);
	}else{
		userDetails.setStatus(status);
		userDetails.setReason(reason);
		userDetailsDAO.updateUser(userDetails);
		userDetails.setErrorCode("200");
		userDetails.setErrorMessage("successfully updated");
		
	}

	return userDetails;
}
}
