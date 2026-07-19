package com.example.api;

import org.springframework.stereotype.Service;

@Service
public class ViacepService {
  
    public boolean validarCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser vazio ou nulo.");
        }
        if (cep.length() != 8) {
            throw new IllegalArgumentException("CEP deve ter exatamente 8 dígitos.");
        }
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP contém caracteres inválidos.");
        }
        return true; // Assume que o CEP é válido para fins de teste.
    }
}
