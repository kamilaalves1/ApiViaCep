package com.viacep.controller;

import com.viacep.model.ProductResponse;
import com.viacep.service.ProductService;
import com.viacep.exception.InvalidCepException;
import com.viacep.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProductsByCep_Success() throws Exception {
        ProductResponse mockResponse = new ProductResponse();
        when(productService.findProductsByCep(anyString())).thenReturn(mockResponse);

        mockMvc.perform(get("/api/products/by-location?cep=01310-100"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProductsByCep_InvalidCep() throws Exception {
        mockMvc.perform(get("/api/products/by-location?cep=12345"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    throw new InvalidCepException("Formato de CEP inválido");
                });
    }

    @Test
    void testGetProductsByCep_NotFound() throws Exception {
        when(productService.findProductsByCep(anyString())).thenReturn(new ProductResponse()); // vazio

        mockMvc.perform(get("/api/products/by-location?cep=01310-100"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    throw new NotFoundException("Nenhum produto encontrado para o CEP informado.");
                });
    }
}
