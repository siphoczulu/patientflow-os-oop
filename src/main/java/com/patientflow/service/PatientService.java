package com.patientflow.service;

import com.patientflow.model.Patient;
import com.patientflow.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Just like LeadService, PatientService has all the business logic related to Patients
 * It interacts with PatientRepository for data storage and retrieval
 * It does not handle user interface or input/output operations directly
 */
public class PatientService {

    // Repository for Patient data
    private final PatientRepository patientRepository;

    /* Constructor to initialize PatientService with required dependencies
     * patientRepository Repository for Patient data
     * This allows us to use different implementations of PatientRepository
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Method to save a Patient
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Method to retrieve all Patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Method to retrieve a Patient by ID
    public Optional<Patient> getPatientById(long id) {
        return patientRepository.findById(id);
    }
}
