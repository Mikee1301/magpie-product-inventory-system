package com.magpie.productinventorysystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.magpie.productinventorysystem.entity.Product;
import com.magpie.productinventorysystem.models.ApiResponse;
import com.magpie.productinventorysystem.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;


    @GetMapping
    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {

        String productType = product.getProductType();

        if (!isValidProductType(productType)) {
            String errorMessage = "Invalid product type. Product types are either: food, sports, household, music, electronic, appliance";
            ApiResponse errorResponse = new ApiResponse("failure", errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }


        Product createdProduct = productServices.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

    }


    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productServices.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            String errorMessage = "No such product with id: " + id;
            ApiResponse errorResponse = new ApiResponse("Not Found", errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productServices.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            String errorMessage = "No such product with id: " + id;
            ApiResponse errorResponse = new ApiResponse("Not Found", errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    private boolean isValidProductType(String productType) {
        List<String> allowedTypes = Arrays.asList("food", "sports", "household", "music", "electronic", "appliance");
        return allowedTypes.contains(productType);
    }
}
