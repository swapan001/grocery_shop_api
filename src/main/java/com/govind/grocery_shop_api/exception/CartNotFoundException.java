package com.govind.grocery_shop_api.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("Cart Not Found");
    }
}
