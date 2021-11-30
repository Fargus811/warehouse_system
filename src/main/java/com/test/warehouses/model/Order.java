package com.test.warehouses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Order {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = "Field userId must not be null")
    private Long userId;

    @JsonProperty
    @NotNull(message = "Field point must not be null")
    private Point point;

    @JsonProperty
    @NotNull(message = "Field product must not be null")
    private Product product;

    @JsonProperty
    private Warehouse warehouse;

    @JsonProperty
    private Double distance;
}
