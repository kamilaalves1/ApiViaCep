package com.kamilaalves.vaiacep.service;

import com.kamilaalves.vaiacep.model.ProductResponse;
import com.kamilaalves.vaiacep.model.Location;
import com.kamilaalves.vaiacep.model.Store;
import com.kamilaalves.vaiacep.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {

    public ProductResponse findProductsByLocation(String cep, String uf, String city) {
        // Mocked data for testing
        Location location = new Location(cep, city, uf);
        Store store = new Store("1", "Store A", "2 km", "Address A");
        
        Product product1 = new Product("1", "Product 1", "Category 1", 10, 99.99, 
                Arrays.asList("Available Online", "Store 1"), "1-3 days");
        Product product2 = new Product("2", "Product 2", "Category 2", 5, 49.99, 
                Arrays.asList("Store 2"), "2-5 days");
        
        ProductResponse response = new ProductResponse();
        response.setLocation(location);
        response.setProducts(Arrays.asList(product1, product2));
        response.setNearestStore(store);
        
        return response;
    }
}
