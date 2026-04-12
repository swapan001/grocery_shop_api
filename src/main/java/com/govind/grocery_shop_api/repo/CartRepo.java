package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.Cart;
import com.govind.grocery_shop_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}