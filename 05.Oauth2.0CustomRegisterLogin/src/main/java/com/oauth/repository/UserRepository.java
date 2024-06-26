package com.oauth.repository;

import com.oauth.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
   public User findByEmail(String email);
}
