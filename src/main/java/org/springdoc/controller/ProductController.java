package org.springdoc.controller;

import org.springdoc.exception.ProductNotFoundException;
import org.springdoc.response.ApiResponse;
import org.springdoc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/cep-teste-kamila")
public class ProductController {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}|\\d{8}");

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{cep}")
    public ApiResponse<?> getProductsByCep(@PathVariable String cep) {
        if (cep == null || cep.trim().isEmpty() || !CEP_PATTERN.matcher(cep).matches()) {
            throw new ProductNotFoundException("CEP inválido: " + cep);
        }

        return productService.findProductsByCep(cep);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ApiResponse<>(ex.getMessage());
    }
}
