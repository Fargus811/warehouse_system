package com.test.warehouse.data;

import com.test.warehouse.model.Order;
import com.test.warehouse.model.Point;
import com.test.warehouse.model.Product;
import com.test.warehouse.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Getter
public class LocalDataRepository {

    private List<Warehouse> warehouses;
    private List<Order> orders;
    private List<Product> products;

    @PostConstruct
    private void postConstruct(){
        Product ps5 = new Product(1L,"PS5");
        Product lgTV= new Product(2L,"LGTV");
        Product samsungTV = new Product(3L,"SAMSUNGTV");
        Product xiaomiTV = new Product(4L,"XIAOMITV");
        Product appleTV = new Product(5L,"APPLETV");
        products = Stream.of(lgTV, appleTV, samsungTV, xiaomiTV, ps5).collect(Collectors.toList());

        Warehouse warehouseMinsk = Warehouse.builder()
                .id(1L)
                .name("Minsk")
                .point(new Point(1L, 1L))
                .products(Stream.of(lgTV, appleTV, ps5, ps5).collect(Collectors.toList()))
                .build();
        Warehouse warehouseBrest = Warehouse.builder()
                .id(2L)
                .name("Brest")
                .point(new Point(22L, 22L))
                .products(Stream.of(appleTV, appleTV,samsungTV).collect(Collectors.toList()))
                .build();
        Warehouse warehouseKiev = Warehouse.builder()
                .id(3L)
                .name("Kiev")
                .point(new Point(33L, 33L))
                .products(Stream.of(lgTV, ps5, ps5, xiaomiTV).collect(Collectors.toList()))
                .build();
        Warehouse warehouseMoscow = Warehouse.builder()
                .id(4L)
                .name("Moscow")
                .point(new Point(44L, 44L))
                .products(Stream.of(lgTV, appleTV, ps5).collect(Collectors.toList()))
                .build();
        Warehouse warehouseKrakow = Warehouse.builder()
                .id(5L)
                .name("Krakow")
                .point(new Point(55L, 55L))
                .products(Stream.of(lgTV, appleTV, ps5, ps5).collect(Collectors.toList()))
                .build();
        warehouses = Stream.of(warehouseMinsk,warehouseBrest,warehouseKiev,warehouseMoscow,warehouseKrakow)
                    .collect(Collectors.toList());
    }
}
