package com.example.familyhelpuae.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.repository.FamilyRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Family family = familyRepository.findByEmail(email);

        if (family == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(family);
    }
}
