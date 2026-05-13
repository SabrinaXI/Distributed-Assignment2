package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpRequest;

public interface HelpRequestRepository extends JpaRepository<HelpRequest, Integer> {
	
	List<HelpRequest> findByStatus(String status);

	List<HelpRequest> findByRequesterFamily(Family requesterFamily);
}
