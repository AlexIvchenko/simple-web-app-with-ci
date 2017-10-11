package com.github.alexivchenko.simplewebappwithci.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Alex Ivchenko
 */
@Entity
@Builder
@NoArgsConstructor
public @Data class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
