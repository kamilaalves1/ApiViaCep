package com.example.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViacepServiceTest {
    
    private final ViacepService viacepService = new ViacepService();

    @Test
    @DisplayName("CEP vazio deve retornar false")
    void testCepVazio() {
        assertFalse(viacepService.validarCep(""));
    }

    @Test
    @DisplayName("CEP nulo deve retornar false")
    void testCepNulo() {
        assertFalse(viacepService.validarCep(null));
    }

    @Test
    @DisplayName("CEP com menos de 8 dígitos deve retornar false")
    void testCepMenosDe8Digitos() {
        assertFalse(viacepService.validarCep("1234567"));
    }

    @Test
    @DisplayName("CEP com mais de 8 dígitos deve retornar false")
    void testCepMaisDe8Digitos() {
        assertFalse(viacepService.validarCep("123456789"));
    }

    @Test
    @DisplayName("CEP com caracteres inválidos deve retornar false")
    void testCepComCaracteresInvalidos() {
        assertFalse(viacepService.validarCep("1234-567"));
    }

    @Test
    @DisplayName("CEP com espaço deve retornar true após formatação")
    void testCepComEspacos() {
        assertTrue(viacepService.validarCep(" 12345678 "));
    }

    @Test
    @DisplayName("CEP válido simples deve retornar true")
    void testCepValidoSimples() {
        assertTrue(viacepService.validarCep("12345678"));
    }

    @Test
    @DisplayName("CEP com múltiplos hífens deve retornar true após formatação")
    void testCepComMultiplosHifens() {
        assertTrue(viacepService.validarCep("12-34-5678"));
    }

    @Test
    @DisplayName("CEP com caracteres especiais deve retornar true após formatação")
    void testCepComCaracteresEspeciais() {
        assertTrue(viacepService.validarCep("#12345678!"));
    }

    @Test
    @DisplayName("CEP com unicode/acentuação deve retornar true após formatação")
    void testCepComUnicode() {
        assertTrue(viacepService.validarCep("12345-678"));
    }
}
