package com.patientflow.repository;

import com.patientflow.model.Patient;

/**
 * PatientRepository extends the CrudRepository to provide basic CRUD operations for Patient entities.
 * At the moment, it does not add any additional methods beyond those inherited from CrudRepository.
 * This was the last interface we implemented in the repository layer so it remains minimal.
 * We may expand its functionality in the future.
 * Future enhancements may include methods to find patients by specific criteria.
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {
    // Future: findByTreatmentType, findByCity, etc.
}
