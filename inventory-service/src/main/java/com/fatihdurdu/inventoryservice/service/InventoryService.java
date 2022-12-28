package com.fatihdurdu.inventoryservice.service;

import com.fatihdurdu.inventoryservice.model.InventoryResponse;
import com.fatihdurdu.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isExist(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream().map(item ->
                        InventoryResponse.builder()
                                .skuCode(item.getSkuCode())
                                .isInStock(item.getQuantity() > 0).build())
                .collect(Collectors.toList());
    }
}
