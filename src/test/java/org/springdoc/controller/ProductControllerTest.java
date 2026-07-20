package org.springdoc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springdoc.dto.ProductResponse;
import org.springdoc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProductsByLocation_ValidCep() throws Exception {
        String validCep = "01310-100";
        ProductResponse mockResponse = new ProductResponse(); // Populate mockResponse with expected data here
        when(productService.findProductsByCep(validCep)).thenReturn(mockResponse);

        ResponseEntity<ProductResponse> response = productController.getProductsByLocation(validCep);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testGetProductsByLocation_InvalidCep() {
        String invalidCep = "00000000";

        Exception exception = assertThrows(InvalidCepException.class, () -> {
            productController.getProductsByLocation(invalidCep);
        });
        
        assertEquals("CEP inválido. Por favor, verifique o formato e tente novamente.", exception.getMessage());
    }
}
