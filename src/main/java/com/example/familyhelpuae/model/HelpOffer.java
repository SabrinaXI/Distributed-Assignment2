package com.example.familyhelpuae.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HelpOffer {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String description;
	    private String category;
	    private String availability;
	    private boolean active;

	    @ManyToOne
	    @JoinColumn(name = "family_id")
	    private Family family;

	    public HelpOffer() {
	    }

	    public HelpOffer(String title, String description, String category,
	                     String availability, boolean active, Family family) {
	        this.title = title;
	        this.description = description;
	        this.category = category;
	        this.availability = availability;
	        this.active = active;
	        this.family = family;
	    }
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
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

	    public String getAvailability() {
	        return availability;
	    }

	    public void setAvailability(String availability) {
	        this.availability = availability;
	    }

	    public boolean isActive() {
	        return active;
	    }

	    public void setActive(boolean active) {
	        this.active = active;
	    }

	    public Family getFamily() {
	        return family;
	    }

	    public void setFamily(Family family) {
	        this.family = family;
	    
	    }
}