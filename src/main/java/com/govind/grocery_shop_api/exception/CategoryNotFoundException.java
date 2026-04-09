package com.govind.grocery_shop_api.exception;

public class CategoryNotFoundException extends RuntimeException{
        public CategoryNotFoundException(){
            super("Category not found");
        }
}
