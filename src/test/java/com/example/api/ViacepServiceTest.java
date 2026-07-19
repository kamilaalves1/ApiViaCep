package com.example.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ViacepServiceTest {

    private final ViacepService viacepService = new ViacepService();

    @Test
    @DisplayName("CEP vazio deve ser inválido")
    void testValidarCep_Vazio() {
        assertFalse(viacepService.validarCep(""));
    }

    @Test
    @DisplayName("CEP nulo deve ser inválido")
    void testValidarCep_Nulo() {
        assertFalse(viacepService.validarCep(null));
    }

    @Test
    @DisplayName("CEP com menos de 8 dígitos deve ser inválido")
    void testValidarCep_MenosDeOitoDigitos() {
        assertFalse(viacepService.validarCep("1234-567"));
    }

    @Test
    @DisplayName("CEP com mais de 8 dígitos deve ser inválido")
    void testValidarCep_MaisDeOitoDigitos() {
        assertFalse(viacepService.validarCep("123456789"));
    }

    @Test
    @DisplayName("CEP com caracteres inválidos deve ser inválido")
    void testValidarCep_CaracteresInvalidos() {
        assertFalse(viacepService.validarCep("12345-A67"));
    }

    @Test
    @DisplayName("CEP com hífen deve ser válido")
    void testValidarCep_ValidoComHifen() {
        assertTrue(viacepService.validarCep("12345-678"));
    }

    @Test
    @DisplayName("CEP com espaços deve ser inválido")
    void testValidarCep_ComEspacos() {
        assertFalse(viacepService.validarCep("123 45-678"));
    }

    @Test
    @DisplayName("CEP válido simples deve ser válido")
    void testValidarCep_ValidoSimples() {
        assertTrue(viacepService.validarCep("98765-432"));
    }

    @Test
    @DisplayName("CEP com múltiplos hífens deve ser inválido")
    void testValidarCep_MultiploHifen() {
        assertFalse(viacepService.validarCep("123--45-678"));
    }

    @Test
    @DisplayName("CEP com caracteres especiais deve ser inválido")
    void testValidarCep_CaracteresEspeciais() {
        assertFalse(viacepService.validarCep("12345@678"));
    }

    @Test
    @DisplayName("CEP com unicode/acentuação deve ser inválido")
    void testValidarCep_Unicode() {
        assertFalse(viacepService.validarCep("1234-5á6"));
    }
}
