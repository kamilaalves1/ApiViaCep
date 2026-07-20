package org.springdoc.controller;

import org.springdoc.dto.ProductResponse;
import org.springdoc.service.ProductService;
import org.springframework.http.ResponseEntity;
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
            throw new InvalidCepException("CEP inválido. Por favor, verifique o formato e tente novamente.");
        }
        ProductResponse response = productService.findProductsByCep(cep);
        return ResponseEntity.ok(response);
    }
}
