package com.example.familyhelpuae.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.exception.ResourceNotFoundException;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.Feedback;
import com.example.familyhelpuae.model.Task;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.repository.FeedbackRepository;
import com.example.familyhelpuae.repository.TaskRepository;
import com.example.familyhelpuae.service.FamilyService;
import com.example.familyhelpuae.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private FamilyRepository familyRepository;

	@Autowired
	private FamilyService familyService;

	@Override
	public Feedback submitFeedback(Integer taskId, Feedback feedback) {

		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));

		if (!task.getStatus().equals("COMPLETED")) {
			throw new RuntimeException("Feedback can only be submitted after task is completed.");
		}

		if (feedbackRepository.existsByTask_TaskId(taskId)) {
			throw new RuntimeException("Feedback already exists for this task.");
		}

		Family currentFamily = familyService.getCurrentFamily();

		if (!task.getRequesterFamily().getFamilyId().equals(currentFamily.getFamilyId())) {
			throw new RuntimeException("Only requester family can submit feedback.");
		}

		Family helperFamily = task.getHelperFamily();

		feedback.setTask(task);
		feedback.setReviewerFamily(currentFamily);
		feedback.setReviewedFamily(helperFamily);
		feedback.setFeedbackDate(LocalDateTime.now());

		Feedback savedFeedback = feedbackRepository.save(feedback);

		updateHelperFamilyReputation(helperFamily);

		return savedFeedback;
	}

	@Override
	public List<Feedback> getFeedbackForFamily(Integer familyId) {

		Family family = familyRepository.findById(familyId)
				.orElseThrow(() -> new ResourceNotFoundException("Family", "familyId", familyId));

		return feedbackRepository.findByReviewedFamily(family);
	}

	private void updateHelperFamilyReputation(Family helperFamily) {

		List<Feedback> feedbackList = feedbackRepository.findByReviewedFamily(helperFamily);

		double totalRating = 0;

		for (Feedback feedback : feedbackList) {
			totalRating += feedback.getRating();
		}

		double averageRating = totalRating / feedbackList.size();

		Integer completedTasks = helperFamily.getCompletedTasks();

		if (completedTasks == null) {
			completedTasks = 0;
		}

		completedTasks = completedTasks + 1;

		double trustScore = (averageRating * 15) + (completedTasks * 2);

		if (trustScore > 100) {
			trustScore = 100;
		}

		helperFamily.setAverageRating(averageRating);
		helperFamily.setCompletedTasks(completedTasks);
		helperFamily.setTrustScore(trustScore);

		familyRepository.save(helperFamily);
	}

	@Override
	public boolean hasFeedbackForTask(Integer taskId) {
		return feedbackRepository.existsByTask_TaskId(taskId);
	}
}
