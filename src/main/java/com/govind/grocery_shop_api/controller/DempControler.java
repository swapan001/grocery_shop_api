package com.govind.grocery_shop_api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DempControler {

    @GetMapping("/")
    public String hello(){
        return "Welcome";
    }



}
