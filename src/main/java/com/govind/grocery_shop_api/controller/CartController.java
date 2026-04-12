package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.CartItem;
import com.govind.grocery_shop_api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/{productId}/{qty}")
    public ResponseEntity<ResponseStructure<CartItem>> addToCart(@PathVariable Long productId, @PathVariable int qty) {
        return cartService.addToCart(productId, qty);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<CartItem>>>getCart() {
        return cartService.getAllCartItem();
    }


    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ResponseStructure<String>> removeItemFromCart(@PathVariable Long cartItemId) {
        return cartService.removeItemFromCart(cartItemId);
    }

}
