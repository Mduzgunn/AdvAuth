package com.md.admin.user;

import com.md.common.entity.Role;
import com.md.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        Role roleAdmin  = entityManager.find(Role.class, 2);
        User Mdzgn = new User("Mdzgn@test.com", "şifre", "melih", "düzgün");
        Mdzgn.addRole(roleAdmin);

        User savedUser = repository.save(Mdzgn);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
