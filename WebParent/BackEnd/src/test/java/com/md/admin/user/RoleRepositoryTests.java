package com.md.admin.user;

import com.md.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

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

     @Test
    public void testCreateRoles(){
         Role roleAsistan = new Role("asistan", "intern");
         Role roleEditor= new Role("editor", "junior admin");
         repository.saveAll(List.of(roleAsistan,roleEditor));
     }



}
