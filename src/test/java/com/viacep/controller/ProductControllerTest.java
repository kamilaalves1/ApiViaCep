package com.viacep.controller;

import com.viacep.dto.ProductResponse;
import com.viacep.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    public ProductControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductsByLocation() {
        String cep = "01310-100";
        List<ProductResponse> productList = Collections.singletonList(new ProductResponse("123", "Produto A", "Eletrônicos", 45, 199.90, List.of("loja SP-01"), "2-3 dias"));
        when(productService.getProductsByLocation(cep, null, null)).thenReturn(productList);
        
        ResponseEntity<List<ProductResponse>> response = productController.getProductsByLocation(cep, null, null);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testGetNearbyProducts() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        String radius = "5km";
        
        List<ProductResponse> productList = Collections.singletonList(new ProductResponse("123", "Produto A", "Eletrônicos", 45, 199.90, List.of("loja SP-01"), "2-3 dias"));
        when(productService.getNearbyProducts(latitude, longitude, radius)).thenReturn(productList);
        
        ResponseEntity<List<ProductResponse>> response = productController.getNearbyProducts(latitude, longitude, radius);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productList, response.getBody());
    }
}
