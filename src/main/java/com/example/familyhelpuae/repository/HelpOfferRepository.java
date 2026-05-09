package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.HelpOffer;

public interface HelpOfferRepository extends JpaRepository<HelpOffer, Long> {

   

    List<HelpOffer> findByCategory(String category);

    List<HelpOffer> findByFamilyId(Integer familyId);
}