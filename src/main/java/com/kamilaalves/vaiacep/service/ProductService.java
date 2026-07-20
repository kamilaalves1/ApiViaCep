package com.kamilaalves.vaiacep.service;

import com.kamilaalves.vaiacep.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductResponse getProductsByZipcode(String cep) {
        // Lógica para buscar produtos por CEP
        return new ProductResponse();
    }

    public ProductResponse getProductsByLocation(String uf, String city) {
        // Lógica para buscar produtos por localização
        return new ProductResponse();
    }

    public ProductResponse getNearbyProducts(double latitude, double longitude, String radius) {
        // Lógica para buscar produtos próximos
        return new ProductResponse();
    }
}
