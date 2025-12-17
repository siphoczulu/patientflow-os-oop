package com.patientflow.scoring;

import com.patientflow.model.Lead;

/**
 * LeadScoringStrategy defines a strategy for calculating the score of a Lead.
 * Different scoring rules can be implemented without changing the Lead class itself.
 */
public interface LeadScoringStrategy {
    // Calculate the score for a given Lead.
    int calculateScore(Lead lead);
}