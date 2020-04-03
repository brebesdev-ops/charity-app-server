package com.sk.charity.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sk.charity.util.Response;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	Response response;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
		response.setStatus(200);
		response.setMessage("Login successful.");
		
		String json = new Gson().toJson(response);
		
		logger.info("Login success");
		System.out.println("Success login");
		
		res.setContentType("application/json");
		res.getWriter().write(json);
	}
}