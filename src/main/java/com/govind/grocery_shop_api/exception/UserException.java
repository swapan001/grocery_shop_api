package com.govind.grocery_shop_api.exception;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserException {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseStructure<User>> handleException(UserNotFoundException e ){
        ResponseStructure<User> rs=new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(e.getMessage());
        rs.setData(null);
        return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.NOT_FOUND);

    }
}
