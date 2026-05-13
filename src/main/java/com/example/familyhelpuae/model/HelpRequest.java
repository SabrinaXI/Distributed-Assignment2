package com.example.familyhelpuae.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HelpRequest {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer requestId;
   private String title;
   private String description;
   private String category;
   private String status; 																//ACCEPTED, PENDING only. If helper family cancels then status back to PENDING
   private String city;
   private LocalDateTime requestedDate;
   private LocalDateTime neededDate;
   
   @ManyToOne
   @JoinColumn(name = "requester_family_id")
   private Family requesterFamily; 														// requester family ID 
   
   
   //Constructors
   public HelpRequest() {
   }
   public HelpRequest(Integer requestId, String title, String description, String category, String status,
                      String city, LocalDateTime requestedDate, LocalDateTime neededDate,
                      Family requesterFamily) {
       this.requestId = requestId;
       this.title = title;
       this.description = description;
       this.category = category;
       this.status = status;
       this.city = city;
       this.requestedDate = requestedDate;
       this.neededDate = neededDate;
       this.requesterFamily = requesterFamily;
   }
   
   //Getters & Setters
   public Integer getRequestId() {
       return requestId;
   }
   public void setRequestId(Integer requestId) {
       this.requestId = requestId;
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
   public String getStatus() {
       return status;
   }
   public void setStatus(String status) {
       this.status = status;
   }
   public String getCity() {
       return city;
   }
   public void setCity(String city) {
       this.city = city;
   }
   public LocalDateTime getRequestedDate() {
       return requestedDate;
   }
   public void setRequestedDate(LocalDateTime requestedDate) {
       this.requestedDate = requestedDate;
   }
   public LocalDateTime getNeededDate() {
       return neededDate;
   }
   public void setNeededDate(LocalDateTime neededDate) {
       this.neededDate = neededDate;
   }
   public Family getRequesterFamily() {
       return requesterFamily;
   }
   public void setRequesterFamily(Family requesterFamily) {
       this.requesterFamily = requesterFamily;
   }
}
