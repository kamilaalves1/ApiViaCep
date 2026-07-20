package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.ProductResponse;
import com.viacep.exception.InvalidCepException;
import com.viacep.exception.NotFoundException;
import com.viacep.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/by-location")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductsByLocation(@RequestParam String cep) {
        String cepDigits = cep.replaceAll("-", "");
        if (!cepDigits.matches("\\d{8}")) {
            throw new InvalidCepException("CEP invalido: " + cep);
        }

        ProductResponse data = productService.findProductsByCep(cep);

        if (data.getProducts() == null || data.getProducts().isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado para o CEP informado.");
        }

        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .data(data)
                .message("Produtos encontrados com sucesso")
                .build());
    }
}