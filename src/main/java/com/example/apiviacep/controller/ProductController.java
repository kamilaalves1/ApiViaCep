package com.example.apiviacep.controller;

import com.example.apiviacep.model.ProductResponse;
import com.example.apiviacep.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products/by-location")
    public ResponseEntity<ProductResponse> getProductsByLocation(@RequestParam(required = false) String cep,
                                                                  @RequestParam(required = false) String uf,
                                                                  @RequestParam(required = false) String city) {
        ProductResponse products = productService.findProductsByLocation(cep, uf, city);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/api/products/nearby")
    public ResponseEntity<ProductResponse> getNearbyProducts(@RequestParam double latitude,
                                                             @RequestParam double longitude,
                                                             @RequestParam String radius) {
        ProductResponse products = productService.findNearbyProducts(latitude, longitude, radius);
        return ResponseEntity.ok(products);
    }
}
