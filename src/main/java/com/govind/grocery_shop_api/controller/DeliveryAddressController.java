package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.DeliveryAddress;
import com.govind.grocery_shop_api.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//update it later
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class DeliveryAddressController {
    private  final DeliveryAddressService deliveryAddressService;
    @PostMapping
    public ResponseEntity<ResponseStructure<DeliveryAddress>> addDeliveryAddress(@RequestBody DeliveryAddress address){
        return deliveryAddressService.addDeliveryAddress(address);  //used token for userId
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<DeliveryAddress>>> getDeliveryAddress(){
       return deliveryAddressService.getAllDeliveryAddressOfUser();
    }

}
