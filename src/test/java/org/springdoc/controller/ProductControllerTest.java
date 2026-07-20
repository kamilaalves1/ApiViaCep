package org.springdoc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springdoc.dto.ApiResponse;
import org.springdoc.exception.ResourceNotFoundException;
import org.springdoc.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductByCep_Success() throws ResourceNotFoundException {
        String validCep = "01310-100";
        Object mockProductResponse = new Object(); // substitua por uma resposta de produto válida

        when(productService.findProductByCep(anyString())).thenReturn(mockProductResponse);

        ResponseEntity<ApiResponse> response = productController.getProductByCep(validCep);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProductResponse, response.getBody().getData());  // consider assuming ApiResponse has getData() method
    }

    @Test
    public void testGetProductByCep_NotFound() throws ResourceNotFoundException {
        String invalidCep = "00000000";

        when(productService.findProductByCep(anyString())).thenThrow(new ResourceNotFoundException("CEP não encontrado."));

        ResponseEntity<ApiResponse> response = productController.getProductByCep(invalidCep);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("CEP não encontrado. Por favor, verifique se digitou corretamente.", response.getBody().getMessage());
    }

    @Test
    public void testGetProductByCep_NoCepProvided() {
        ResponseEntity<ApiResponse> response = productController.getProductByCep("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Por favor, forneça um CEP para buscar as informações correspondentes.", response.getBody().getMessage());
    }
}
