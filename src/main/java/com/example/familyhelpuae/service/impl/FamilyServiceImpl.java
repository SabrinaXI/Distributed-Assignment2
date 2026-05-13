package com.example.familyhelpuae.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.exception.ResourceNotFoundException;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.service.FamilyService;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public Family registerFamily(Family family) {
    	
    	family.setPassword(encoder.encode(family.getPassword()));
    	
    	family.setTrustScore(0.0);
    	family.setCompletedTasks(0);
    	family.setAverageRating(0.0);
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Integer familyId, Family updatedFamily) {

        Family existingFamily = familyRepository.findById(familyId)
                .orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));

        existingFamily.setFamilyName(updatedFamily.getFamilyName());
        existingFamily.setPhoneNumber(updatedFamily.getPhoneNumber());
        existingFamily.setCity(updatedFamily.getCity());
        existingFamily.setAddress(updatedFamily.getAddress());
        existingFamily.setBio(updatedFamily.getBio());
        existingFamily.setNumberOfMembers(updatedFamily.getNumberOfMembers());

        return familyRepository.save(existingFamily);
    }

    
	@Override
	public Family getCurrentFamily() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();											//when user calls the endpoint api/family/me the user browser sends the cookie JSESSIONID=... along with the request. And then getContext().getAuthentication(); gives the authenticated user
	    String email = authentication.getName();																						//getUsername in UserPrincipal
	    return familyRepository.findByEmail(email);
	}
}