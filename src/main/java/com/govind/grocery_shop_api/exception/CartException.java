package com.govind.grocery_shop_api.exception;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.CartItem;
import com.govind.grocery_shop_api.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CartException {
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ResponseStructure<CartItem>> handleException(InvalidQuantityException e ){
        ResponseStructure<CartItem> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<CartItem>>(rs,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseStructure<CartItem>> handleException(ProductNotFoundException e ){
        ResponseStructure<CartItem> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<CartItem>>(rs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ResponseStructure<List<CartItem>>> handleException(CartNotFoundException e ){
        ResponseStructure<List<CartItem>> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<List<CartItem>>>(rs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleException(CartItemNotFoundException e ){
        ResponseStructure<String> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
    }


}
