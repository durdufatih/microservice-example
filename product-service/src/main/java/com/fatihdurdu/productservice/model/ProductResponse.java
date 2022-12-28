package com.fatihdurdu.productservice.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
