package com.test.warehouses.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestErrorMessage {

    private String message;
    private List<String> errors;

}
