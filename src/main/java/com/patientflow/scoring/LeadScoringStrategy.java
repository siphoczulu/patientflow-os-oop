package com.patientflow.scoring;

import com.patientflow.model.Lead;

public interface LeadScoringStrategy {
    int calculateScore(Lead lead);
}