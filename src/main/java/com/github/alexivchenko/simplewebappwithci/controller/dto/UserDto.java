package com.github.alexivchenko.simplewebappwithci.controller.dto;

import com.github.alexivchenko.simplewebappwithci.controller.validation.NotEmptyString;
import com.github.alexivchenko.simplewebappwithci.controller.validation.PasswordMatches;
import com.github.alexivchenko.simplewebappwithci.controller.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alex Ivchenko
 */
@PasswordMatches
public @Data class UserDto {
    @NotNull
    @NotEmptyString
    @Size(min = 6, max = 30, message = "{NotEmpty.user.username}")
    private String username;

    @NotNull
    @NotEmptyString
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String firstName;

    @NotNull
    @NotEmptyString
    @ValidEmail
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String lastName;

    @NotNull
    @NotEmptyString(message = "{NotEmpty.user.password}")
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String password;

    @Size(min = 6, max = 30, message = "{validation.error.size}")
    @NotEmptyString
    private String matchingPassword;

    @NotNull
    @NotEmptyString
    private String email;
}
