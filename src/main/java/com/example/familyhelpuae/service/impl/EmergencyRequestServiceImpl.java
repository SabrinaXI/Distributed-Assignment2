package com.example.familyhelpuae.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.model.EmergencyRequest;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.repository.EmergencyRequestRepository;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.service.EmergencyRequestService;

@Service
public class EmergencyRequestServiceImpl
        implements EmergencyRequestService {

    @Autowired
    private EmergencyRequestRepository emergencyRequestRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public EmergencyRequest createEmergencyRequest(
            Integer familyId,
            EmergencyRequest emergencyRequest) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() ->
                        new RuntimeException("Family not found"));

        emergencyRequest.setFamily(family);
        emergencyRequest.setPriorityLevel("HIGH");
        emergencyRequest.setStatus("OPEN");
        emergencyRequest.setRequestTime(LocalDateTime.now());

        return emergencyRequestRepository.save(emergencyRequest);
    }

    @Override
    public List<EmergencyRequest> getAllEmergencyRequests() {

        return emergencyRequestRepository.findAll();
    }

    @Override
    public List<EmergencyRequest> getOpenEmergencyRequests() {

        return emergencyRequestRepository.findByStatus("OPEN");
    }

    @Override
    public List<EmergencyRequest> getRequestsByFamily(
            Integer familyId) {

        return emergencyRequestRepository
                .findByFamilyFamilyId(familyId);
    }

    @Override
    public EmergencyRequest resolveEmergencyRequest(Long id) {

        EmergencyRequest request =
                emergencyRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Emergency request not found"));

        request.setStatus("RESOLVED");

        return emergencyRequestRepository.save(request);
    }
}