package com.github.alexivchenko.simplewebappwithci.security;

import com.github.alexivchenko.simplewebappwithci.model.User;
import com.github.alexivchenko.simplewebappwithci.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class DatabaseUserDetailsServiceTest {
    @TestConfiguration
    public static class DatabaseUserDetailsServiceTestConfiguration {
        @Bean
        public UserDetailsService userService() {
            return new DatabaseUserDetailsService();
        }
    }

    @Autowired
    private UserDetailsService detailsService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        User user = User.builder()
                .username("test")
                .password("pass")
                .build();
        Mockito.when(userRepository.findByUsername("test")).thenReturn(user);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() throws Exception {
        UserDetails found = detailsService.loadUserByUsername("test");
        assertThat(found.getUsername()).isEqualTo("test");
    }
}