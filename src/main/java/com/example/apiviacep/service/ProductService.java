package com.example.apiviacep.service;

import com.example.apiviacep.model.Product;
import com.example.apiviacep.model.ProductResponse;
import com.example.apiviacep.model.Location;
import com.example.apiviacep.model.Store;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {

    public ProductResponse findProductsByLocation(String cep, String uf, String city) {
        // Dados mockados
        Location location = new Location(cep, city, uf);
        Store store = new Store("store1", "Store Name", "100m", "Store Address");

        Product product1 = new Product("prod1", "Product 1", "Category 1", 10, 29.99, Arrays.asList("Location 1"), "3 days");
        Product product2 = new Product("prod2", "Product 2", "Category 2", 5, 49.99, Arrays.asList("Location 2"), "5 days");

        ProductResponse response = new ProductResponse();
        response.setLocation(location);
        response.setProducts(Arrays.asList(product1, product2));
        response.setNearestStore(store);

        return response;
    }

    public ProductResponse findNearbyProducts(double latitude, double longitude, String radius) {
        // Lógica para buscar produtos próximos
        return new ProductResponse(); // Placeholder para a lógica real
    }
}
