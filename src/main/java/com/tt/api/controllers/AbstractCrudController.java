package com.tt.api.controllers;

import com.tt.api.entities.AbstractEntity;
import com.tt.api.services.CrudService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

// I don't use DTO to avoid more code(redundant for test task)
public abstract class AbstractCrudController<T extends AbstractEntity> {

    // to make ability inject mocks in unit tests injection is done using setter
    @Setter(onMethod = @__({@Autowired}))
    protected CrudService<T> crudService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        return crudService
                .getById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @GetMapping
    public List<T> findAll() {
        return crudService.findAll();
    }

    @PostMapping
    public T save(@RequestBody T entity) {
        return crudService.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@RequestBody T entity, @PathVariable String id) {
        return crudService.update(entity, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        crudService.delete(id);
    }

}
