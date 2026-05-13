package com.example.familyhelpuae.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@OneToOne
	@JoinColumn(name = "help_request_id")
	private HelpRequest helpRequest;

	@ManyToOne
	@JoinColumn(name = "requester_family_id")
	private Family requesterFamily;

	@ManyToOne
	@JoinColumn(name = "helper_family_id")
	private Family helperFamily;

	private String status; // IN_PROGRESS, COMPLETED, CANCELLED

	private LocalDateTime createdDate;

	private LocalDateTime completedDate;

	// Constructors
	public Task() {
	}

	public Task(Integer taskId, HelpRequest helpRequest, Family requesterFamily, Family helperFamily, String status,
			LocalDateTime createdDate, LocalDateTime completedDate) {
		super();
		this.taskId = taskId;
		this.helpRequest = helpRequest;
		this.requesterFamily = requesterFamily;
		this.helperFamily = helperFamily;
		this.status = status;
		this.createdDate = createdDate;
		this.completedDate = completedDate;
	}

	// getters and setters
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public HelpRequest getHelpRequest() {
		return helpRequest;
	}

	public void setHelpRequest(HelpRequest helpRequest) {
		this.helpRequest = helpRequest;
	}

	public Family getRequesterFamily() {
		return requesterFamily;
	}

	public void setRequesterFamily(Family requesterFamily) {
		this.requesterFamily = requesterFamily;
	}

	public Family getHelperFamily() {
		return helperFamily;
	}

	public void setHelperFamily(Family helperFamily) {
		this.helperFamily = helperFamily;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDateTime completedDate) {
		this.completedDate = completedDate;
	}

}
