package com.test.warehouses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class Product {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = "Field name must not be null")
    @NotEmpty(message = "Field name must not be empty")
    private String name;
}
