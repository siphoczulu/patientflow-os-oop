package com.patientflow.scoring;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

/**
 * Default implementation of the LeadScoringStrategy interface.
 * This class provides a basic scoring algorithm based on budget,
 * travel willingness, treatment timeline, and age.
 * After calculating the score, it assigns a status to the lead. (HOT, WARM, COLD)
 */
public class DefaultLeadScoringStrategy implements LeadScoringStrategy {

    // Calculate score based on business rules (budget, travel willingness, timeline, age)
    @Override
    public int calculateScore(Lead lead) {
        int score = 0;

        // Higher budget leads to higher score
        double budget = lead.getEstimatedBudget();
        if (budget >= 5000) score += 40;
        else if (budget >= 3000) score += 25;
        else if (budget >= 1500) score += 10;

        // Higher travel willingness leads to higher score
        String travel = lead.getTravelWillingness().toUpperCase();
        if (travel.contains("HIGH")) score += 30;
        else if (travel.contains("MEDIUM")) score += 15;

        //Higher urgency in treatment timeline leads to higher score
        String timeline = lead.getTreatmentTimeline().toUpperCase();
        if (timeline.contains("ASAP") || timeline.contains("NOW")) score += 20;
        else if (timeline.contains("1-3")) score += 10;

        // Age between 20 and 60 adds to score
        int age = lead.getAge();
        if (age > 20 && age < 60) score += 5;

        // Assign status based on score
        if (score >= 60) lead.setStatus(LeadStatus.HOT);
        else if (score >= 30) lead.setStatus(LeadStatus.WARM);
        else lead.setStatus(LeadStatus.COLD);

        return score;
    }
}