package org.springdoc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(CepController.class)
public class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Configuração inicial, se necessário
    }

    @Test
    public void testFilterCep_Returns200() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf=SP&city=São Paulo"))
               .andExpect(status().isOk());
    }

    @Test
    public void testFilterCep_Returns200_WithMultipleFilters() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf=RJ&region=Metropolitana"))
               .andExpect(status().isOk());
    }

    @Test
    public void testFilterCep_Returns200_DefaultPagination() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf=MG"))
               .andExpect(status().isOk());
    }

    @Test
    public void testFilterCep_Returns400_WhenInvalidUf() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf=ZZZ"))
               .andExpect(status().isBadRequest());
    }
}
