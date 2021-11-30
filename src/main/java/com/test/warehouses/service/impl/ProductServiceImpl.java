package com.test.warehouses.service.impl;

import com.test.warehouses.data.LocalDataRepository;
import com.test.warehouses.exception.NotFoundException;
import com.test.warehouses.model.Product;
import com.test.warehouses.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final LocalDataRepository localDataRepository;

    @Override
    public Product createProduct(Product product) {
        product.setId(localDataRepository.getProducts().size() + 1L);
        localDataRepository.getProducts().add(product);
        return product;
    }

    @Override
    public Product findProductById(Long productId) {
        return localDataRepository.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id = %s not found",
                        productId)));
    }

    @Override
    public Product updateProduct(Product product) {
        Product productFromRepository = findProductById(product.getId());
        localDataRepository.getProducts().remove(productFromRepository);
        productFromRepository.setName(product.getName());
        localDataRepository.getProducts().add(productFromRepository);
        localDataRepository.getWarehouses().forEach(warehouse ->
                warehouse.getProducts().forEach(productInWarehouse -> product.setName(product.getName())));
        return productFromRepository;
    }

    @Override
    public void deleteProductById(Long productId) {
        Product productFromRepository = findProductById(productId);
        localDataRepository.getProducts().remove(productFromRepository);
        localDataRepository.getWarehouses().forEach(warehouse ->
                warehouse.getProducts().remove(productFromRepository));
    }

    @Override
    public List<Product> findAll() {
        return localDataRepository.getProducts();
    }
}
