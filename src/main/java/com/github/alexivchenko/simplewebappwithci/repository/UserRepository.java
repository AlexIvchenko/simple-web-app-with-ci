package com.github.alexivchenko.simplewebappwithci.repository;

import com.github.alexivchenko.simplewebappwithci.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
