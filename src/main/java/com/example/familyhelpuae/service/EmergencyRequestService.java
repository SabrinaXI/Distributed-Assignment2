package com.example.familyhelpuae.service;

import java.util.List;

import com.example.familyhelpuae.model.EmergencyRequest;

public interface EmergencyRequestService {

    EmergencyRequest createEmergencyRequest(
            Integer familyId,
            EmergencyRequest emergencyRequest);

    List<EmergencyRequest> getAllEmergencyRequests();

    List<EmergencyRequest> getOpenEmergencyRequests();

    List<EmergencyRequest> getRequestsByFamily(Integer familyId);

    EmergencyRequest resolveEmergencyRequest(Long id);
}
