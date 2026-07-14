package com.viacep.service;

import com.viacep.dto.EnderecoDto;
import com.viacep.exception.CepInvalidoException;
import com.viacep.exception.ViacepException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViacepServiceTest {

    private ViacepService viacepService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        viacepService = new ViacepService(restTemplate);
    }

    @Test
    void testBuscarPorCepComSucesso() {
        String cep = "01310100";
        EnderecoDto expectedEndereco = new EnderecoDto();
        expectedEndereco.setCep("01310-100");
        expectedEndereco.setLogradouro("Avenida Paulista");
        expectedEndereco.setBairro("Bela Vista");
        expectedEndereco.setLocalidade("São Paulo");
        expectedEndereco.setUf("SP");
        expectedEndereco.setErro(false);

        when(restTemplate.getForObject(anyString(), eq(EnderecoDto.class), eq(cep)))
                .thenReturn(expectedEndereco);

        EnderecoDto resultado = viacepService.buscarPorCep(cep);

        assertNotNull(resultado);
        assertEquals("Avenida Paulista", resultado.getLogradouro());
        assertEquals("São Paulo", resultado.getLocalidade());
    }

    @Test
    void testBuscarPorCepNaoEncontrado() {
        String cep = "99999999";
        EnderecoDto expectedEndereco = new EnderecoDto();
        expectedEndereco.setErro(true);

        when(restTemplate.getForObject(anyString(), eq(EnderecoDto.class), eq(cep)))
                .thenReturn(expectedEndereco);

        assertThrows(CepInvalidoException.class, () -> viacepService.buscarPorCep(cep));
    }

    @Test
    void testBuscarPorCepVazio() {
        assertThrows(CepInvalidoException.class, () -> viacepService.buscarPorCep(""));
    }

    @Test
    void testBuscarPorCepComMenosDe8Digitos() {
        assertThrows(CepInvalidoException.class, () -> viacepService.buscarPorCep("1234567"));
    }

    @Test
    void testBuscarPorCepComMaisDe8Digitos() {
        assertThrows(CepInvalidoException.class, () -> viacepService.buscarPorCep("123456789"));
    }

    @Test
    void testBuscarPorCepComCaracteresInvalidos() {
        assertThrows(CepInvalidoException.class, () -> viacepService.buscarPorCep("ABCD-1234"));
    }

    @Test
    void testBuscarPorCepComFormatoCorreto() {
        String cep = "01310-100";
        EnderecoDto expectedEndereco = new EnderecoDto();
        expectedEndereco.setCep("01310-100");
        expectedEndereco.setErro(false);

        when(restTemplate.getForObject(anyString(), eq(EnderecoDto.class), eq("01310100")))
                .thenReturn(expectedEndereco);

        assertDoesNotThrow(() -> viacepService.buscarPorCep(cep));
    }

    @Test
    void testErroAoConsultarViacep() {
        String cep = "01310100";
        when(restTemplate.getForObject(anyString(), eq(EnderecoDto.class), eq(cep)))
                .thenThrow(new RestClientException("Conexão recusada"));

        assertThrows(ViacepException.class, () -> viacepService.buscarPorCep(cep));
    }
}
