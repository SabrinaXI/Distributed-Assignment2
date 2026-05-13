package com.example.familyhelpuae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.familyhelpuae.model.Task;
import com.example.familyhelpuae.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/accept-request/{requestId}")
	public Task acceptHelpRequest(@PathVariable Integer requestId) {
		return taskService.acceptHelpRequest(requestId);
	}

	@GetMapping("/me")
	public List<Task> getMyTasks() {
		return taskService.getMyTasks();
	}

	@PutMapping("/{taskId}/complete")
	public Task completeTask(@PathVariable Integer taskId) {
		return taskService.completeTask(taskId);
	}

	@PutMapping("/{taskId}/cancel")
	public Task cancelTask(@PathVariable Integer taskId) {
		return taskService.cancelTask(taskId);
	}
}
