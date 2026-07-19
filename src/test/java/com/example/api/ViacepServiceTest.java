package com.example.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViacepServiceTest {

    private final ViacepService viacepService = new ViacepService();

    @Test
    @DisplayName("Deve lançar exceção quando CEP é vazio")
    void testValidarCepVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("");
        });
        assertEquals("CEP não pode ser vazio ou nulo.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP é nulo")
    void testValidarCepNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep(null);
        });
        assertEquals("CEP não pode ser vazio ou nulo.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP tem menos de 8 dígitos")
    void testValidarCepMenosDeOitoDigitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("1234567");
        });
        assertEquals("CEP deve ter exatamente 8 dígitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP tem mais de 8 dígitos")
    void testValidarCepMaisDeOitoDigitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("123456789");
        });
        assertEquals("CEP deve ter exatamente 8 dígitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP contém caracteres inválidos")
    void testValidarCepCaracteresInvalidos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("1234ABCD");
        });
        assertEquals("CEP contém caracteres inválidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve aceitar CEP válido com hífen")
    void testValidarCepValidoComHifen() {
        assertTrue(viacepService.validarCep("12345-678"));
    }

    @Test
    @DisplayName("Deve aceitar CEP válido sem hífen")
    void testValidarCepValidoSemHifen() {
        assertTrue(viacepService.validarCep("12345678"));
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP contém espaços")
    void testValidarCepComEspacos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("12 345678");
        });
        assertEquals("CEP contém caracteres inválidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP contém múltiplos hífens")
    void testValidarCepComMultiplosHifens() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viacepService.validarCep("12--345678");
        });
        assertEquals("CEP contém caracteres inválidos.", exception.getMessage());
    }
}
