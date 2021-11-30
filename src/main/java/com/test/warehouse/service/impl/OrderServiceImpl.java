package com.test.warehouse.service.impl;

import com.test.warehouse.data.LocalDataRepository;
import com.test.warehouse.exception.NotFoundException;
import com.test.warehouse.model.Order;
import com.test.warehouse.model.Warehouse;
import com.test.warehouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final LocalDataRepository localDataRepository;

    @Override
    public Order createOrder(Order order) {
        List<Warehouse> warehousesWithProductFromOrder = localDataRepository.getWarehouses().stream()
                .filter(warehouse -> warehouse.getProducts().contains(order.getProduct()))
                .collect(Collectors.toList());
        if (warehousesWithProductFromOrder.isEmpty()) {
            throw new NotFoundException(String.format("Product with name = %s not found",
                    order.getProduct().getName()));
        }
        Warehouse closestWarehouse = warehousesWithProductFromOrder.get(0);
        double nearestDistance = order.getPoint().distanceSquare(closestWarehouse.getPoint());
        for (int i = 1; i < warehousesWithProductFromOrder.size(); i++) {
            double distance = warehousesWithProductFromOrder.get(i).getPoint().distanceSquare(order.getPoint());
            if (distance < nearestDistance) {
                closestWarehouse = warehousesWithProductFromOrder.get(i);
                nearestDistance = distance;
            }
        }
        closestWarehouse.getProducts().remove(order.getProduct());
        order.setWarehouse(closestWarehouse);
        order.setDistance(Math.sqrt(nearestDistance));
        order.setId(localDataRepository.getOrders().size() + 1L);
        localDataRepository.getOrders().add(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return localDataRepository.getOrders();
    }

    @Override
    public Order findOrderById(Long orderId) {
        return localDataRepository.getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Order with id = %s not found",
                        orderId)));
    }

    @Override
    public void deleteOrderById(Long orderId) {
        localDataRepository.getOrders().remove(findOrderById(orderId));
    }
}
