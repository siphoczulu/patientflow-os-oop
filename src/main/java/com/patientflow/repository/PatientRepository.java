package com.patientflow.repository;

import com.patientflow.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    // Future: findByTreatmentType, findByCity, etc.
}
