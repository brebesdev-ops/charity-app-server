package com.sk.charity.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sk.charity.util.Response;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler  {
	
	@Autowired 
	Response responseHandler;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		responseHandler.setStatus(0);
		responseHandler.setError("Login Failed.");
		responseHandler.setMessage("Login failed.");
		
		String json = new Gson().toJson(response);
		
		System.out.println("Failed login");
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}
}