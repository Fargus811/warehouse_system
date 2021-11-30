package com.test.warehouses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class Warehouse {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = "Field name must not be null")
    @NotEmpty(message = "Field name must not be empty")
    private String name;

    @JsonProperty
    @NotNull(message = "Field point must not be null")
    private Point point;

    @JsonProperty
    private List<Product> products;
}
