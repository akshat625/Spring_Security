package com.akshat.springt_security.repository;

import com.akshat.springt_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);


}
