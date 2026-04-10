package com.govind.grocery_shop_api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
