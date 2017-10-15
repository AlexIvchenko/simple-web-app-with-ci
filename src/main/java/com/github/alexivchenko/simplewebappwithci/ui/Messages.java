package com.github.alexivchenko.simplewebappwithci.ui;

import com.vaadin.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Alex Ivchenko
 */
@Component
public class Messages {
    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        Messages.messageSource = messageSource;
    }

    public static void locale(Locale locale) {
        VaadinSession.getCurrent().setLocale(locale);
    }

    public static String message(String key) {
        return messageSource.getMessage(key, null, VaadinSession.getCurrent().getLocale());
    }
}
