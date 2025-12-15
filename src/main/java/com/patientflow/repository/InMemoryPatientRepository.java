package com.patientflow.repository;

import com.patientflow.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryPatientRepository implements PatientRepository {

    private final List<Patient> storage = new ArrayList<>();

    @Override
    public Patient save(Patient entity) {
        deleteById(entity.getId());
        storage.add(entity);
        return entity;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return storage.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void deleteById(Long id) {
        storage.removeIf(p -> p.getId() == id);
    }
}
