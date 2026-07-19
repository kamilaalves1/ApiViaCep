package com.example.apiviacep.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ViacepServiceTest {

    private final ViacepService viacepService = new ViacepService();

    @Test
    @DisplayName("Validar CEP vazio")
    public void testValidarCepVazio() {
        assertFalse(viacepService.validarCep(""));
    }

    @Test
    @DisplayName("Validar CEP nulo")
    public void testValidarCepNulo() {
        assertFalse(viacepService.validarCep(null));
    }

    @Test
    @DisplayName("Validar CEP com menos de 8 dígitos")
    public void testValidarCepMenosDeOito() {
        assertFalse(viacepService.validarCep("12345"));
    }

    @Test
    @DisplayName("Validar CEP com mais de 8 dígitos")
    public void testValidarCepMaisDeOito() {
        assertFalse(viacepService.validarCep("12345-6789"));
    }

    @Test
    @DisplayName("Validar CEP com caracteres inválidos")
    public void testValidarCepCaracteresInvalidos() {
        assertFalse(viacepService.validarCep("1234A-678"));
    }

    @Test
    @DisplayName("Validar CEP com hífen")
    public void testValidarCepComHifen() {
        assertTrue(viacepService.validarCep("12345-678"));
    }

    @Test
    @DisplayName("Validar CEP com espaços")
    public void testValidarCepComEspacos() {
        assertFalse(viacepService.validarCep(" 12345-678 "));
    }

    @Test
    @DisplayName("Validar CEP válido")
    public void testValidarCepValido() {
        assertTrue(viacepService.validarCep("12345-678"));
    }

    @Test
    @DisplayName("Validar múltiplos hífens")
    public void testValidarCepMultiploHifen() {
        assertFalse(viacepService.validarCep("12-34-56-78"));
    }

    @Test
    @DisplayName("Validar caracteres especiais")
    public void testValidarCepCaracteresEspeciais() {
        assertFalse(viacepService.validarCep("12345@678"));
    }

    @Test
    @DisplayName("Validar CEP com Unicode/Acentuação")
    public void testValidarCepUnicode() {
        assertFalse(viacepService.validarCep("12345-67á"));
    }
}
