package com.github.alexivchenko.simplewebappwithci.spring;

import com.github.alexivchenko.simplewebappwithci.model.User;
import com.github.alexivchenko.simplewebappwithci.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Alex Ivchenko
 */
@Component
@Profile("dev")
@Slf4j
public class DatabaseDataSetUp {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DatabaseDataSetUp() {
        log.info("data base set up");
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void setUp() {
        User user = User.builder()
                .username("Username")
                .email("Username@domain.com")
                .firstName("FirstName")
                .lastName("LastName")
                .password(passwordEncoder.encode("password"))
                .build();
        userRepository.save(user);
    }
}
