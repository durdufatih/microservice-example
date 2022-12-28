package com.fatihdurdu.orderservice.dto;

import com.fatihdurdu.orderservice.model.OrderLineItems;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderRequestDto> itemList;
}
