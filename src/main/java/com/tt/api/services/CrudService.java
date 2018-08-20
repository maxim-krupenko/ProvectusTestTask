package com.tt.api.services;

import com.tt.api.entities.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends AbstractEntity> {
    Optional<T> getById(String id);

    List<T> findAll();

    T save(T entity);

    T update(T entity, String id);

    void delete(String id);
}
