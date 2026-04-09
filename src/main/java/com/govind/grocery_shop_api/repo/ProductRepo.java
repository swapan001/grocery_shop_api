package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
