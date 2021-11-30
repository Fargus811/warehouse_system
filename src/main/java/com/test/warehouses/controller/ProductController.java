package com.test.warehouses.controller;

import com.test.warehouses.model.Order;
import com.test.warehouses.model.Product;
import com.test.warehouses.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/product")
@Valid
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Order> findProductById(@PathVariable Long productId) {
        return new ResponseEntity(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity(productService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity(productService.updateProduct(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
