package com.fatihdurdu.productservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
