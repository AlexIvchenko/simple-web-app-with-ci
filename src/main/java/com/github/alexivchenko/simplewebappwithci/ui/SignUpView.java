package com.github.alexivchenko.simplewebappwithci.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Alex Ivchenko
 */
@SpringView(name = "signUp")
@Theme("valo")
public final class SignUpView extends VerticalLayout implements View {
    @Resource
    private SignUpBox signUpBox;

    @PostConstruct
    private void createContent() {
        setSizeFull();
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponent(signUpBox);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
