package org.springdoc.service;

import org.springdoc.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    public ApiResponse<?> filterCeps(String uf, String city, String region) {
        // Lógica para buscar CEPs com base nos filtros
        return new ApiResponse<>(/* dados filtrados */);
    }
}
