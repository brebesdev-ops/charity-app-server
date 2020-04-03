package com.sk.charity.service;

import com.sk.charity.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	
	public User findUserByUsername(String username);
	
	public void saveUser(User user);
	
	//public Boolean authenticate(User user);

}
