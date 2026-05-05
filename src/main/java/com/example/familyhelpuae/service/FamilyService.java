package com.example.familyhelpuae.service;

import com.example.familyhelpuae.model.Family;

public interface FamilyService {

    Family registerFamily(Family family);

    Family updateFamily(Integer id, Family family);

}