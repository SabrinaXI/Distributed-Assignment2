package com.example.familyhelpuae.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familyhelpuae.exception.ResourceNotFoundException;
import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.HelpRequest;
import com.example.familyhelpuae.model.Task;
import com.example.familyhelpuae.repository.FamilyRepository;
import com.example.familyhelpuae.repository.HelpRequestRepository;
import com.example.familyhelpuae.repository.TaskRepository;
import com.example.familyhelpuae.service.FamilyService;
import com.example.familyhelpuae.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private HelpRequestRepository helpRequestRepository;

	@Autowired
	private FamilyRepository familyRepository;

	@Autowired
	private FamilyService familyService;

	@Override
	public Task acceptHelpRequest(Integer requestId) {

		HelpRequest helpRequest = helpRequestRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("HelpRequest", "requestId", requestId));

		if (!helpRequest.getStatus().equals("PENDING")) {
			throw new RuntimeException("This help request is not available anymore.");
		}

		if (taskRepository.existsByHelpRequest_RequestId(requestId)) {
			throw new RuntimeException("A task already exists for this help request.");
		}

		Family helperFamily = familyService.getCurrentFamily();

		if (helpRequest.getRequesterFamily().getFamilyId().equals(helperFamily.getFamilyId())) {
			throw new RuntimeException("You cannot accept your own help request.");
		}

		helpRequest.setStatus("ACCEPTED");
		helpRequestRepository.save(helpRequest);

		Task task = new Task();
		task.setHelpRequest(helpRequest);
		task.setRequesterFamily(helpRequest.getRequesterFamily());
		task.setHelperFamily(helperFamily);
		task.setStatus("IN_PROGRESS");
		task.setCreatedDate(LocalDateTime.now());

		return taskRepository.save(task);
	}

	@Override
	public List<Task> getMyTasks() {

		Family currentFamily = familyService.getCurrentFamily();

		return taskRepository.findByRequesterFamilyOrHelperFamily(currentFamily, currentFamily);
	}

	@Override
	public Task completeTask(Integer taskId) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));
		Family currentFamily = familyService.getCurrentFamily();
		if (!task.getRequesterFamily().getFamilyId().equals(currentFamily.getFamilyId())) {
			throw new RuntimeException("Only requester family can complete this task.");
		}
		task.setStatus("COMPLETED");
		task.setCompletedDate(LocalDateTime.now());
		return taskRepository.save(task);
	}

	@Override
	public Task cancelTask(Integer taskId) {

		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));

		task.setStatus("CANCELLED");

		HelpRequest helpRequest = task.getHelpRequest();
		helpRequest.setStatus("PENDING");
		helpRequestRepository.save(helpRequest);

		return taskRepository.save(task);
	}

}
