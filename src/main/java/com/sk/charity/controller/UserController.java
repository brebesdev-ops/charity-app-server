package com.sk.charity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.charity.model.User;
import com.sk.charity.service.UserService;
import com.sk.charity.util.Response;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	Response response;
	
	@PostMapping("/signup")
	public String signup(@RequestBody User user) throws Exception {
		
		User emailExists = null;
		if ( user.getEmail() != null && user.getEmail() != "" )
			emailExists = userService.findUserByEmail(user.getEmail());
		User userExists = userService.findUserByUsername(user.getUsername());
		
		if(userExists != null || emailExists != null) {
			throw new Exception("This user already exists.");
			//response.setStatus(401);
			//response.setError("This user already exists.");
			//response.setMessage(null);
		}else {
			User authUser = userService.saveUser(user);
			
			//response.setStatus(201);
			//response.setMessage("Successfully signed up.");
			//response.setError(null);
			return "{\"token\":{\"userId\":" + authUser.getId() + "}}";
		}
		
		//return response;

	}
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//TODO: Send token with userId in response
	@PostMapping("/signin")
	public String signin(@RequestBody User user) throws Exception {
		authenticate(user.getUsername(), user.getPassword());
		
		//Mock
		User authUser = userService.findUserByUsername(user.getUsername());
			
		//response.setStatus(200);
		//response.setMessage("Login successful");
		//response.setError(null);
		return "{\"token\":{\"userId\":" + authUser.getId() + "}}";
	}
	
	//TODO: different endpoint to update each field
	@PostMapping("/user/update/stripeCustomerId")
	public Response updateUser(@RequestBody User user) {
		userService.updateUser(user);
		
		response.setStatus(200);
		response.setMessage("User updated successfully");
		response.setError(null);

		return response;
	}
	
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable int userId) throws Exception {
		Optional<User> user = userService.findUserById(userId);
	
		if ( user.get() == null )
			throw new Exception("User not found.");
		
		//hide sensitive info
		User userResponse = user.get();
		userResponse.setConfirmationToken(null);
		userResponse.setPassword(null);
				
		return userResponse;
	}
	
	@ExceptionHandler({DisabledException.class, BadCredentialsException.class, Exception.class})
	public Response handleAuthenticationException(Exception e) {
		//response.setError("Error");
		response.setError(e.getMessage());
		response.setMessage(null);
		response.setStatus(403);
		
		return response;
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch( DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
	    } catch (BadCredentialsException e) {
	    	throw new Exception("BAD");//new BadCredentialsException("BAD", e);
	    }
		
	}
		
}
