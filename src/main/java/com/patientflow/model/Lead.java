package com.patientflow.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Lead class to represent a potential patient lead.
 * Contains personal details, treatment preferences, budget, score, and status.
 * We kept this separate from Patient to clearly distinguish between potential and actual patients.
 * Additionally the reason we used a seperate ContactInfo class is so that we can reuse it in both Lead and Patient classes.
 */
public class Lead {

    // Auto-incrementing ID generator for leads. Starts from 1.
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private final long id;
    private String fullName;
    private ContactInfo contactInfo;
    private int age;
    private String travelWillingness;
    private String treatmentType;
    private String treatmentTimeline;
    private double estimatedBudget;
    private int score; // Lead score
    private LeadStatus status;

    // Constructor to create a Lead object
    public Lead(String fullName, ContactInfo contactInfo, int age,
                String travelWillingness, String treatmentType,
                String treatmentTimeline, double estimatedBudget) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.age = age;
        this.travelWillingness = travelWillingness;
        this.treatmentType = treatmentType;
        this.treatmentTimeline = treatmentTimeline;
        this.estimatedBudget = estimatedBudget;
        this.score = 0; // Default score
        this.status = LeadStatus.COLD;
    }

    // Our Getters
    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public ContactInfo getContactInfo() { return contactInfo; }
    public int getAge() { return age; }
    public String getTravelWillingness() { return travelWillingness; }
    public String getTreatmentType() { return treatmentType; }
    public String getTreatmentTimeline() { return treatmentTimeline; }
    public double getEstimatedBudget() { return estimatedBudget; }
    public int getScore() { return score; }
    public LeadStatus getStatus() { return status; }

    // Our Setters
    public void setScore(int score) { this.score = score; }
    public void setStatus(LeadStatus status) { this.status = status; }

    // THis method is for displaying lead information. Console output.
    @Override
    public String toString() {
        return "Lead{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", city='" + contactInfo.getCity() + '\'' +
                ", treatmentType='" + treatmentType + '\'' +
                ", budget=" + estimatedBudget +
                ", status=" + status +
                ", score=" + score +
                '}';
    }
}
