package com.example.shopbackend.service.user;
import com.example.shopbackend.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    void deleteAll();

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
