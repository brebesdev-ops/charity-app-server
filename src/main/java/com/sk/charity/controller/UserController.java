package com.sk.charity.controller;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	public Response signup(@RequestBody User user) {
		
		User emailExists = userService.findUserByEmail(user.getEmail());
		User userExists = userService.findUserByUsername(user.getUsername());
		
		if(userExists != null || emailExists != null) {
			
			response.setStatus(401);
			response.setError("This user already exists.");
		}else {
			userService.saveUser(user);
			
			response.setStatus(201);
			response.setMessage("Successfully signed up.");
		}
		
		return response;

	}
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public Response signin(@RequestBody User user) throws Exception {
		authenticate(user.getUsername(), user.getPassword());
		
		response.setStatus(200);
		response.setMessage("Login successful");
		return response;
	}
	
	//TODO: fix this
	@ExceptionHandler({DisabledException.class, BadCredentialsException.class, Exception.class})
	public Response handleAuthenticationException(Exception e) {
		response.setError("Error");
		//response.setError(e.getMessage());
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
