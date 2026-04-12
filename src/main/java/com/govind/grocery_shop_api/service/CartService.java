package com.govind.grocery_shop_api.service;
import com.govind.grocery_shop_api.dao.ProductDao;
import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Cart;
import com.govind.grocery_shop_api.entity.CartItem;
import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.entity.User;
import com.govind.grocery_shop_api.exception.CartItemNotFoundException;
import com.govind.grocery_shop_api.exception.CartNotFoundException;
import com.govind.grocery_shop_api.exception.InvalidQuantityException;
import com.govind.grocery_shop_api.exception.ProductNotFoundException;
import com.govind.grocery_shop_api.repo.CartItemRepo;
import com.govind.grocery_shop_api.repo.CartRepo;
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
public class CartService {
    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final UserRepo userRepo;
    private final ProductDao productDao;



    public Cart getOrCreateCart(User user) {
        return cartRepo.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepo.save(cart);
                });
    }

    public ResponseEntity<ResponseStructure<CartItem>> addToCart(Long productId, int quantity) {
        if(quantity <= 0){
            throw new InvalidQuantityException();

        }
        String email = SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getName();

        User user=userRepo.findByEmail(email).get();
        Optional<Product> product = productDao.findProductById(productId);
        if(product.isPresent()){
            Cart cart = getOrCreateCart(user);
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product.get());
            item.setQuantity(quantity);
            cartItemRepo.save(item);

            ResponseStructure<CartItem>responseStructure=new ResponseStructure<>();
            responseStructure.setData(item);
            responseStructure.setMessage("Item added to cart");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }else {
            throw new ProductNotFoundException();
        }
    }

    public ResponseEntity<ResponseStructure<List<CartItem>>> getAllCartItem(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user=userRepo.findByEmail(email).get();
        Optional<Cart> cart=cartRepo.findByUser(user);
        if(cart.isEmpty()){
            throw new CartNotFoundException();
        }

        ResponseStructure<List<CartItem>>responseStructure=new ResponseStructure<>();
        responseStructure.setData(cartItemRepo.findByCart(cart.get()));
        responseStructure.setMessage("Cart data");
        responseStructure.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> removeItemFromCart(Long cartItemId) {
        if(!cartItemRepo.existsById(cartItemId)){
            throw new CartItemNotFoundException();
        }
        cartItemRepo.deleteById(cartItemId);
        ResponseStructure<String>responseStructure=new ResponseStructure<>();
        responseStructure.setData("removed cart Item : id-> "+cartItemId);
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("removed data");
        return ResponseEntity.ok().body(responseStructure);
    }


    //cart total calculator

    public ResponseEntity<ResponseStructure<Double>> getCartTotal() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user=userRepo.findByEmail(email).get();
        Optional<Cart> cart=cartRepo.findByUser(user);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found for user");
        }
        List<CartItem> items = cartItemRepo.findByCart(cart.get());

        double total = 0;

        for (CartItem item : items) {
            double price = item.getProduct().getProductPrice();
            total += price * item.getQuantity();
        }

        return ResponseEntity.ok()
                .body(ResponseStructure.<Double>builder()
                        .data(total)
                        .message("Cart total calculated successfully")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
