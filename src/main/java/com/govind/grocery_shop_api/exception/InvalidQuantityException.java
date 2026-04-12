package com.govind.grocery_shop_api.exception;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException() {
        super("In valid quantity , quantity should greater than Zero");
    }
}
