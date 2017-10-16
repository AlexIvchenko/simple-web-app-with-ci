package com.github.alexivchenko.simplewebappwithci.ui;

import com.vaadin.annotations.Title;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;

/**
 * @author Alex Ivchenko
 */
@SpringUI(path = "/main")
@Title("Main")
public class MainUI extends BaseUI {
    private void onProfileClicked() {
        getNavigator().navigateTo("profile");
    }

    @Override
    protected MenuBar initMenu() {
        MenuBar menu = new MenuBar();
        menu.addItem("Profile", e -> onProfileClicked());
        menu.addItem("Logout", e -> logout());
        return menu;
    }

    private void logout() {
        getPage().setLocation("/logout");
    }

    @Override
    protected void defaultNavigate() {
        getNavigator().navigateTo("profile");
    }
}
