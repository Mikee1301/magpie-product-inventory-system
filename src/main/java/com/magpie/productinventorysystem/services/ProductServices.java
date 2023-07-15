package com.magpie.productinventorysystem.services;

import com.magpie.productinventorysystem.entity.Product;
import com.magpie.productinventorysystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setProductDescription(updatedProduct.getProductDescription());
            product.setProductType(updatedProduct.getProductType());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            product.setUnitPrice(updatedProduct.getUnitPrice());
            return productRepository.save(product);
        }
        return null;
    }
    public void deleteProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = getProductById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(product);
        }
    }

}
