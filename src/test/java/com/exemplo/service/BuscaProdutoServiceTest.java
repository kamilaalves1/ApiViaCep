package com.exemplo.service;

import com.exemplo.model.ProdutoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuscaProdutoServiceTest {

    @Test
    void testBuscaProdutosPorCep() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        BuscaProdutoService service = new BuscaProdutoService();
        service.setRestTemplate(restTemplate);

        String cep = "01310-100";
        ProdutoResponse mockResponse = new ProdutoResponse();
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(ProdutoResponse.class)))
               .thenReturn(mockResponse);

        ProdutoResponse response = service.buscaProdutosPorCep(cep);
        assertNotNull(response);
    }
}
