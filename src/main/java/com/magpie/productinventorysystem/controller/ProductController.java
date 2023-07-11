package com.magpie.productinventorysystem.controller;


import com.magpie.productinventorysystem.entity.Product;
import com.magpie.productinventorysystem.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productServices.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

    }


    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productServices.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productServices.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
