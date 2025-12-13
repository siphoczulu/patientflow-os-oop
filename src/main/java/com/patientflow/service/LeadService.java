package com.patientflow.service;

import com.patientflow.model.ContactInfo;
import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;
import com.patientflow.repository.LeadRepository;
import com.patientflow.scoring.LeadScoringStrategy;

import java.util.List;
import java.util.Optional;

public class LeadService {

    private final LeadRepository leadRepository;
    private final LeadScoringStrategy scoringStrategy;

    public LeadService(LeadRepository leadRepository, LeadScoringStrategy scoringStrategy) {
        this.leadRepository = leadRepository;
        this.scoringStrategy = scoringStrategy;
    }

    public Lead createLead(String fullName,
                           ContactInfo contactInfo,
                           int age,
                           String travelWillingness,
                           String treatmentType,
                           String treatmentTimeline,
                           double estimatedBudget) {

        Lead lead = new Lead(fullName, contactInfo, age, travelWillingness, treatmentType, treatmentTimeline, estimatedBudget);

        int score = scoringStrategy.calculateScore(lead);
        lead.setScore(score);

        return leadRepository.save(lead);
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public List<Lead> getLeadsByStatus(LeadStatus status) {
        return leadRepository.findByStatus(status);
    }

    public Optional<Lead> getLeadById(long id) {
        return leadRepository.findById(id);
    }
}