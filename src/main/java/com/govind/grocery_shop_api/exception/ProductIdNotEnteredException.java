package com.govind.grocery_shop_api.exception;

public class ProductIdNotEnteredException extends RuntimeException{
    public ProductIdNotEnteredException(){
        super("Product Id Not Entered -_-");
    }
}
