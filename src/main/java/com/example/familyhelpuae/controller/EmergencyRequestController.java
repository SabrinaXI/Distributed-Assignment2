package com.example.familyhelpuae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.familyhelpuae.model.EmergencyRequest;
import com.example.familyhelpuae.service.EmergencyRequestService;

@RestController
@RequestMapping("/emergencyRequests")
public class EmergencyRequestController {

    @Autowired
    private EmergencyRequestService emergencyRequestService;

    @PostMapping("/{familyId}")
    public ResponseEntity<EmergencyRequest> createEmergencyRequest(
            @PathVariable Integer familyId,
            @RequestBody EmergencyRequest emergencyRequest) {

        return ResponseEntity.ok(
                emergencyRequestService.createEmergencyRequest(
                        familyId, emergencyRequest));
    }

    @GetMapping
    public ResponseEntity<List<EmergencyRequest>> getAllEmergencyRequests() {

        return ResponseEntity.ok(
                emergencyRequestService.getAllEmergencyRequests());
    }

    @GetMapping("/open")
    public ResponseEntity<List<EmergencyRequest>> getOpenEmergencyRequests() {

        return ResponseEntity.ok(
                emergencyRequestService.getOpenEmergencyRequests());
    }

    @GetMapping("/family/{familyId}")
    public ResponseEntity<List<EmergencyRequest>> getRequestsByFamily(
            @PathVariable Integer familyId) {

        return ResponseEntity.ok(
                emergencyRequestService.getRequestsByFamily(familyId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<EmergencyRequest> resolveEmergencyRequest(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emergencyRequestService.resolveEmergencyRequest(id));
    }
}