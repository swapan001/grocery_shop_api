package com.govind.grocery_shop_api.exception;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ProductException {
        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ResponseStructure<Product>> handleException(ProductNotFoundException e ){
            ResponseStructure<Product> rs=new ResponseStructure<>();
            rs.setStatusCode(HttpStatus.NOT_FOUND.value());
            rs.setMessage(e.getMessage());
            rs.setData(null);
            return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.NOT_FOUND);

    }

        @ExceptionHandler(ProductIdNotEnteredException.class)
        public ResponseEntity<ResponseStructure<Product>> handleException(ProductIdNotEnteredException e ){
            ResponseStructure<Product> rs=new ResponseStructure<>();
            rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
            rs.setMessage(e.getMessage());
            rs.setData(null);
            return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.BAD_REQUEST);
        }
}
