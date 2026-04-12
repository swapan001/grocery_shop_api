package com.govind.grocery_shop_api.exception;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException() {
        super("Cart Item Not Found");
    }
}
