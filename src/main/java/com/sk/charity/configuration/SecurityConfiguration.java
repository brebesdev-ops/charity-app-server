package com.sk.charity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sk.charity.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MyUserDetailsService userDetailsService;	
	
	@Bean
	CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
		CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
		customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
		return customUsernamePasswordAuthenticationFilter;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	};
	
	@Autowired
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/signup").permitAll()
			.antMatchers("/confirm").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/test").permitAll()
			.antMatchers("/signin").permitAll()
			//.anyRequest()
			//.authenticated()
			.and()
			//.addFilterAt( customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			//.formLogin()
			//.successHandler(customAuthenticationSuccessHandler)
			//.failureHandler(customAuthenticationFailureHandler)
			//.and()
			.httpBasic().disable()
			.cors()
			.and()
			.csrf().disable()
			;
	}
	
}

