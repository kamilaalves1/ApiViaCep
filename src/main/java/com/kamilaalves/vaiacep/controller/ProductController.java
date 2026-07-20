package com.kamilaalves.vaiacep.controller;

import com.kamilaalves.vaiacep.model.Location;
import com.kamilaalves.vaiacep.model.ProductResponse;
import com.kamilaalves.vaiacep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/by-zipcode/{cep}")
    public ResponseEntity<ProductResponse> getProductsByZipcode(@PathVariable String cep) {
        ProductResponse response = productService.getProductsByZipcode(cep);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-location")
    public ResponseEntity<ProductResponse> getProductsByLocation(@RequestParam String uf, @RequestParam String city) {
        ProductResponse response = productService.getProductsByLocation(uf, city);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nearby")
    public ResponseEntity<ProductResponse> getNearbyProducts(@RequestParam double latitude, @RequestParam double longitude, @RequestParam String radius) {
        ProductResponse response = productService.getNearbyProducts(latitude, longitude, radius);
        return ResponseEntity.ok(response);
    }
}
