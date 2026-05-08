package com.example.familyhelpuae.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
public class Family {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer familyId;
	
	private String familyName;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private String city;
	
	private String address;
	
	private String bio;
	
	private Double trustScore;
	
	private Integer completedTasks;
	
	private Double averageRating;
	
	private Integer numberOfMembers;

	
	//CONSTRUCTORS
	public Family() {
		
	}

	public Family(Integer familyId, String familyName, String email, String password, String phoneNumber, String city,
			String address, String bio, Double trustScore, Integer completedTasks, Double averageRating,
			Integer numberOfMembers) {
		super();
		this.familyId = familyId;
		this.familyName = familyName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.address = address;
		this.bio = bio;
		this.trustScore = trustScore;
		this.completedTasks = completedTasks;
		this.averageRating = averageRating;
		this.numberOfMembers = numberOfMembers;
	}
	
	//GETTERS SETTERS
	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Double getTrustScore() {
		return trustScore;
	}

	public void setTrustScore(Double trustScore) {
		this.trustScore = trustScore;
	}

	public Integer getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(Integer completedTasks) {
		this.completedTasks = completedTasks;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(Integer numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

	
	//TO STRING METHOD
	@Override
	public String toString() {
		return "Family [familyId=" + familyId + ", familyName=" + familyName + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + ", city=" + city + ", address=" + address + ", bio=" + bio
				+ ", trustScore=" + trustScore + ", completedTasks=" + completedTasks + ", averageRating="
				+ averageRating + ", numberOfMembers=" + numberOfMembers + "]";
	}
	
	
}



