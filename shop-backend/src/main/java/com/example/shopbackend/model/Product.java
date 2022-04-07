package com.example.shopbackend.model;

import com.example.shopbackend.model.enums.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 5, max = 250)
    private String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category = Category.OTHER;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToOne
    private com.example.shopbackend.model.CartItem cartItem;

    @OneToOne
    private OrderItem orderItem;
}
