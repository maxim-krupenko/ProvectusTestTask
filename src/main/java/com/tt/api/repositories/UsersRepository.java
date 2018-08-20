package com.tt.api.repositories;

import com.tt.api.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends AbstractRepository<User> {
    Optional<User> findByLogin(String login);
}
