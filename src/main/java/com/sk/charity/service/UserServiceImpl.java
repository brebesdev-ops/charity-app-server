package com.sk.charity.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sk.charity.model.Role;
import com.sk.charity.model.User;
import com.sk.charity.repository.RoleRepository;
import com.sk.charity.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(false);
		user.setConfirmationToken(UUID.randomUUID().toString());
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		return userRepository.save(user);
	}
	
	public Optional<User> findUserById(int userId) {
		return userRepository.findById(userId);
	}
	
	//currently updates stripeCustomerId only.
	@Override
	public void updateUser(User user) {
		//update only the fields where user.field isn't empty
		//don't update password and confirmationToken fields
		
		User u = userRepository.getOne(user.getId());		
		
		u.setStripeCustomerId(user.getStripeCustomerId()); //example
		userRepository.save(u);
	}

	
}
