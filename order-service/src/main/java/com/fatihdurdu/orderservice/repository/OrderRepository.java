package com.fatihdurdu.orderservice.repository;

import com.fatihdurdu.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
