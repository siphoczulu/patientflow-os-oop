package com.patientflow.repository;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryLeadRepository implements LeadRepository {

    private final List<Lead> storage = new ArrayList<>();

    @Override
    public Lead save(Lead entity) {
        deleteById(entity.getId());
        storage.add(entity);
        return entity;
    }

    @Override
    public Optional<Lead> findById(Long id) {
        return storage.stream()
                .filter(lead -> lead.getId() == id)
                .findFirst();
    }

    @Override
    public List<Lead> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void deleteById(Long id) {
        storage.removeIf(lead -> lead.getId() == id);
    }

    @Override
    public List<Lead> findByStatus(LeadStatus status) {
        List<Lead> result = new ArrayList<>();
        for (Lead lead : storage) {
            if (lead.getStatus() == status) {
                result.add(lead);
            }
        }
        return result;
    }

    @Override
    public List<Lead> findByCity(String city) {
        List<Lead> result = new ArrayList<>();
        if (city == null) return result;

        String target = city.trim().toLowerCase();
        for (Lead lead : storage) {
            String leadCity = lead.getContactInfo().getCity();
            if (leadCity != null && leadCity.trim().toLowerCase().equals(target)) {
                result.add(lead);
            }
        }
        return result;
    }
}
