package com.example.apiviacep;

import com.example.apiviacep.controller.ProductController;
import com.example.apiviacep.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // Mock setup if necessary
    }

    @Test
    void testGetProductsByLocation() throws Exception {
        mockMvc.perform(get("/api/products/by-location?cep=01310-100"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNearbyProducts() throws Exception {
        mockMvc.perform(get("/api/products/nearby?latitude=-23.5505&longitude=-46.6333&radius=5km"))
                .andExpect(status().isOk());
    }
}
