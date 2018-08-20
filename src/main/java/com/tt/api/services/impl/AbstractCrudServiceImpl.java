package com.tt.api.services.impl;

import com.tt.api.entities.AbstractEntity;
import com.tt.api.repositories.AbstractRepository;
import com.tt.api.services.CrudService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudServiceImpl<T extends AbstractEntity> implements CrudService<T> {

    @Setter(onMethod = @__({@Autowired}))
    protected AbstractRepository<T> repository;

    @Override
    public Optional<T> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity, String id) {
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
