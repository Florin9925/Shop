package com.example.shopbackend.mocks;

import com.example.shopbackend.model.User;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)

public class UserMock {
    public static User getMockUserAdmin(){
        return User.builder()
                .username("admin")
                .password("1q2w3e")
                .address("Brasov")
                .createdAt(LocalDateTime.now())
                .id(1L)
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@admin.com")
                .build();
    }

    public static User getMockUserBasic(){
        return User.builder()
                .username("user")
                .password("1q2w3e")
                .address("Brasov")
                .createdAt(LocalDateTime.now())
                .id(2L)
                .firstName("User")
                .lastName("User")
                .email("user@user.com")
                .build();
    }
}
