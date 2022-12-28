package com.fatihdurdu.productservice.repository;

import com.fatihdurdu.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
