package com.test.warehouses.controller;

import com.test.warehouses.model.Warehouse;
import com.test.warehouses.service.WarehouseService;
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
@RequestMapping(path = "/api/v1/warehouse")
@Valid
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity(warehouseService.createWarehouse(warehouse), HttpStatus.CREATED);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> findProductById(@PathVariable Long warehouseId) {
        return new ResponseEntity(warehouseService.findWarehouseById(warehouseId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAll() {
        return new ResponseEntity(warehouseService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.updateWarehouse(warehouse), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrderById(@RequestParam Long warehouseId) {
        warehouseService.deleteOrderById(warehouseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/add-product")
    public ResponseEntity<Warehouse> addProductToWarehouse(@RequestParam Long warehouseId, @RequestParam Long productId) {
        return new ResponseEntity<>(warehouseService.addProductToWarehouse(warehouseId, productId), HttpStatus.ACCEPTED);
    }
}
