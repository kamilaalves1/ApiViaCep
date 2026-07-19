package com.example.api;

import java.util.regex.Pattern;

public class ViacepService {

    private static final String CEP_PATTERN = "^\\d{5}-\\d{3}$"; // Formato: 12345-678

    public boolean validarCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false; // CEP vazio ou nulo
        }
        return Pattern.matches(CEP_PATTERN, cep);
    }
}
