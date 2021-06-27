package com.md.admin.user;

import com.md.common.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
