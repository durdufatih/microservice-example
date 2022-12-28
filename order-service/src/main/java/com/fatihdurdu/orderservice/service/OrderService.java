package com.fatihdurdu.orderservice.service;

import com.fatihdurdu.orderservice.dto.InventoryResponse;
import com.fatihdurdu.orderservice.dto.OrderRequest;
import com.fatihdurdu.orderservice.dto.OrderRequestDto;
import com.fatihdurdu.orderservice.model.Order;
import com.fatihdurdu.orderservice.model.OrderLineItems;
import com.fatihdurdu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> itemsList = orderRequest.getItemList().stream().map(orderLineItems -> mapToDo(orderLineItems)).collect(Collectors.toList());
        order.setOrderLineItemsList(itemsList);

        List<String> skuList = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://inventory-service/api/inventory")
                .queryParam("skuCode", skuList)
                .encode()
                .toUriString();

        InventoryResponse[] responseList = restTemplate.getForEntity(urlTemplate, InventoryResponse[].class, orderRequest.getItemList()).getBody();


        boolean existAllProduct = Arrays.stream(responseList).allMatch(InventoryResponse::isInStock);
        if (existAllProduct) {
            orderRepository.save(order);
            log.info("Item Created: {}", order.toString());
        } else {
            throw new IllegalArgumentException("Item not found in inventory");
        }

    }

    private OrderLineItems mapToDo(OrderRequestDto orderLineItems) {
        OrderLineItems orderLine = new OrderLineItems();
        orderLine.setPrice(orderLineItems.getPrice());
        orderLine.setQuantity(orderLineItems.getQuantity());
        orderLine.setSkuCode(orderLineItems.getSkuCode());
        return orderLine;
    }
}
