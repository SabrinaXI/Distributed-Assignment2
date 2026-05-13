package com.example.familyhelpuae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.familyhelpuae.model.HelpOffer;
import com.example.familyhelpuae.service.HelpOfferService;

@RestController
@RequestMapping("/api/help-offers")
public class HelpOfferController {

    @Autowired
    private HelpOfferService helpOfferService;

    @PostMapping("/family/{familyId}")
    public HelpOffer postHelpOffer(@PathVariable Integer familyId,
                                   @RequestBody HelpOffer helpOffer) {
        return helpOfferService.postHelpOffer(familyId, helpOffer);
    }

    @GetMapping
    public List<HelpOffer> getAllHelpOffers() {
        return helpOfferService.getAllHelpOffers();
    }

    @GetMapping("/family/{familyId}")
    public List<HelpOffer> getHelpOffersByFamily(@PathVariable Integer familyId) {
        return helpOfferService.getHelpOffersByFamily(familyId);
    }

    @GetMapping("/{offerId}")
    public HelpOffer getHelpOfferById(@PathVariable Integer offerId) {
        return helpOfferService.getHelpOfferById(offerId);
    }

    @PutMapping("/{offerId}")
    public HelpOffer updateHelpOffer(@PathVariable Integer offerId,
                                     @RequestBody HelpOffer updatedOffer) {
        return helpOfferService.updateHelpOffer(offerId, updatedOffer);
    }

    @DeleteMapping("/{offerId}")
    public String deleteHelpOffer(@PathVariable Integer offerId) {
        helpOfferService.deleteHelpOffer(offerId);
        return "Help offer deleted successfully";
    }
    
    
    @GetMapping("/recommendations/request/{requestId}")
    public List<HelpOffer> getRecommendedOffers(@PathVariable Integer requestId) {

        return helpOfferService.getRecommendedOffers(requestId);
    }
}