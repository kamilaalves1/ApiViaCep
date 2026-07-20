package com.viacep.controller;

import com.viacep.dto.ProductResponse;
import com.viacep.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products/by-location")
    public ResponseEntity<?> getProductsByLocation(@RequestParam String cep) {
        try {
            return ResponseEntity.ok(productService.findProductsByCep(cep));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("CEP inválido. Certifique-se de que o formato está correto.");
        } catch (NoProductsFoundException e) {
            return ResponseEntity.status(404).body("Nenhum produto encontrado para o CEP informado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor.");
        }
    }
}
