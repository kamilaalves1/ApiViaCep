package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.ProductResponse;
import com.viacep.exception.InvalidCepException;
import com.viacep.exception.NotFoundException;
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
    public ResponseEntity<ApiResponse<ProductResponse>> getProductsByLocation(@RequestParam String cep) {
        if (!isCepValid(cep)) {
            throw new InvalidCepException("Formato de CEP inválido");
        }
        
        ProductResponse products = productService.findProductsByCep(cep);
        
        if (products.getProducts() == null || products.getProducts().isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado para o CEP informado.");
        }
        
        ApiResponse<ProductResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(products);
        response.setMessage("Produtos encontrados com sucesso");

        return ResponseEntity.ok(response);
    }

    private boolean isCepValid(String cep) {
        return cep.replaceAll("-", "").matches("\\d{8}");
    }
}
