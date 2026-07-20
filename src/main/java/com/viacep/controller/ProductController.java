package com.viacep.controller;

import com.viacep.model.ProductResponse;
import com.viacep.service.ProductService;
import com.viacep.exception.InvalidCepException;
import com.viacep.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/by-location")
    public ResponseEntity<?> getProductsByCep(@RequestParam String cep) {
        if (!isCepValid(cep)) {
            throw new InvalidCepException("Formato de CEP inválido");
        }
        var products = productService.findProductsByCep(cep);
        
        if (products.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado para o CEP informado.");
        }
        
        return ResponseEntity.ok(products);
    }

    private boolean isCepValid(String cep) {
        return cep.matches("\\d{5}-\\d{3}");
    }
}
