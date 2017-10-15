package com.github.alexivchenko.simplewebappwithci.ui;

import com.github.alexivchenko.simplewebappwithci.service.UserService;
import com.github.alexivchenko.simplewebappwithci.service.UsernameExistsException;
import com.github.alexivchenko.simplewebappwithci.web.dto.UserDto;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.github.alexivchenko.simplewebappwithci.ui.Messages.message;

/**
 * @author Alex Ivchenko
 */
@SpringComponent
@UIScope
@Slf4j
public final class SignUpBox extends VerticalLayout {
    private UserService userService;

    private TextField username;
    private TextField email;
    private TextField firstName;
    private TextField lastName;
    private PasswordField password;
    private PasswordField confirmPassword;
    private Button signUp;

    private Binder<UserDto> binder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void initialize() {
        config();
        initializeFields();
        initializeSignUpButton();
        initializeBinder();
        applyAllComponents();
        username.focus();
    }

    private void config() {
        setWidth("400px");
        setSpacing(true);
        setMargin(true);
    }

    private void initializeFields() {
        username = new TextField(message("label.user.username"));
        email = new TextField(message("label.user.email"));
        firstName = new TextField(message("label.user.firstName"));
        lastName = new TextField(message("label.user.lastName"));
        password = new PasswordField(message("label.user.password"));
        confirmPassword = new PasswordField(message("label.user.confirmPass"));

    }

    private void initializeSignUpButton() {
        signUp = new Button(message("label.signUp"));
        signUp.setEnabled(false);
        signUp.addClickListener(
                event -> registerNewUser(binder.getBean()));
    }

    private void registerNewUser(UserDto userDto) {
        try {
            userService.signUp(userDto);
        } catch (UsernameExistsException e) {
            log.warn("username " + userDto.getUsername() + " exists");
        }
    }

    private void initializeBinder() {
        binder = new BeanValidationBinder<>(UserDto.class);
        binder.setBean(new UserDto());
        binder.bind(username, "username");
        binder.bind(password, "password");
        binder.bind(firstName, "firstName");
        binder.bind(lastName, "lastName");
        binder.bind(email, "email");
        binder.bind(password, "password");
        binder.bind(confirmPassword, "confirmPassword");
        binder.addStatusChangeListener(event -> signUp.setEnabled(binder.isValid()));
    }

    private void applyAllComponents() {
        addComponents(username, email, firstName, lastName, password, confirmPassword, signUp);
        forEach(component -> component.setWidth("100%"));
    }
}