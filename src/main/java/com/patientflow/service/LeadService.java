package com.patientflow.service;

import com.patientflow.model.ContactInfo;
import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;
import com.patientflow.repository.LeadRepository;
import com.patientflow.scoring.LeadScoringStrategy;

import java.util.List;
import java.util.Optional;

/**
 * LeadService has all the business logic related to Leads
 * It interacts with LeadRepository for data storage and retrieval
 * It uses LeadScoringStrategy to calculate lead scores
 * It also has a method to convert Leads to Patients and interacts with PatientService for that purpose
 * It does not handle user interface or input/output operations directly
 */
public class LeadService {

    //Repository for Lead data
    private final LeadRepository leadRepository;
    //Strategy for calculating Lead scores
    private final LeadScoringStrategy scoringStrategy;

    /* Constructor to initialize LeadService with required dependencies
     * leadRepository Repository for Lead data
     * scoringStrategy Strategy for calculating Lead scores
     * THis allows us to use different implementations of LeadRepository and LeadScoringStrategy
     */
    public LeadService(LeadRepository leadRepository, LeadScoringStrategy scoringStrategy) {
        this.leadRepository = leadRepository;
        this.scoringStrategy = scoringStrategy;
    }

    /* Method to create a new Lead
     * fullName Full name of the lead
     * contactInfo Contact information of the lead
     * age Age of the lead
     * travelWillingness Willingness to travel for treatment
     * treatmentType Type of treatment interested in
     * treatmentTimeline Timeline for treatment
     * estimatedBudget Estimated budget for treatment
     * it calculates the lead score using the scoring strategy and saves the lead to the repository
     * it then returns the created Lead with calculated score
     */
    public Lead createLead(String fullName,
                           ContactInfo contactInfo,
                           int age,
                           String travelWillingness,
                           String treatmentType,
                           String treatmentTimeline,
                           double estimatedBudget) {

        Lead lead = new Lead(fullName, contactInfo, age, travelWillingness, treatmentType, treatmentTimeline, estimatedBudget);

        // Calculate lead score using the scoring strategy
        int score = scoringStrategy.calculateScore(lead);
        lead.setScore(score);

        // Save the lead to the repository
        return leadRepository.save(lead);
    }

    // Method to retrieve all leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // Method to retrieve leads by their status (HOT, WARM, COLD)
    public List<Lead> getLeadsByStatus(LeadStatus status) {
        return leadRepository.findByStatus(status);
    }

    // Method to retrieve a lead by its ID
    public Optional<Lead> getLeadById(long id) {
        return leadRepository.findById(id);
    }
    // Method to retrieve leads by city
    public List<Lead> getLeadsByCity(String city) {
        return leadRepository.findByCity(city);
    }

    //This method converts a Lead to a Patient and deletes the Lead after conversion
    public com.patientflow.model.Patient convertLeadToPatient(long leadId,
                                                              String notes,
                                                              PatientService patientService) {

        // Retrieve the lead by ID or throw an exception if not found
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + leadId));

        //This is the conversion logic from Lead to Patient. It takes the lead data and creates a new Patient object
        com.patientflow.model.Patient patient = new com.patientflow.model.Patient(
                lead.getFullName(),
                lead.getContactInfo(),
                lead.getAge(),
                lead.getTreatmentType(),
                notes
        );

        patientService.save(patient);

        // THis deletes the lead after conversion to patient
        leadRepository.deleteById(leadId);

        return patient;
    }

}