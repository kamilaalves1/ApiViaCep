package com.example.apiviacep;

import com.example.apiviacep.controller.ProductController;
import com.example.apiviacep.model.ProductResponse;
import com.example.apiviacep.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

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
