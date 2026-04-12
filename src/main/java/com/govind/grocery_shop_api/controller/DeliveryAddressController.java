package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.DeliveryAddress;
import com.govind.grocery_shop_api.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//update it later
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class DeliveryAddressController {
    private  final DeliveryAddressService deliveryAddressService;
    @PostMapping("/{userId}")
    public ResponseEntity<ResponseStructure<DeliveryAddress>> addDeliveryAddress(@PathVariable Long userId, @RequestBody DeliveryAddress address){
        return deliveryAddressService.addDeliveryAddress(userId,address);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<DeliveryAddress>> getDeliveryAddress(){
        return null;
    }

}
