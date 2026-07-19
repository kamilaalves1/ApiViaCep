package com.example.api;

import org.springframework.stereotype.Service;

@Service
public class ViacepService {
    
    public boolean validarCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false; // CEP vazio ou nulo
        }
        
        // Remove caracteres especiais e espaços
        cep = cep.replaceAll("[^0-9]", "");

        if (cep.length() != 8) {
            return false; // CEP deve ter exatamente 8 dígitos
        }

        return true; // CEP válido
    }
}
