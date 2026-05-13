package com.example.familyhelpuae.service;

import java.util.List;

import com.example.familyhelpuae.model.Feedback;

public interface FeedbackService {

	Feedback submitFeedback(Integer taskId, Feedback feedback);

	List<Feedback> getFeedbackForFamily(Integer familyId);
	
	boolean hasFeedbackForTask(Integer taskId);
}
