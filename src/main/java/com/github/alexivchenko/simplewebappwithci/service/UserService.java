package com.github.alexivchenko.simplewebappwithci.service;

import com.github.alexivchenko.simplewebappwithci.controller.dto.UserDto;
import com.github.alexivchenko.simplewebappwithci.model.User;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    User registerNewUserAccount(UserDto accountDto) throws UsernameExistsException;
}
