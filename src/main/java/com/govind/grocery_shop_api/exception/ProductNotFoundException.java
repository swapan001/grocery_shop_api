package com.govind.grocery_shop_api.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product Not Found!");
    }
}
