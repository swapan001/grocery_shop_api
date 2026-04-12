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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepo deliveryAddressRepo;
    private  final UserRepo userRepo;

    public ResponseEntity<ResponseStructure<DeliveryAddress>> addDeliveryAddress(DeliveryAddress address) {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        //wow get email from security context holder ^_^

        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            address.setUser(user.get());
            ResponseStructure<DeliveryAddress>responseStructure=new ResponseStructure<>();
            responseStructure.setMessage("Delivery Address Added"+email);
            responseStructure.setData(deliveryAddressRepo.save(address));
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
        }else{
               throw new UserNotFoundException();

        }
    }

    public ResponseEntity<ResponseStructure<List<DeliveryAddress>>> getAllDeliveryAddressOfUser() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        //After Authenticate we get detail of user like email so user should not null

        Long userId=userRepo.findByEmail(email).get().getUserId();
        ResponseStructure<List<DeliveryAddress>> responseStructure=new ResponseStructure<>();
        responseStructure.setData(deliveryAddressRepo.findByUserUserId(userId));    //findAllByUserId
        responseStructure.setMessage("All Delivery Address");
        responseStructure.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseStructure,HttpStatus.OK);

    }

}