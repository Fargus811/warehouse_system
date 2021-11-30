package com.test.warehouse.service.impl;

import com.test.warehouse.data.LocalDataRepository;
import com.test.warehouse.exception.NotFoundException;
import com.test.warehouse.model.Product;
import com.test.warehouse.model.Warehouse;
import com.test.warehouse.service.ProductService;
import com.test.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final LocalDataRepository localDataRepository;
    private final ProductService productService;

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        warehouse.setId(localDataRepository.getWarehouses().size() + 1L);
        localDataRepository.getWarehouses().add(warehouse);
        return warehouse;
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        Warehouse warehouseEntity = localDataRepository.getWarehouses().stream()
                .filter(warehouseFromData -> warehouseFromData.getId().equals(warehouse.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id = %s not found",
                        warehouse.getId())));
        localDataRepository.getWarehouses().remove(warehouseEntity);
        localDataRepository.getWarehouses().add(warehouse);
        return warehouse;
    }

    @Override
    public Warehouse findWarehouseById(Long warehouseId) {
        return localDataRepository.getWarehouses().stream()
                .filter(warehouse -> warehouse.getId().equals(warehouseId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id = %s not found",
                        warehouseId)));
    }

    @Override
    public List<Warehouse> findAll() {
        return localDataRepository.getWarehouses();
    }

    @Override
    public void deleteOrderById(Long warehouseId) {
        localDataRepository.getWarehouses().remove(findWarehouseById(warehouseId));
    }

    @Override
    public Warehouse addProductToWarehouse(Long warehouseId, Long productId) {
        Product product = productService.findProductById(productId);
        localDataRepository.getWarehouses().stream()
                .filter(warehouse -> warehouse.getId().equals(warehouseId))
                .forEach(warehouse -> warehouse.getProducts().add(product));
        return findWarehouseById(warehouseId);
    }
}
