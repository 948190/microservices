package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	// user operations

	// create
	User saveUser(User user);

	// get all user
	List<User> getAllUser();

	// get single user of given userId

	User getUser(String userId);

	// TODO: delete
	// TODO: update

}
