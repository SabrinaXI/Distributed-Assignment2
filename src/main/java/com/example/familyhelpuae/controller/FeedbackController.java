package com.example.familyhelpuae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.familyhelpuae.model.Feedback;
import com.example.familyhelpuae.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/task/{taskId}")
	public Feedback submitFeedback(@PathVariable Integer taskId, @RequestBody Feedback feedback) {
		return feedbackService.submitFeedback(taskId, feedback);
	}

	@GetMapping("/family/{familyId}")
	public List<Feedback> getFeedbackForFamily(@PathVariable Integer familyId) {
		return feedbackService.getFeedbackForFamily(familyId);
	}

	@GetMapping("/task/{taskId}/exists")
	public boolean hasFeedbackForTask(@PathVariable Integer taskId) {
		return feedbackService.hasFeedbackForTask(taskId);
	}
}
