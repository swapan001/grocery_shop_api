package com.govind.grocery_shop_api.service;

import com.govind.grocery_shop_api.dao.CategoryDao;
import com.govind.grocery_shop_api.dao.ProductDao;
import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.exception.CategoryNotFoundException;
import com.govind.grocery_shop_api.exception.ProductIdNotEnteredException;
import com.govind.grocery_shop_api.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    public ResponseEntity<ResponseStructure<Product>> findProductById(Long productId){
        Optional<Product>product=productDao.findProductById(productId);
        if(product.isPresent()){
           ResponseStructure<Product>responseStructure=new ResponseStructure<>();
           responseStructure.setData(product.get());
           responseStructure.setStatusCode(HttpStatus.OK.value());
           responseStructure.setMessage("Product Found");

           return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }else {
            throw new ProductNotFoundException();
        }
    }
    public ResponseEntity<ResponseStructure<Product>> addProduct(Product product,Long categoryId){
        Optional<Category>category=categoryDao.findCategoryById(categoryId);
        if(category.isPresent()){
            product.setCategory(category.get());
            ResponseStructure<Product>responseStructure=new ResponseStructure<>();
            responseStructure.setData(productDao.addProduct(product));  //product add
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Product Added");

            return new ResponseEntity<>(responseStructure, HttpStatus.OK);

        }else{
            System.out.println("Category not found");
            throw new CategoryNotFoundException();
        }
    }

    public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
        ResponseStructure<List<Product>>responseStructure=new ResponseStructure<>();
        responseStructure.setData(productDao.getAllProduct());
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("All Products Found");

        return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    }


    public ResponseEntity<ResponseStructure<Product> > deleteProductById(Long productId){
        Optional<Product>product=productDao.findProductById(productId);
        if(product.isPresent()){
            productDao.deleteProduct(productId);

            ResponseStructure<Product>responseStructure=new ResponseStructure<>();
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Product Deleted successfully");

            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
        else {
            System.out.println("Product not found");
            throw new ProductNotFoundException();
        }
    }

    public ResponseEntity<ResponseStructure<Product> > updateProduct(Product product,Long categoryId){
        if(product.getProductId()==null){
            throw new ProductIdNotEnteredException();
        }
        Optional<Product>product1=productDao.findProductById(product.getProductId());
        Optional<Category>category=categoryDao.findCategoryById(categoryId);
        if(product1.isPresent() && category.isPresent()){
            product.setCategory(category.get());
            productDao.updateProduct(product);
            ResponseStructure<Product>responseStructure=new ResponseStructure<>();
            responseStructure.setData(product);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Product Updated successfully");
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
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
