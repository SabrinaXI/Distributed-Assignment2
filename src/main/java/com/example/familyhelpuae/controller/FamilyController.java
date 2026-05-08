package com.example.familyhelpuae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.service.FamilyService;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;
    
    @GetMapping("/test")
    public String test() {
        return "testing only";
    }

    @PutMapping("/{familyId}/profile")
    public Family updateFamilyProfile(@PathVariable Integer familyId, @RequestBody Family family) {
        return familyService.updateFamily(familyId, family);
    }
}