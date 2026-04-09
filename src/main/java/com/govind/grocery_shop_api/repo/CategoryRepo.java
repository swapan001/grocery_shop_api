package com.govind.grocery_shop_api.repo;

import com.govind.grocery_shop_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
