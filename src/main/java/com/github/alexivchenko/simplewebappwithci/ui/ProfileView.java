package com.github.alexivchenko.simplewebappwithci.ui;

import com.github.alexivchenko.simplewebappwithci.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Alex Ivchenko
 */
@SpringView(name = "profile")
public final class ProfileView extends VerticalLayout implements View {
    private UserService userService;

    @Autowired
    public void setRecognitionService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initialize() {
        addComponent(new Label("Hello, " + userService.currentUser().getUsername() + "!!"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
