package com.patientflow.repository;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

import java.util.List;

public interface LeadRepository extends CrudRepository<Lead, Long> {
    List<Lead> findByStatus(LeadStatus status);
    List<Lead> findByCity(String city);
}
