package com.patientflow.scoring;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

public class DefaultLeadScoringStrategy implements LeadScoringStrategy {

    @Override
    public int calculateScore(Lead lead) {
        int score = 0;

        double budget = lead.getEstimatedBudget();
        if (budget >= 5000) score += 40;
        else if (budget >= 3000) score += 25;
        else if (budget >= 1500) score += 10;

        String travel = lead.getTravelWillingness().toUpperCase();
        if (travel.contains("HIGH")) score += 30;
        else if (travel.contains("MEDIUM")) score += 15;

        String timeline = lead.getTreatmentTimeline().toUpperCase();
        if (timeline.contains("ASAP") || timeline.contains("NOW")) score += 20;
        else if (timeline.contains("1-3")) score += 10;

        int age = lead.getAge();
        if (age > 20 && age < 60) score += 5;

        if (score >= 60) lead.setStatus(LeadStatus.HOT);
        else if (score >= 30) lead.setStatus(LeadStatus.WARM);
        else lead.setStatus(LeadStatus.COLD);

        return score;
    }
}