package com.example.familyhelpuae.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class HelpOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offerId;

    private String title;

    private String description;

    private String category;

    private String city;

    private String status; // AVAILABLE, UNAVAILABLE

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "offering_family_id")
    private Family offeringFamily;

    //constructors
    public HelpOffer() {
    }

	public HelpOffer(Integer offerId, String title, String description, String category, String city, String status,
			LocalDateTime createdDate, Family offeringFamily) {
		super();
		this.offerId = offerId;
		this.title = title;
		this.description = description;
		this.category = category;
		this.city = city;
		this.status = status;
		this.createdDate = createdDate;
		this.offeringFamily = offeringFamily;
	}

    // getters and setters
	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Family getOfferingFamily() {
		return offeringFamily;
	}

	public void setOfferingFamily(Family offeringFamily) {
		this.offeringFamily = offeringFamily;
	}
	
	
}