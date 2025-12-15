package com.patientflow.service;

import com.patientflow.model.Patient;
import com.patientflow.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(long id) {
        return patientRepository.findById(id);
    }
}
