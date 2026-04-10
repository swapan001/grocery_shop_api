package com.govind.grocery_shop_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String phone;
    private String address;
    private String city;
    private String pincode;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
