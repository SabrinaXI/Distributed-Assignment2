package com.example.familyhelpuae.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.exception.ResourceNotFoundException;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpOffer;
import com.example.familyhelpuae.model.HelpRequest;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.repository.HelpOfferRepository;
import com.example.familyhelpuae.repository.HelpRequestRepository;
import com.example.familyhelpuae.service.HelpOfferService;

@Service
public class HelpOfferServiceImpl implements HelpOfferService {

    @Autowired
    private HelpOfferRepository helpOfferRepository;

    @Autowired
    private FamilyRepository familyRepository;
    
    @Autowired
    private HelpRequestRepository helpRequestRepository;

    @Override
    public HelpOffer postHelpOffer(Integer familyId, HelpOffer helpOffer) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));

        helpOffer.setOfferingFamily(family);
        helpOffer.setStatus("AVAILABLE");
        helpOffer.setCreatedDate(LocalDateTime.now());

        return helpOfferRepository.save(helpOffer);
    }

    @Override
    public List<HelpOffer> getAllHelpOffers() {
        return helpOfferRepository.findAll();
    }

    @Override
    public List<HelpOffer> getHelpOffersByFamily(Integer familyId) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));

        return helpOfferRepository.findByOfferingFamily(family);
    }

    @Override
    public HelpOffer getHelpOfferById(Integer offerId) {
        return helpOfferRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("HelpOffer", "offerId", offerId));
    }

    @Override
    public HelpOffer updateHelpOffer(Integer offerId, HelpOffer updatedOffer) {

        HelpOffer existingOffer = getHelpOfferById(offerId);

        existingOffer.setTitle(updatedOffer.getTitle());
        existingOffer.setDescription(updatedOffer.getDescription());
        existingOffer.setCategory(updatedOffer.getCategory());
        existingOffer.setCity(updatedOffer.getCity());
        existingOffer.setStatus(updatedOffer.getStatus());

        return helpOfferRepository.save(existingOffer);
    }

    @Override
    public void deleteHelpOffer(Integer offerId) {

        HelpOffer helpOffer = getHelpOfferById(offerId);

        helpOfferRepository.delete(helpOffer);
    }

	@Override
	public List<HelpOffer> getRecommendedOffers(Integer requestId) {
		HelpRequest helpRequest = helpRequestRepository.findById(requestId)
				.orElseThrow(() ->new ResourceNotFoundException("Help Request","requestId",requestId));

	    return helpOfferRepository.findByCategoryAndCity(helpRequest.getCategory(),helpRequest.getCity() );
	}
}