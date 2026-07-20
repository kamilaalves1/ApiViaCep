package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.ProductResponse;
import com.viacep.exception.InvalidCepException;
import com.viacep.exception.NotFoundException;
import com.viacep.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductsByLocation_WithInvalidCep() {
        String invalidCep = "1234-567";
        InvalidCepException exception = assertThrows(InvalidCepException.class, () -> {
            productController.getProductsByLocation(invalidCep);
        });
        assertEquals("Formato de CEP inválido", exception.getMessage());
    }

    @Test
    public void testGetProductsByLocation_WithNoProductsFound() {
        String cep = "12345-678";
        when(productService.findProductsByCep(cep)).thenReturn(new ProductResponse());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            productController.getProductsByLocation(cep);
        });
        assertEquals("Nenhum produto encontrado para o CEP informado.", exception.getMessage());
    }

    @Test
    public void testGetProductsByLocation_Success() {
        String cep = "12345-678";
        ProductResponse productResponse = new ProductResponse();
        when(productService.findProductsByCep(cep)).thenReturn(productResponse);

        ResponseEntity<ApiResponse<ProductResponse>> response = productController.getProductsByLocation(cep);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productResponse, response.getBody().getData());
    }
}
