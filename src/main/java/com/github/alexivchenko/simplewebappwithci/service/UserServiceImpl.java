package com.github.alexivchenko.simplewebappwithci.service;

import com.github.alexivchenko.simplewebappwithci.web.dto.UserDto;
import com.github.alexivchenko.simplewebappwithci.model.User;
import com.github.alexivchenko.simplewebappwithci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto account) throws UsernameExistsException {
        if (usernameExist(account.getEmail())) {
            throw new UsernameExistsException("There is an account with that email address: " + account.getEmail());
        }
        User user = User.builder()
                .username(account.getUsername())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .password(passwordEncoder.encode(account.getPassword()))
                .build();
        return repository.save(user);
    }

    private boolean usernameExist(String username) {
        User user = repository.findByUsername(username);
        return user != null;
    }
}
