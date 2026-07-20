package com.kamilaalves.vaiacep;

import com.kamilaalves.vaiacep.controller.ProductController;
import com.kamilaalves.vaiacep.model.ProductResponse;
import com.kamilaalves.vaiacep.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        ProductService productService = Mockito.mock(ProductService.class);
        this.productController = new ProductController();
        this.productController.productService = productService;
    }

    @Test
    public void testGetProductsByZipcode() {
        ProductResponse expectedResponse = new ProductResponse();
        // Mockando o serviço para retornar uma resposta esperada
        Mockito.when(productService.getProductsByZipcode("01310-100")).thenReturn(expectedResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.getProductsByZipcode("01310-100");
        assertThat(responseEntity.getBody()).isEqualTo(expectedResponse);
    }
}
