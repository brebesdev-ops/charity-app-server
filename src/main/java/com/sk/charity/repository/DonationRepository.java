package com.sk.charity.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.charity.model.Donation;

@Repository("donationRepository")
public interface DonationRepository extends JpaRepository<Donation, Long> {
	Set<Donation> findByUserId(int userId);
	
	Optional<Donation> findByIdAndUserId(int id, int userId);
}
