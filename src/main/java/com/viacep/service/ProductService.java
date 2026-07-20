package com.viacep.service;

import com.viacep.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {

    public ProductResponse findProductsByCep(String cep) {
        // Dados mock para retorno do endpoint
        ProductResponse response = new ProductResponse();
        
        response.setLocation(new Location(cep, "São Paulo", "SP"));

        Product product1 = new Product("1", "Produto 1", "Categoria A", 10, 99.99, 
                Arrays.asList("Disponível Online", "Loja 1"), "1-3 dias");
        Product product2 = new Product("2", "Produto 2", "Categoria B", 5, 49.99, 
                Arrays.asList("Loja 2"), "2-5 dias");
        
        response.setProducts(Arrays.asList(product1, product2));
        response.setNearestStore(new Store("1", "Loja 1", "2 km", "Rua Exemplo, 123"));
        
        return response;
    }
}
