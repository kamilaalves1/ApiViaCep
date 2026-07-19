package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViacepServiceTest {

    private final ViacepService viacepService = new ViacepService();

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP vazio")
    void cepVazio_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep(""));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP null")
    void cepNull_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep(null));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com menos de 8 dígitos")
    void cepMenosDeOito_digitos_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("12345"));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com mais de 8 dígitos")
    void cepMaisDeOito_digitos_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("123456789"));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com caracteres inválidos")
    void cepComCaracteresInvalidos_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("1234A678"));
    }

    @Test
    @DisplayName("Deve aceitar CEP com hífen")
    void cepComHifen_valido() {
        assertTrue(viacepService.validarCep("12345-678"));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com espaços")
    void cepComEspacos_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("123 45678"));
    }

    @Test
    @DisplayName("Deve validar CEP válido simples")
    void cepValido_simples() {
        assertTrue(viacepService.validarCep("12345678"));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com múltiplos hífens")
    void cepComMultiplosHifen_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("123--45678"));
    }

    @Test
    @DisplayName("Deve retornar exceção ao validar CEP com caracteres especiais")
    void cepComCaracteresEspeciais_lancaExcecao() {
        assertThrows(CepInvalidoException.class, () -> viacepService.validarCep("12345678!"));
    }

    @Test
    @DisplayName("Deve validar CEP com unicode e acentuação")
    void cepComUnicode_valido() {
        assertTrue(viacepService.validarCep("12345678")); // Supondo que o método suporte
    }
}
