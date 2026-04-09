package com.govind.grocery_shop_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long productId;

    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productStock;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnore
    private Category category;
}
