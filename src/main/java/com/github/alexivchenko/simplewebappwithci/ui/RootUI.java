package com.github.alexivchenko.simplewebappwithci.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 * @author Alex Ivchenko
 */
@SpringUI(path = "/*")
public class RootUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        getPage().setLocation("/main");
    }
}
