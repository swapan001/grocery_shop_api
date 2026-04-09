package com.govind.grocery_shop_api.dao;

import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CategoryDao {
    @Autowired
    private CategoryRepo categoryRepo;
    public Optional<Category>findCategoryById(Long categoryId){
        return  categoryRepo.findById(categoryId);
    }

}
