package org.springdoc.controller;

import org.springdoc.exception.ProductNotFoundException;
import org.springdoc.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/cep-teste-kamila")
public class ProductController {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}|\\d{8}");

    @GetMapping
    public ApiResponse<?> getProductsByCep(@RequestParam String cep) {
        if (!CEP_PATTERN.matcher(cep).matches()) {
            throw new ProductNotFoundException("CEP inválido: " + cep);
        }

        // Lógica para buscar produtos pelo código postal.
        return new ApiResponse<>(/* dados dos produtos encontrados */);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ApiResponse<>(ex.getMessage());
    }
}
