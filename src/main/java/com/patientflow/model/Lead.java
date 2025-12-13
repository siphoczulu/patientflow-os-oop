package com.patientflow.model;

import java.util.concurrent.atomic.AtomicLong;

public class Lead {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private final long id;
    private String fullName;
    private ContactInfo contactInfo;
    private int age;
    private String travelWillingness;
    private String treatmentType;
    private String treatmentTimeline;
    private double estimatedBudget;
    private int score;
    private LeadStatus status;

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
        this.score = 0;
        this.status = LeadStatus.COLD;
    }

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

    public void setScore(int score) { this.score = score; }
    public void setStatus(LeadStatus status) { this.status = status; }

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
