package com.github.alexivchenko.simplewebappwithci.repository;

import com.github.alexivchenko.simplewebappwithci.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUser_whenFindByUsername_thenReturnUser() throws Exception {
        User user = User.builder()
                .username("test")
                .password("pass")
                .build();

        testEntityManager.persist(user);
        testEntityManager.flush();

        User found = userRepository.findByUsername(user.getUsername());

        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }
}