package org.springdoc.controller;

import org.springdoc.dto.ApiResponse;
import org.springdoc.exception.ResourceNotFoundException;
import org.springdoc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep-teste-kamila")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getProductByCep(@RequestParam(value = "cep") String cep) {
        // Validação do formato do CEP
        if (cep == null || cep.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("Por favor, forneça um CEP para buscar as informações correspondentes."));
        }

        try {
            // Chamada ao serviço para obter informações do produto
            Object product = productService.findProductByCep(cep);  // Assumindo que o ProductService retorna um objeto correspondente
            return ResponseEntity.ok(new ApiResponse(product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("CEP não encontrado. Por favor, verifique se digitou corretamente."));
        }
    }
}
