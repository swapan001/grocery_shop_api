package com.govind.grocery_shop_api.exception;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryException {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseStructure<Category>> handleException(CategoryNotFoundException e ){
        ResponseStructure<Category> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<Category>>(rs,HttpStatus.NOT_FOUND);
    }
}
