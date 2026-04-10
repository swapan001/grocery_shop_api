package com.govind.grocery_shop_api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
