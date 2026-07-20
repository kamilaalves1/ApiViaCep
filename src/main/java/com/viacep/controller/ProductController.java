package com.viacep.controller;

import com.viacep.dto.ProductResponse;
import com.viacep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products/by-location")
    public ResponseEntity<ProductResponse> getProductsByLocation(@RequestParam String cep) {
        ProductResponse productResponse = productService.findProductsByCep(cep);
        return ResponseEntity.ok(productResponse);
    }

    // Outros endpoints podem ser adicionados aqui conforme necessário
}
