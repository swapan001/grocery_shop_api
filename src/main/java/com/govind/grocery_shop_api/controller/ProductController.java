package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.dto.ResponseStructure;
import com.govind.grocery_shop_api.entity.Category;
import com.govind.grocery_shop_api.entity.Product;
import com.govind.grocery_shop_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    final private ProductService productService;

    //get productById
    @GetMapping("/{productId}")
    public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable long productId){
       return productService.findProductById(productId);
    }

    //Add new Product
    @PostMapping("/{categoryId}")
    public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product, @PathVariable long categoryId){
        return productService.addProduct(product,categoryId);
    }


    // get all Product
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
        return productService.findAllProducts();
    }

    //Delete product

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    //update product

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseStructure<Product> > updateProduct(@RequestBody Product product,@PathVariable long categoryId){
        return productService.updateProduct(product,categoryId);
    }
}
