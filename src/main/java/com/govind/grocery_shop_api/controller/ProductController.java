package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    final private ProductService productService;

    //get productById
    @GetMapping("/{productId}")
    public Product findProductById(@PathVariable long productId){
       return productService.findProductById(productId);
    }

    //Add new Product
    @PostMapping("/{categoryId}")
    public Product addProduct(@RequestBody Product product, @PathVariable long categoryId){
        return productService.addProduct(product,categoryId);
    }


    // get all Product
    @GetMapping("/")
    public List<Product> allProduct(){
        return productService.getAllProduct();
    }

    //Delete product

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable long productId){
        productService.deleteProductById(productId);
    }

    //update product

    @PutMapping("/{categoryId}")
    public Product updateProduct(@RequestBody Product product,@PathVariable long categoryId){
        return productService.updateProduct(product,categoryId);
    }
}
