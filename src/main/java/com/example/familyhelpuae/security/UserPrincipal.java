package com.example.familyhelpuae.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.familyhelpuae.model.Family;


public class UserPrincipal implements UserDetails {

    private Family family;

    public UserPrincipal(Family family) {
        this.family = family;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();										//no roles
    }

    @Override
    public String getPassword() {
        return family.getPassword();
    }

    @Override
    public String getUsername() {
        return family.getEmail();
    }
    
    
    public Family getUser() {
    	return family;
    }
}
