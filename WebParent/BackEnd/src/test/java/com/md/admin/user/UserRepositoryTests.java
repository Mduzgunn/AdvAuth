package com.md.admin.user;

import com.md.common.entity.Role;
import com.md.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole(){
        Role roleAdmin  = entityManager.find(Role.class, 2);
        User Mdzgn = new User("Mdzgn@test.com", "şifre", "melih", "düzgün");
        Mdzgn.addRole(roleAdmin);

        User savedUser = repository.save(Mdzgn);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithManyRole(){
        User Mdzgn = new User("Mdzgn@test2gmail.com", "şifre2", "ahmet", "düzgün");
        Role roleEditor = new Role(2);
        Role roleAsistan = new Role(5);

        Mdzgn.addRole(roleEditor);
        Mdzgn.addRole(roleAsistan);

        User savedUser = repository.save(Mdzgn);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers = repository.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void getUserById(){
        User userMd = repository.findById(1).get();
        System.out.println(userMd);
        assertThat(userMd).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User userMd = repository.findById(1).get();
        userMd.setEnabled(true);
        userMd.setEmail("guncel@gmail.com");

        repository.save(userMd);
    }

    @Test
    public void testUpdateUserRoles(){
        User userMd = repository.findById(10).get();
        Role roleAsistan = new Role(5);
        Role roleAdmin= new Role(2);

        userMd.getRoles().remove(roleAsistan);
        userMd.addRole(roleAdmin);

        repository.save(userMd);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 7;
        repository.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail(){
        String email="abc@def.com";
        User user = repository.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
    @Test
    public void testCountById(){
        Integer id = 1;
        Long countById=repository.countById(id);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Integer id = 14;
        repository.updateEnabledStatus(id, false);
    }

    @Test
    public void testEnableUser(){
        Integer id = 14;
        repository.updateEnabledStatus(id, true);
    }
    @Test
    public void testListFirstPage() {
        int pageNumber = 1;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = repository.findAll(pageable);

        List<User> listUsers = page.getContent();

        listUsers.forEach(user -> System.out.println(user));

        assertThat(listUsers.size()).isEqualTo(pageSize);
    }
    @Test
    public void testShortUsers(){
        String keyword = "bruce";

        int pageNumber = 0;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = repository.findAll(keyword,pageable);

        List<User> listUsers = page.getContent();

        listUsers.forEach(user -> System.out.println(user));

        assertThat(listUsers.size()).isGreaterThan(0);

    }
}
