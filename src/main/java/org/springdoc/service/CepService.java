package org.springdoc.service;

import org.springdoc.repository.CepRepository;
import org.springdoc.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private final CepRepository cepRepository;

    @Autowired
    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public ApiResponse<?> findCepByFilters(String uf, String city, String region) {
        // Lógica para buscar CEPS utilizando o repositório
        return new ApiResponse<>(cepRepository.findByFilters(uf, city, region));
    }
}
