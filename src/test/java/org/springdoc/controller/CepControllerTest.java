package org.springdoc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CepController.class)
public class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Configuração inicial
    }

    @Test
    public void testFilterCeps_Returns200() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf=SP&city=São Paulo"))
               .andExpect(status().isOk());
    }
    
    @Test
    public void testFilterCeps_Returns400_EmptyUF() throws Exception {
        mockMvc.perform(get("/api/cep/filter?uf="))
               .andExpect(status().isBadRequest());
    }

    // Outros testes relevantes
}
