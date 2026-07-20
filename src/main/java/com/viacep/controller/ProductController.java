package com.viacep.controller;

import com.viacep.dto.ProductResponse;
import com.viacep.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<ProductResponse>> getProductsByLocation(@RequestParam(required = false) String cep,
                                                                      @RequestParam(required = false) String uf,
                                                                      @RequestParam(required = false) String city) {
        List<ProductResponse> products = productService.getProductsByLocation(cep, uf, city);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<ProductResponse>> getNearbyProducts(@RequestParam double latitude,
                                                                  @RequestParam double longitude,
                                                                  @RequestParam String radius) {
        List<ProductResponse> products = productService.getNearbyProducts(latitude, longitude, radius);
        return ResponseEntity.ok(products);
    }
}
