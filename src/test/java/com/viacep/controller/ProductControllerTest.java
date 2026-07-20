package com.viacep.controller;

import com.viacep.dto.ProductResponse;
import com.viacep.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    void whenGetProductsByLocation_thenReturnProducts() throws Exception {
        // dado
        ProductResponse response = new ProductResponse();
        // Inicializa o objeto response com produtos mockados aqui
        when(productService.findProductsByCep(anyString())).thenReturn(response);

        // quando
        mockMvc.perform(get("/api/products/by-location?cep=01310-100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").exists());
    }

    // Outros testes podem ser adicionados aqui conforme necessário
}
