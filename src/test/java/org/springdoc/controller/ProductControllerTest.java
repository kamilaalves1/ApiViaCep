package org.springdoc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springdoc.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        // Configuração inicial, se necessário
    }

    @Test
    @DisplayName("Deve retornar 200 ao consultar cep válido formatado")
    public void testGetProductsByCep_Returns200() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/01310-100"))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar 200 ao consultar cep válido não formatado")
    public void testGetProductsByCep_Returns200_NonFormattedCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/01310100"))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep inválido")
    public void testGetProductsByCep_Returns400_InvalidCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/12345"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: 12345"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep vazio")
    public void testGetProductsByCep_Returns400_EmptyCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: "));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com letras")
    public void testGetProductsByCep_Returns400_InvalidCep_WithLetters() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/abcde"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: abcde"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com caracteres especiais")
    public void testGetProductsByCep_Returns400_InvalidCep_WithSpecialCharacters() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/12-34-56$%"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: 12-34-56$%"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com mais de 8 dígitos")
    public void testGetProductsByCep_Returns400_InvalidCep_WithMoreDigits() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/123456789"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: 123456789"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com menos de 8 dígitos")
    public void testGetProductsByCep_Returns400_InvalidCep_WithLessDigits() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/1234"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: 1234"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com múltiplos hífens")
    public void testGetProductsByCep_Returns400_InvalidCep_WithMultipleHyphens() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/12-34-56-78"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido: 12-34-56-78"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao consultar cep com espaços")
    public void testGetProductsByCep_Returns400_InvalidCep_WithSpaces() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/ 12345678 "))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("CEP inválido:  12345678 "));
    }
}
