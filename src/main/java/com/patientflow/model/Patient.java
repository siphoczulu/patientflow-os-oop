package com.patientflow.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Patient class to represent an actual patient.
 * Contains personal details, treatment type, and notes.
 * This class is separate from Lead to clearly distinguish between potential leads and confirmed patients.
 * Another example of why it was so important for us to create a ContactInfo class is to avoid code duplication.
 * Write code once and use it in multiple places :)
 */
public class Patient {

    // Auto-incrementing ID generator for patients. Starts from 1000 so we can ealsily distinguish between leads and patients.
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1000);

    private final long id;
    private String fullName;
    private ContactInfo contactInfo;
    private int age;
    private String treatmentType;
    private String notes;

    // Constructor to create a Patient object
    public Patient(String fullName, ContactInfo contactInfo, int age, String treatmentType, String notes) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.age = age;
        this.treatmentType = treatmentType;
        this.notes = notes;
    }

    // Getters
    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public ContactInfo getContactInfo() { return contactInfo; }
    public int getAge() { return age; }
    public String getTreatmentType() { return treatmentType; }
    public String getNotes() { return notes; }

    // Setters
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }
    public void setAge(int age) { this.age = age; }
    public void setTreatmentType(String treatmentType) { this.treatmentType = treatmentType; }
    public void setNotes(String notes) { this.notes = notes; }

    // Method to display patient information. Console output, just like in Lead class.
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
