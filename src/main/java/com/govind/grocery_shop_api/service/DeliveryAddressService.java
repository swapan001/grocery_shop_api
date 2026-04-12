package com.govind.grocery_shop_api.service;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.DeliveryAddress;
import com.govind.grocery_shop_api.entity.User;
import com.govind.grocery_shop_api.exception.UserNotFoundException;
import com.govind.grocery_shop_api.repo.DeliveryAddressRepo;
import com.govind.grocery_shop_api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepo deliveryAddressRepo;
    private  final UserRepo userRepo;

    public ResponseEntity<ResponseStructure<DeliveryAddress>> addDeliveryAddress(Long userId, DeliveryAddress address) {

        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            address.setUser(user.get());
            ResponseStructure<DeliveryAddress>responseStructure=new ResponseStructure<>();
            responseStructure.setMessage("Delivery Address Added");
            responseStructure.setData(deliveryAddressRepo.save(address));
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
        }else{
               throw new UserNotFoundException();

        }
    }

    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepo.findByUserUserId(userId);
    }

}