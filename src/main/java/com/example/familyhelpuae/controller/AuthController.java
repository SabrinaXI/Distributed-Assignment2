package com.example.familyhelpuae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.service.FamilyService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 @Autowired
	 private FamilyService familyService;
	
	//login
	//no need to create /login mapping since Spring security will handle it
	
	
	//register
    @PostMapping("/register")
    public Family registerFamily(@RequestBody Family family) {
    	System.out.println("REGISTER CALLED " +family.getEmail());
        return familyService.registerFamily(family);
    }

}
