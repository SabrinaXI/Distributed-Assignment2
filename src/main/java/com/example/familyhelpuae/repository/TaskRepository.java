package com.example.familyhelpuae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.familyhelpuae.model.Family;
import com.example.familyhelpuae.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByRequesterFamilyOrHelperFamily(Family requesterFamily, Family helperFamily);

	boolean existsByHelpRequest_RequestId(Integer requestId);
}
