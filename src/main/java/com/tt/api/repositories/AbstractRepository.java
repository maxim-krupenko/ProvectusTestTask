package com.tt.api.repositories;

import com.tt.api.entities.AbstractEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends MongoRepository<T, String> {
}
