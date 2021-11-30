package com.test.warehouses.service;

import com.test.warehouses.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product findProductById(Long productId);

    List<Product> findAll();

    Product updateProduct(Product product);

    void deleteProductById(Long productId);
}
