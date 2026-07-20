package com.viacep.controller;

import com.viacep.service.ProductService;
import com.viacep.exception.InvalidCepException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/by-location")
    public ResponseEntity<ProductResponse> getProductsByLocation(@RequestParam String cep) {
        String cepDigits = cep.replaceAll("-", "");
        if (!cepDigits.matches("\\d{8}")) {
            throw new InvalidCepException("CEP inválido: " + cep);
        }
        return ResponseEntity.ok(productService.findProductsByCep(cep));
    }
}
