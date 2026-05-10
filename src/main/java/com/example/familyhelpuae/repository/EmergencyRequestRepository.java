package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.EmergencyRequest;

public interface EmergencyRequestRepository 
        extends JpaRepository<EmergencyRequest, Long> {

    List<EmergencyRequest> findByStatus(String status);

    List<EmergencyRequest> findByFamilyFamilyId(Integer familyId);
}
