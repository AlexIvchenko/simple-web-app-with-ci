package com.github.alexivchenko.simplewebappwithci.service;

/**
 * @author Alex Ivchenko
 */
public class UsernameExistsException extends Exception {
    public UsernameExistsException(String message) {
        super(message);
    }
}
