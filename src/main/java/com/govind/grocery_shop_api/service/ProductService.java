package com.govind.grocery_shop_api.service;

import com.govind.grocery_shop_api.dao.CategoryDao;
import com.govind.grocery_shop_api.dao.ProductDao;
import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.exception.CategoryNotFoundException;
import com.govind.grocery_shop_api.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    public Product findProductById(Long productId){
        Optional<Product>product=productDao.findProductById(productId);
        if(product.isPresent()){
            return product.get();
        }else {
            throw new ProductNotFoundException();
        }
    }
    public Product addProduct(Product product,Long categoryId){
        Optional<Category>category=categoryDao.findCategoryById(categoryId);
        if(category.isPresent()){
            product.setCategory(category.get());
            productDao.addProduct(product);
            return product;
        }else{
            System.out.println("Category not found");
            throw new CategoryNotFoundException();
        }
    }

    public List<Product> getAllProduct(){
       return productDao.getAllProduct();
    }

    public void deleteProductById(Long productId){
        Optional<Product>product=productDao.findProductById(productId);
        if(product.isPresent()){
            productDao.deleteProduct(productId);
        }
        else {
            System.out.println("Product not found");
            throw new ProductNotFoundException();
        }
    }

    public Product updateProduct(Product product,Long categoryId){
        Optional<Product>product1=productDao.findProductById(product.getProductId());
        Optional<Category>category=categoryDao.findCategoryById(categoryId);
        if(product1.isPresent() && category.isPresent()){
            product.setCategory(category.get());
            productDao.updateProduct(product);
            return product;
        }else {
            if(product1.isEmpty()){
                System.out.println("Product not found");
                throw new ProductNotFoundException();
            }else {
                System.out.println("Category not found");
                throw new CategoryNotFoundException();
            }
        }
    }

}
