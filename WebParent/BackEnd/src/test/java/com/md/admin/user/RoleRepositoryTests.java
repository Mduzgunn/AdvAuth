package com.md.admin.user;

import com.md.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository repository;

     @Test
    public void testCreateFirstRole(){
         Role roleAdmin = new Role("admin", "manage everything");
         Role savedRole = repository.save(roleAdmin);
         assertThat(savedRole.getId()).isGreaterThan(0);
     }


}
