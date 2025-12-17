package com.patientflow.repository;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

import java.util.List;

/**
 * LeadRepository extends the CrudRepository to provide additional methods specific to Lead entities.
 * it inherits basic CRUD operations and adds methods to find leads by status and city.
 */
public interface LeadRepository extends CrudRepository<Lead, Long> {
    // Find leads by their status (HOT, WARM, COLD)
    List<Lead> findByStatus(LeadStatus status);
    // Find leads by their city
    List<Lead> findByCity(String city);
}
