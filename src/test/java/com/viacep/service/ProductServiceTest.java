package com.viacep.service;

import static org.junit.jupiter.api.Assertions.*;

import com.viacep.dto.ProductResponse;
import com.viacep.model.Product;
import com.viacep.model.Store;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @Test
    void testFindProductsByCep() {
        String cep = "01310-100";
        ProductResponse response = productService.findProductsByCep(cep);

        assertNotNull(response);
        assertEquals(2, response.getProducts().size());
        assertEquals("Notebook Dell", response.getProducts().get(0).getName());
        assertEquals("Loja Centro", response.getNearestStore().getName());
    }
}
