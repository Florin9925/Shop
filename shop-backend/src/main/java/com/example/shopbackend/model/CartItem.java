package com.example.shopbackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long quantity = 0L;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt;

    @NotNull
    @OneToOne(mappedBy = "cartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    @NotNull
    @ManyToOne(optional = false)
    private ShoppingSession shoppingSession;
}
