package com.example.familyhelpuae.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.familyhelpuae.exception.ResourceNotFoundException;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpRequest;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.repository.HelpRequestRepository;
import com.example.familyhelpuae.service.HelpRequestService;

@Service
public class HelpRequestServiceImpl implements HelpRequestService {
	
	@Autowired
	private HelpRequestRepository helpRequestRepository;
	
	@Autowired
	private FamilyRepository familyRepository;

	@Override
	public HelpRequest submitHelpRequest(Integer familyId, HelpRequest helpRequest) {
		Family family = familyRepository.findById(familyId)
				.orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));
		helpRequest.setRequesterFamily(family);																	//only have to set these 3 because the user sets the rest in the requestbody where Springboot converts JSON to HelpRequest object
		helpRequest.setStatus("PENDING");
		helpRequest.setRequestedDate(LocalDateTime.now());
		return helpRequestRepository.save(helpRequest);
	}

	@Override
	public List<HelpRequest> getPendingHelpRequests() {
		return helpRequestRepository.findByStatus("PENDING");
	}

	@Override
	public List<HelpRequest> getHelpRequestsByFamily(Integer familyId) {
		Family family = familyRepository.findById(familyId)
				.orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));
		return helpRequestRepository.findByRequesterFamily(family);													//passing family object not just the family id because in model it is Family not Integer
	}

	@Override
	public HelpRequest getHelpRequestById(Integer requestId) {
		return helpRequestRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("HelpRequest", "requestId", requestId));
	}

//	@Override
//	public HelpRequest markRequestAsAccepted(Integer requestId) {
//		HelpRequest helpRequest = getHelpRequestById(requestId);
//		helpRequest.setStatus("ACCEPTED");
//		return helpRequestRepository.save(helpRequest);																		//updates 
//	}

	@Override
	public HelpRequest markRequestAsPending(Integer requestId) {
		HelpRequest helpRequest = getHelpRequestById(requestId);
		helpRequest.setStatus("PENDING");
		return helpRequestRepository.save(helpRequest);
	}
}
