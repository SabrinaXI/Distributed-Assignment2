package com.example.familyhelpuae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.familyhelpuae.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Integer> {

	Family findByEmail(String email);		// or Optional<Family> findByEmail(String email);
	

}