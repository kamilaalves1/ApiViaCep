package org.springdoc.service;

import org.springdoc.dto.ProductResponse;
import org.springdoc.model.Location;
import org.springdoc.model.Product;
import org.springdoc.model.Store;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {
    public ProductResponse findProductsByCep(String cep) {
        Location loc = new Location(cep, "Sao Paulo", "SP");
        Product p1 = new Product("1", "Notebook Dell", "Informatica", 10, 3999.99, Arrays.asList("Online"), "1-3 dias");
        Product p2 = new Product("2", "Mouse Logitech", "Perifericos", 50, 89.90, Arrays.asList("Online"), "1-2 dias");
        Store store = new Store("1", "Loja Centro", "1.2 km", "Av. Paulista, 1000");
        return new ProductResponse(loc, Arrays.asList(p1, p2), store);
    }
}
