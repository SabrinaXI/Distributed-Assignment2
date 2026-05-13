package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	List<Feedback> findByReviewedFamily(Family reviewedFamily);

	boolean existsByTask_TaskId(Integer taskId);
}