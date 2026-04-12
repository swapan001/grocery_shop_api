package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.Cart;
import com.govind.grocery_shop_api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCart(Cart cart);
}
