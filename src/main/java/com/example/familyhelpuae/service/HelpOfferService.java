package com.example.familyhelpuae.service;

import java.util.List;

import com.example.familyhelpuae.model.HelpOffer;

public interface HelpOfferService {

    HelpOffer postHelpOffer(Integer familyId, HelpOffer helpOffer);

    List<HelpOffer> getAllHelpOffers();

    List<HelpOffer> getHelpOffersByFamily(Integer familyId);

    HelpOffer getHelpOfferById(Integer offerId);
    
    //List<HelpOffer> getOffersByCategory(String category);

    HelpOffer updateHelpOffer(Integer offerId, HelpOffer updatedOffer);

    void deleteHelpOffer(Integer offerId);
    
    List<HelpOffer> getRecommendedOffers(Integer requestId);				//bonus feature
}
