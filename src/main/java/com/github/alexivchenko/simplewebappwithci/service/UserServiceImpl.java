package com.github.alexivchenko.simplewebappwithci.service;

import com.github.alexivchenko.simplewebappwithci.model.User;
import com.github.alexivchenko.simplewebappwithci.repository.UserRepository;
import com.github.alexivchenko.simplewebappwithci.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Alex Ivchenko
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setDaoAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || (auth instanceof AnonymousAuthenticationToken)) {
            return null;
        }
        return repository.findByUsername(((UserDetails) auth.getPrincipal()).getUsername());
    }

    @Transactional
    @Override
    public User signUp(UserDto userDto) throws UsernameExistsException {
        if (usernameExist(userDto.getEmail())) {
            throw new UsernameExistsException("There is an account with that email address: " + userDto.getEmail());
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        return repository.save(user);
    }

    @Transactional
    @Override
    public void signIn(String username, String password) {
        log.info("signing in: " + username);
        log.info("db user: " + repository.findByUsername(username));
        AbstractAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean usernameExist(String username) {
        User user = repository.findByUsername(username);
        return user != null;
    }
}
