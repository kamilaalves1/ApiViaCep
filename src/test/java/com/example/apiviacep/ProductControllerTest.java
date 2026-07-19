package com.example.apiviacep;

import com.example.apiviacep.controller.ProductController;
import com.example.apiviacep.model.ProductResponse;
import com.example.apiviacep.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
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
    void testGetProductsByLocation() throws Exception {
        when(productService.findProductsByLocation(anyString(), any(), any()))
                .thenReturn(new ProductResponse());

        mockMvc.perform(get("/api/products/by-location?cep=01310-100"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNearbyProducts() throws Exception {
        when(productService.findNearbyProducts(anyDouble(), anyDouble(), anyString()))
                .thenReturn(new ProductResponse());

        mockMvc.perform(get("/api/products/nearby?latitude=-23.5505&longitude=-46.6333&radius=5km"))
                .andExpect(status().isOk());
    }
}
