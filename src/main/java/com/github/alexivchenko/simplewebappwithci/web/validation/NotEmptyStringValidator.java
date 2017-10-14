package com.github.alexivchenko.simplewebappwithci.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Alex Ivchenko
 */
public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, Object> {
    @Override
    public void initialize(NotEmptyString notEmptyString) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String str = (String) o;
        return str != null && !str.isEmpty();
    }
}
