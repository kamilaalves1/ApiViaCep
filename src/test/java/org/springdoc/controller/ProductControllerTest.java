package org.springdoc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Configuração inicial, se necessário
    }

    @Test
    public void testGetProductsByCep_Returns200() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/01310-100"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetProductsByCep_Returns200_NonFormattedCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/01310100"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetProductsByCep_Returns400_InvalidCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/12345"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetProductsByCep_Returns400_EmptyCep() throws Exception {
        mockMvc.perform(get("/api/cep-teste-kamila/"))
               .andExpect(status().isBadRequest());
    }

    // Adicione outros testes relevantes
}
