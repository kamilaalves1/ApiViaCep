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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
