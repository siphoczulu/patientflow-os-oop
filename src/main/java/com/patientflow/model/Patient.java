package com.patientflow.model;

import java.util.concurrent.atomic.AtomicLong;

public class Patient {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1000);

    private final long id;
    private String fullName;
    private ContactInfo contactInfo;
    private int age;
    private String treatmentType;
    private String notes;

    public Patient(String fullName, ContactInfo contactInfo, int age, String treatmentType, String notes) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.age = age;
        this.treatmentType = treatmentType;
        this.notes = notes;
    }

    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public ContactInfo getContactInfo() { return contactInfo; }
    public int getAge() { return age; }
    public String getTreatmentType() { return treatmentType; }
    public String getNotes() { return notes; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }
    public void setAge(int age) { this.age = age; }
    public void setTreatmentType(String treatmentType) { this.treatmentType = treatmentType; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", city='" + (contactInfo != null ? contactInfo.getCity() : "N/A") + '\'' +
                ", treatmentType='" + treatmentType + '\'' +
                '}';
    }
}
