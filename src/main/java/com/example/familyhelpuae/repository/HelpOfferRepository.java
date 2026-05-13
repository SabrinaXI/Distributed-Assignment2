package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpOffer;

public interface HelpOfferRepository extends JpaRepository<HelpOffer, Integer> {

    List<HelpOffer> findByCategory(String category);

    List<HelpOffer> findByOfferingFamily(Family offeringFamily);
    
    List<HelpOffer> findByCategoryAndCity(String category, String city);			//bonus feature
}
