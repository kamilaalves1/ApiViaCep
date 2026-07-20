package com.kamilaalves.vaiacep.controller;

import com.kamilaalves.vaiacep.model.Location;
import com.kamilaalves.vaiacep.model.ProductResponse;
import com.kamilaalves.vaiacep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products/by-location")
    public ResponseEntity<ProductResponse> getProductsByLocation(@RequestParam String cep, @RequestParam(required = false) String uf, @RequestParam(required = false) String city) {
        if (!isCepValid(cep)) {
            throw new InvalidCepException("Formato de CEP inválido");
        }
      
        ProductResponse response = productService.findProductsByLocation(cep, uf, city);
        return ResponseEntity.ok(response);
    }

    private boolean isCepValid(String cep) {
        return cep.replaceAll("-", "").matches("\\d{8}");
    }
}
