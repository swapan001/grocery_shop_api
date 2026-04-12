package com.govind.grocery_shop_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure <T> {
    private int statusCode;
    private String message;
    private T data;
}
