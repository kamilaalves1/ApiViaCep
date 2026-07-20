package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.EnderecoDto;
import com.viacep.exception.CepInvalidoException;
import com.viacep.service.ViacepService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CepController.class)
class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViacepService viacepService;

    @Test
    void testBuscarCepComSucesso() throws Exception {
        String cep = "01310100";
        EnderecoDto endereco = new EnderecoDto();
        endereco.setCep("01310-100");
        endereco.setLogradouro("Avenida Paulista");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");

        when(viacepService.buscarPorCep(cep)).thenReturn(endereco);

        mockMvc.perform(get("/api/cep/{cep}", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data.logradouro", is("Avenida Paulista")))
                .andExpect(jsonPath("$.data.localidade", is("São Paulo")));
    }

    @Test
    void testBuscarCepInvalido() throws Exception {
        String cep = "00000000";
        when(viacepService.buscarPorCep(cep))
                .thenThrow(new CepInvalidoException("CEP não encontrado"));

        mockMvc.perform(get("/api/cep/{cep}", cep))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    void testValidarCepValido() throws Exception {
        String cep = "01310100";
        EnderecoDto endereco = new EnderecoDto();
        endereco.setErro(false);

        when(viacepService.buscarPorCep(cep)).thenReturn(endereco);

        mockMvc.perform(get("/api/cep/validate/{cep}", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    void testValidarCepInvalido() throws Exception {
        String cep = "00000000";
        when(viacepService.buscarPorCep(cep))
                .thenThrow(new CepInvalidoException("CEP inválido"));

        mockMvc.perform(get("/api/cep/validate/{cep}", cep))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.data", is(false)));
    }

    @Test
    void testBuscarCepComFormatoIncorreto() throws Exception {
        String cep = "ABCDE-00";
        mockMvc.perform(get("/api/cep/{cep}", cep))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error", is("CEP deve conter apenas dígitos")));
    }

    @Test
    void testBuscarCepInexistente() throws Exception {
        String cep = "99999999";
        when(viacepService.buscarPorCep(cep))
                .thenThrow(new CepInvalidoException("CEP não encontrado"));

        mockMvc.perform(get("/api/cep/{cep}", cep))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error", is("CEP não encontrado")));
    }

    // Cenário de sucesso para a validação do CEP
    @Test
    void testValidarCepComSucesso() throws Exception {
        String cep = "01310100";
        when(viacepService.buscarPorCep(cep)).thenReturn(new EnderecoDto());

        mockMvc.perform(get("/api/cep/validate/{cep}", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data", is(true)))
                .andExpect(jsonPath("$.message", is("CEP válido")));
    }

    // Cenário de erro para a validação do CEP
    @Test
    void testValidarCepComErro() throws Exception {
        String cep = "99999999";
        when(viacepService.buscarPorCep(cep))
                .thenThrow(new CepInvalidoException("CEP inválido"));

        mockMvc.perform(get("/api/cep/validate/{cep}", cep))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.data", is(false)))
                .andExpect(jsonPath("$.error", is("CEP inválido")));
    }

    // Testando cabeçalhos de resposta
    @Test
    void testHeadersCORS() throws Exception {
        mockMvc.perform(get("/api/cep/01310100"))
                .andExpect(header().exists("Access-Control-Allow-Origin"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }

    // Verificação do Content-Type da resposta
    @Test
    void testContentType() throws Exception {
        mockMvc.perform(get("/api/cep/01310100"))
                .andExpect(content().contentType("application/json"));
    }

    // Verificando performance do endpoint
    @Test
    void testPerformance() throws Exception {
        long startTime = System.currentTimeMillis();
        mockMvc.perform(get("/api/cep/01310100"));
        long duration = System.currentTimeMillis() - startTime;

        assertTrue(duration < 500, "A performance do endpoint excedeu 500ms");
    }
}
