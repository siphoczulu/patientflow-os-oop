package com.patientflow.model;

/**
 * Enum to represent the status of a Lead.
 * HOT: Highly interested lead
 * WARM: Moderately interested lead
 * COLD: Low interest lead
 * This is importah nt for lead prioritization and follow-up strategies.
 * This is gold for clinics to focus their efforts on the most promising leads.
 */
public enum LeadStatus {
    HOT, WARM, COLD
}
