package org.springdoc.controller;

import org.springdoc.exception.ProductNotFoundException;
import org.springdoc.response.ApiResponse;
import org.springdoc.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}|\\d{8}");

    private final CepService cepService;

    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/filter")
    public ApiResponse<?> filterCep(@RequestParam(required = false) String uf,
                                     @RequestParam(required = false) String city,
                                     @RequestParam(required = false) String region) {
        // Lógica de validação de filtros pode ser adicionada aqui

        return cepService.findCepByFilters(uf, city, region);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ApiResponse<>(ex.getMessage());
    }
}
