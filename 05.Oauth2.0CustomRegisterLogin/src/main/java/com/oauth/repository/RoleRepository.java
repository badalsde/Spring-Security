package com.oauth.repository;

import com.oauth.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    public Role findByRole(String name);
}
