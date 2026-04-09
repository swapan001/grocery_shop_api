package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepo repo;

    @PostMapping
    public Category create(@RequestBody Category c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Category> getAll() {
        return repo.findAll();
    }
}
