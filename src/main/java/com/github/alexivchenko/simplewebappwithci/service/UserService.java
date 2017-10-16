package com.github.alexivchenko.simplewebappwithci.service;

import com.github.alexivchenko.simplewebappwithci.web.dto.UserDto;
import com.github.alexivchenko.simplewebappwithci.model.User;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    User currentUser();
    User signUp(UserDto userDto) throws UsernameExistsException;
    void signIn(String username, String password);
}
