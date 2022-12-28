package com.fatihdurdu.productservice.controller;

import com.fatihdurdu.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import com.fatihdurdu.productservice.model.ProductRequest;
import com.fatihdurdu.productservice.model.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductRequest productRequest) {
        productService.productCreate(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getList(){
        return productService.getAllProducts();
    }
}
