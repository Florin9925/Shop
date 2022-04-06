package com.example.shopbackend.repository;

import com.example.shopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}