package com.example.apiviacep.service;

import com.example.apiviacep.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductResponse findProductsByLocation(String cep, String uf, String city) {
        // Implement your logic to fetch products based on location
        return new ProductResponse(); // return actual data
    }

    public ProductResponse findNearbyProducts(double latitude, double longitude, String radius) {
        // Implement your logic to fetch nearby products based on coordinates
        return new ProductResponse(); // return actual data
    }
}
