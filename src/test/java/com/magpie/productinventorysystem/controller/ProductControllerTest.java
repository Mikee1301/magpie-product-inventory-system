package com.magpie.productinventorysystem.controller;


import com.magpie.productinventorysystem.entity.Product;
import com.magpie.productinventorysystem.repository.ProductRepository;
import com.magpie.productinventorysystem.services.ProductServices;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create test data
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Pancit Canton", "This is Lucky me spicy pancit canton", "Food", 10.0, 10.0));
        mockProducts.add(new Product(2, "", "Cookies", "This is a chocolate cookies", 10.0, 10.0));

        // Mock repository behavior
        when(productRepository.findAll()).thenReturn(mockProducts);

        // Create an instance of the ProductService
        ProductServices productService = new ProductServices(productRepository);

        // Call the getAllProducts method
        List<Product> result = productService.getAllProducts();

        // Verify repository method was called
        verify(productRepository, times(1)).findAll();

        // Assert the result
        assertEquals(mockProducts.size(), result.size());
        assertEquals(mockProducts.get(0).getProductName(), result.get(0).getProductName());
        assertEquals(mockProducts.get(1).getProductDescription(), result.get(1).getProductDescription());
    }

    @Test
    public void testCreateProduct() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a product instance for testing
        Product newProduct = new Product();
        newProduct.setProductName("Pancit Canton");
        newProduct.setProductDescription("This is Lucky me spicy pancit canton");
        newProduct.setProductType("Food");
        newProduct.setProductQuantity(BigDecimal.valueOf(20.0));
        newProduct.setUnitPrice(BigDecimal.valueOf(20.0));

        // Mock the save method of the repository
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        // Create an instance of the ProductService
        ProductServices productService = new ProductServices(productRepository);

        // Call the createProduct method
        Product createdProduct = productService.createProduct(newProduct);

        // Verify that the repository's save method was called once
        verify(productRepository, times(1)).save(newProduct);

        // Assert that the returned product is not null
        assertNotNull(createdProduct);

        // Add more assertions as needed
        assertEquals(newProduct.getProductName(), createdProduct.getProductName());
        assertEquals(newProduct.getProductDescription(), createdProduct.getProductDescription());
        assertEquals(newProduct.getUnitPrice(), createdProduct.getUnitPrice());
    }

    @Test
    public void testUpdateProduct() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a product instance for testing
        Product existingProduct = new Product();
        existingProduct.setProductId(1);
        existingProduct.setProductName("Pancit Canton existing");
        existingProduct.setProductDescription("This is Lucky me spicy pancit canton");
        existingProduct.setProductType("Food");
        existingProduct.setProductQuantity(BigDecimal.valueOf(20.0));
        existingProduct.setUnitPrice(BigDecimal.valueOf(20.0));

        // Create an updated product instance
        Product updatedProduct = new Product();
        updatedProduct.setProductId(1);
        updatedProduct.setProductName("Pancit Canton updated");
        updatedProduct.setProductDescription("This is Lucky me spicy pancit canton");
        updatedProduct.setProductType("Food");
        updatedProduct.setProductQuantity(BigDecimal.valueOf(30.0));
        updatedProduct.setUnitPrice(BigDecimal.valueOf(20.0));

        // Mock the getProductById and save methods of the repository
        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        // Create an instance of the ProductService
        ProductServices productService = new ProductServices(productRepository);

        // Call the updateProduct method
        Product updatedProductResult = productService.updateProduct(1, updatedProduct);

        // Verify that the repository's findById and save methods were called
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(updatedProduct);

        // Assert that the returned product is not null
        assertNotNull(updatedProductResult);

        // Add more assertions as needed
        assertEquals(updatedProduct.getProductName(), updatedProductResult.getProductName());
        assertEquals(updatedProduct.getProductDescription(), updatedProductResult.getProductDescription());
        assertEquals(updatedProduct.getUnitPrice(), updatedProductResult.getUnitPrice());
    }

    @Test
    public void testGetProductById() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a product instance for testing
        Product existingProduct = new Product();
        existingProduct.setProductId(1);
        existingProduct.setProductName("Pancit Canton");
        existingProduct.setProductDescription("This is Lucky me spicy pancit canton");
        existingProduct.setProductType("Food");
        existingProduct.setProductQuantity(BigDecimal.valueOf(30.0));
        existingProduct.setUnitPrice(BigDecimal.valueOf(20.0));

        // Mock the findById method of the repository
        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));

        // Create an instance of the ProductService
        ProductServices productService = new ProductServices(productRepository);

        // Call the getProductById method
        Product retrievedProduct = productService.getProductById(1);

        // Verify that the repository's findById method was called once
        verify(productRepository, times(1)).findById(1);

        // Assert that the retrieved product is not null and its attributes match the expected values
        assertNotNull(retrievedProduct);
        assertEquals(existingProduct.getProductId(), retrievedProduct.getProductId());
        assertEquals(existingProduct.getProductName(), retrievedProduct.getProductName());
        assertEquals(existingProduct.getProductDescription(), retrievedProduct.getProductDescription());
        assertEquals(existingProduct.getUnitPrice(), retrievedProduct.getUnitPrice());
    }

    @Test
    public void testDeleteProductById() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a product instance for testing
        Product existingProduct = new Product();
        existingProduct.setProductId(1);
        existingProduct.setProductName("Pancit Canton");
        existingProduct.setProductDescription("This is Lucky me spicy pancit canton");
        existingProduct.setProductType("Food");
        existingProduct.setProductQuantity(BigDecimal.valueOf(30.0));
        existingProduct.setUnitPrice(BigDecimal.valueOf(20.0));

        // Mock the findById method of the repository
        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));

        // Create an instance of the ProductService
        ProductServices productService = new ProductServices(productRepository);

        // Call the deleteProductById method
        productService.deleteProduct(1);
    }
}
