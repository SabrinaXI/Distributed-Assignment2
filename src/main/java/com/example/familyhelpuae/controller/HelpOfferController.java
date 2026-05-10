package com.example.familyhelpuae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.familyhelpuae.model.HelpOffer;
import com.example.familyhelpuae.service.HelpOfferService;

@RestController
@RequestMapping("/helpOffers")
public class HelpOfferController {

    @Autowired
    private HelpOfferService helpOfferService;

    @PostMapping("/{familyId}")
    public ResponseEntity<HelpOffer> createHelpOffer(
            @PathVariable Integer familyId,
            @RequestBody HelpOffer helpOffer) {

        return ResponseEntity.ok(
                helpOfferService.createHelpOffer(familyId, helpOffer));
    }

    @GetMapping
    public ResponseEntity<List<HelpOffer>> getAllHelpOffers() {

        return ResponseEntity.ok(
                helpOfferService.getAllHelpOffers());
    }

    @GetMapping("/category")
    public ResponseEntity<List<HelpOffer>> getOffersByCategory(
            @RequestParam String category) {

        return ResponseEntity.ok(
                helpOfferService.getOffersByCategory(category));
    }

    @GetMapping("/family/{familyId}")
    public ResponseEntity<List<HelpOffer>> getOffersByFamily(
            @PathVariable Integer familyId) {

        return ResponseEntity.ok(
                helpOfferService.getOffersByFamily(familyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HelpOffer> updateHelpOffer(
            @PathVariable Long id,
            @RequestBody HelpOffer helpOffer) {

        return ResponseEntity.ok(
                helpOfferService.updateHelpOffer(id, helpOffer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHelpOffer(
            @PathVariable Long id) {

        helpOfferService.deleteHelpOffer(id);

        return ResponseEntity.ok(
                "Help offer deleted successfully");
    }
}