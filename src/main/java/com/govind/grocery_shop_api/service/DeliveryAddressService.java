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
import java.util.Objects;
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


    //next update check if this address assign with any active order then it should not be change until order cancel or delivery
    public ResponseEntity<ResponseStructure<DeliveryAddress>> updateDeliveryAddress(Long deliveryAddressId, DeliveryAddress updatedAddress) {

        // 1. Get logged-in user (JWT)
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Fetch existing address
        DeliveryAddress existingAddress = deliveryAddressRepo.findById(deliveryAddressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // 3. SECURITY CHECK
        if (!existingAddress.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("You are not allowed to update this address");
        }

        // 4. Update fields (DO NOT replace object)
        existingAddress.setReceiverName(updatedAddress.getReceiverName());
        existingAddress.setPhone(updatedAddress.getPhone());
        existingAddress.setAddressLine(updatedAddress.getAddressLine());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setPincode(updatedAddress.getPincode());

        // 5. Save updated entity
        DeliveryAddress saved = deliveryAddressRepo.save(existingAddress);

        // 6. Response
        ResponseStructure<DeliveryAddress> response = new ResponseStructure<>();
        response.setData(saved);
        response.setMessage("Delivery Address Updated Successfully");
        response.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }  // next update handle exception in proper way
}