package org.springdoc.controller;

import org.springdoc.exception.ProductNotFoundException;
import org.springdoc.response.ApiResponse;
import org.springdoc.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    private final CepService cepService;

    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/filter")
    public ApiResponse<?> filterCep(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String neighborhood,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return cepService.filterCep(uf, region, city, neighborhood, page, size);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ApiResponse<>(ex.getMessage());
    }
}
