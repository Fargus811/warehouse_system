package com.test.warehouse.service;

import com.test.warehouse.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse createWarehouse(Warehouse warehouse);

    Warehouse findWarehouseById(Long warehouseId);

    List<Warehouse> findAll();

    Warehouse updateWarehouse(Warehouse warehouse);

    void deleteOrderById(Long warehouseId);

    Warehouse addProductToWarehouse(Long warehouseId, Long productId);
}
