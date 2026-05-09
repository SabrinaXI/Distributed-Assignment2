package com.example.familyhelpuae.service;


import java.util.List;

import com.example.familyhelpuae.model.HelpOffer;

public interface HelpOfferService {

    HelpOffer createHelpOffer(Integer familyId, HelpOffer helpOffer);

    List<HelpOffer> getAllHelpOffers();

    List<HelpOffer> getOffersByCategory(String category);

    List<HelpOffer> getOffersByFamily(Integer familyId);

    HelpOffer updateHelpOffer(Long id, HelpOffer updatedOffer);

    void deleteHelpOffer(Long id);
}