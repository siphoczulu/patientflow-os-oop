package com.patientflow.repository;

import com.patientflow.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * InMemoryPatientRepository stores Patient objects in memory using a Java List.
 */
public class InMemoryPatientRepository implements PatientRepository {

    // Internal storage for Patient objects
    private final List<Patient> storage = new ArrayList<>();

    // Save or update a Patient entity. If a Patient with the same ID exists, it is replaced.
    @Override
    public Patient save(Patient entity) {
        deleteById(entity.getId());
        storage.add(entity);
        return entity;
    }

    // Finds a Patient by its ID
    @Override
    public Optional<Patient> findById(Long id) {
        return storage.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // Retrieves all Patient entities
    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(storage);
    }

    // Deletes a Patient by its ID
    @Override
    public void deleteById(Long id) {
        storage.removeIf(p -> p.getId() == id);
    }
}
