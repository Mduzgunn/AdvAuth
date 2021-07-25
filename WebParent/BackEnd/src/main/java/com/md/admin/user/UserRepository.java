package com.md.admin.user;

import com.md.common.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u WHERE  u.email = :email")
    public User getUserByEmail(@Param("email") String email);
}
