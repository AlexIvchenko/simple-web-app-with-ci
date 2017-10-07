package com.github.alexivchenko.simplewebappwithci.security;

import com.github.alexivchenko.simplewebappwithci.model.User;
import com.github.alexivchenko.simplewebappwithci.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Alex Ivchenko
 */
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public DatabaseUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = repository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return new CustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException(username, e);
        }
    }
}
