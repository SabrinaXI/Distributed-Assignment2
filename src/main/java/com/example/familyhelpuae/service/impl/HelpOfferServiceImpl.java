package com.example.familyhelpuae.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpOffer;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.repository.HelpOfferRepository;
import com.example.familyhelpuae.service.HelpOfferService;

@Service
public class HelpOfferServiceImpl implements HelpOfferService {

    @Autowired
    private HelpOfferRepository helpOfferRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public HelpOffer createHelpOffer(Integer familyId, HelpOffer helpOffer) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        helpOffer.setFamily(family);

        return helpOfferRepository.save(helpOffer);
    }

    @Override
    public List<HelpOffer> getAllHelpOffers() {
        return helpOfferRepository.findAll();
    }

    @Override
    public List<HelpOffer> getOffersByCategory(String category) {
        return helpOfferRepository.findByCategory(category);
    }

    @Override
    public List<HelpOffer> getOffersByFamily(Integer familyId) {
        return helpOfferRepository.findByFamilyFamilyId(familyId);
    }

    @Override
    public HelpOffer updateHelpOffer(Long id, HelpOffer updatedOffer) {

        HelpOffer existingOffer = helpOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Help offer not found"));

        existingOffer.setTitle(updatedOffer.getTitle());
        existingOffer.setDescription(updatedOffer.getDescription());
        existingOffer.setCategory(updatedOffer.getCategory());
        existingOffer.setAvailability(updatedOffer.getAvailability());

        return helpOfferRepository.save(existingOffer);
    }

    @Override
    public void deleteHelpOffer(Long id) {
        helpOfferRepository.deleteById(id);
    }
}