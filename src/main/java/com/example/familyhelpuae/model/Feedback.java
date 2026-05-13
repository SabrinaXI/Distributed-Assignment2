package com.example.familyhelpuae.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;

	@OneToOne
	@JoinColumn(name = "task_id")
	private Task task;

	@ManyToOne
	@JoinColumn(name = "reviewer_family_id")
	private Family reviewerFamily; // requester family

	@ManyToOne
	@JoinColumn(name = "reviewed_family_id")
	private Family reviewedFamily; // helper family

	private Integer rating; // 1 to 5

	private String comment;

	private LocalDateTime feedbackDate;

	//Constructors
	public Feedback() {
	}

	
	public Feedback(Integer feedbackId, Task task, Family reviewerFamily, Family reviewedFamily, Integer rating,
			String comment, LocalDateTime feedbackDate) {
		super();
		this.feedbackId = feedbackId;
		this.task = task;
		this.reviewerFamily = reviewerFamily;
		this.reviewedFamily = reviewedFamily;
		this.rating = rating;
		this.comment = comment;
		this.feedbackDate = feedbackDate;
	}

	
	// getters and setters
	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Family getReviewerFamily() {
		return reviewerFamily;
	}

	public void setReviewerFamily(Family reviewerFamily) {
		this.reviewerFamily = reviewerFamily;
	}

	public Family getReviewedFamily() {
		return reviewedFamily;
	}

	public void setReviewedFamily(Family reviewedFamily) {
		this.reviewedFamily = reviewedFamily;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDateTime feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	
	
}
