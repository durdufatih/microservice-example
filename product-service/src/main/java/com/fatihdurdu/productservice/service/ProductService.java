package com.fatihdurdu.productservice.service;

import lombok.extern.slf4j.Slf4j;
import com.fatihdurdu.productservice.model.Product;
import com.fatihdurdu.productservice.model.ProductRequest;
import com.fatihdurdu.productservice.model.ProductResponse;
import org.springframework.stereotype.Service;
import com.fatihdurdu.productservice.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void productCreate(ProductRequest request) {
        log.info("Getting object from request : {}", request.toString());
        Product product = Product.builder()
                .description(request.getDescription())
                .price(request.getPrice())
                .name(request.getName()).build();
        productRepository.save(product);

        log.info("Entity is created :{} ",product.toString());

    }

    public List<ProductResponse> getAllProducts() {
        log.info("Starting getting products");
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapProductToResponse).collect(Collectors.toList());
    }

    private ProductResponse mapProductToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
