package com.example.apiviacep.service;

import java.util.regex.Pattern;

public class ViacepService {

    private static final String CEP_PATTERN = "^[0-9]{5}-?[0-9]{3}$";

    public boolean validarCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        return Pattern.matches(CEP_PATTERN, cep);
    }
}
