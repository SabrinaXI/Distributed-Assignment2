package com.example.familyhelpuae.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.familyhelpuae.model.HelpRequest;
import com.example.familyhelpuae.service.HelpRequestService;


@RestController
@RequestMapping("/api/help-requests")
public class HelpRequestController {

	@Autowired
	private HelpRequestService helpRequestService;

	
	@PostMapping("/family/{familyId}")																								//not just POST /api/help-requests because help request related to family id. Hence need to pass family id
	public HelpRequest submitHelpRequest(@PathVariable Integer familyId, @RequestBody HelpRequest helpRequest) {

		return helpRequestService.submitHelpRequest(familyId, helpRequest);
	}

	
	@GetMapping("/pending")
	public List<HelpRequest> getPendingHelpRequests() {

		return helpRequestService.getPendingHelpRequests();
	}

	
	@GetMapping("/family/{familyId}")
	public List<HelpRequest> getHelpRequestsByFamily(@PathVariable Integer familyId) {

		return helpRequestService.getHelpRequestsByFamily(familyId);
	}

	
	@GetMapping("/{requestId}")
	public HelpRequest getHelpRequestById(@PathVariable Integer requestId) {
		
		return helpRequestService.getHelpRequestById(requestId);
	}

	
//	@PutMapping("/{requestId}/accepted")
//	public HelpRequest markRequestAsAccepted(@PathVariable Integer requestId) {
//
//		return helpRequestService.markRequestAsAccepted(requestId);
//	}

	
	@PutMapping("/{requestId}/pending")
	public HelpRequest markRequestAsPending(@PathVariable Integer requestId) {

		return helpRequestService.markRequestAsPending(requestId);
	}

}
