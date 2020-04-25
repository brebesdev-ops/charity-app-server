package com.sk.charity.service;

import java.util.Optional;

import com.sk.charity.model.User;

public interface UserService {
	public Optional<User> findUserById(int userId);

	public User findUserByEmail(String email);
	
	public User findUserByUsername(String username);
	
	public User saveUser(User user);
	
	public void updateUser(User user);

}
