package com.github.alexivchenko.simplewebappwithci.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Alex Ivchenko
 */
@Entity
public @Data class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
}
