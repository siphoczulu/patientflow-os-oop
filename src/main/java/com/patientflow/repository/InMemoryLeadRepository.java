package com.patientflow.repository;

import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * InMemoryLeadRepository stores Lead objects inm memory using a Java List.
 */
public class InMemoryLeadRepository implements LeadRepository {

    // Internal storage for Lead objects
    private final List<Lead> storage = new ArrayList<>();

    // Save or update a Lead entity. If a Lead with the same ID exists, it is replaced.
    @Override
    public Lead save(Lead entity) {
        deleteById(entity.getId());
        storage.add(entity);
        return entity;
    }

    // Finds a Lead by its ID
    @Override
    public Optional<Lead> findById(Long id) {
        return storage.stream()
                .filter(lead -> lead.getId() == id)
                .findFirst();
    }
    // Retrieves all Lead entities
    @Override
    public List<Lead> findAll() {
        return new ArrayList<>(storage);
    }
    // Deletes a Lead by its ID
    @Override
    public void deleteById(Long id) {
        storage.removeIf(lead -> lead.getId() == id);
    }
    // Finds Leads by their status (HOT, WARM, COLD)
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

    /** Finds Leads by their city
     * We kept the search case-insensitive incase of user input variations.
     */
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
