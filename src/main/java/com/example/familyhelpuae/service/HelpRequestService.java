package com.example.familyhelpuae.service;

import java.util.List;
import com.example.familyhelpuae.model.HelpRequest;

public interface HelpRequestService {
	
	HelpRequest submitHelpRequest(Integer familyId, HelpRequest helpRequest);						//1. Family should be able to submit help request

	List<HelpRequest> getPendingHelpRequests();														//2. Get all help requests that are not accepted to display to ALL users in dashboard

	List<HelpRequest> getHelpRequestsByFamily(Integer familyId);									// 3. Get all help requests belonging to one family to display in profile

	HelpRequest getHelpRequestById(Integer requestId);												//4. Helper method for 5 & 6

//	HelpRequest markRequestAsAccepted(Integer requestId);											//5. Update help request as accepted when another family accepts help req

	HelpRequest markRequestAsPending(Integer requestId);											//6. Update help request back to pending if helping family decides to cancel  
}
