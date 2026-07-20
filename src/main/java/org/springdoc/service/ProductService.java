package org.springdoc.service;

import org.springdoc.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ApiResponse<?> findProductsByCep(String cep) {
        // Lógica para buscar produtos pelo código postal.
        return new ApiResponse<>(null);
    }
}
