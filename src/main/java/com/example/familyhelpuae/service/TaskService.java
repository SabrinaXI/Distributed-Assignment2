package com.example.familyhelpuae.service;

import java.util.List;

import com.example.familyhelpuae.model.Task;

public interface TaskService {

	Task acceptHelpRequest(Integer requestId);

	List<Task> getMyTasks();

	Task completeTask(Integer taskId);

	Task cancelTask(Integer taskId);
}
