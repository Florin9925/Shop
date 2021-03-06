package com.example.shopbackend.controller;

import com.example.shopbackend.model.User;
import com.example.shopbackend.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Api(value = "Users Controller", tags = "/users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(Principal principal) {
        log.info(this.getClass().getName()," getAllUsers");
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info(this.getClass().getName()," createUser");
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteAll() {
        log.info(this.getClass().getName()," deleteAll");
        userService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info(this.getClass().getName()," deleteById");
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
