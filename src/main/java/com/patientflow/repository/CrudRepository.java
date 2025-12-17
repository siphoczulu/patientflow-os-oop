package com.patientflow.repository;

import java.util.List;
import java.util.Optional;
/**
 * CrudRepository defines the basic operations that any repository in the system must support.
 * CRUD stands for:
 * Create, Read, Update, Delete
 * T  = the type of object being stored (Lead, Patient, etc.)
 * ID = the type of the object's identifier (Long in our case
 */
public interface CrudRepository<T, ID> {
    // Save or update an entity
    T save(T entity);
    // Find an entity by its ID
    Optional<T> findById(ID id);
    // Retrieve all entities
    List<T> findAll();
    // Delete an entity by its ID
    void deleteById(ID id);
}