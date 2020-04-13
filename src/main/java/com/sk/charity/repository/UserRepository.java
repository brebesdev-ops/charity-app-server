package com.sk.charity.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sk.charity.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	//@Modifying(clearAutomatically = true)
	//@Transactional
	//@Query("UPDATE user u SET u.stripeCustomerId = :stripeCustomerId WHERE u.id = :userId")
	//int updateStripeCustomerId(@Param("userId") int userId, @Param("stripeCustomerId") String stripeCustomerId);
}
