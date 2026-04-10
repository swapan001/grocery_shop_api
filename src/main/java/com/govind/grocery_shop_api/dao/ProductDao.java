package com.govind.grocery_shop_api.dao;


import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductDao {
    @Autowired
    private ProductRepo productRepo;

    //create operation
    public Product addProduct(Product product){
        return productRepo.save(product);
    }


    //update operation
    public Product updateProduct(Product product){
        return productRepo.save(product);
    }

    //delete operation
    public void deleteProduct(Long productId){
        productRepo.deleteById(productId);
    }

    //find all product
    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    //find product by ID
    public Optional<Product> findProductById(Long productId){
        return productRepo.findById(productId);
    }
}
