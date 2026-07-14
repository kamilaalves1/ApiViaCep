package com.viacep.service;

import com.viacep.dto.EnderecoDto;
import com.viacep.exception.CepInvalidoException;
import com.viacep.exception.ViacepException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViacepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";
    private final RestTemplate restTemplate;

    public EnderecoDto buscarPorCep(String cep) {
        validarCep(cep);

        String cepLimpo = cep.replaceAll("\\D", "");

        try {
            log.info("Buscando CEP: {}", cepLimpo);
            EnderecoDto endereco = restTemplate.getForObject(
                    VIACEP_URL,
                    EnderecoDto.class,
                    cepLimpo
            );

            if (endereco != null && Boolean.TRUE.equals(endereco.getErrro())) {
                log.warn("CEP não encontrado: {}", cepLimpo);
                throw new CepInvalidoException("CEP não encontrado: " + cep);
            }

            log.info("CEP encontrado com sucesso: {}", cepLimpo);
            return endereco;
        } catch (RestClientException e) {
            log.error("Erro ao consultar VIA CEP para o CEP: {}", cepLimpo, e);
            throw new ViacepException("Erro ao consultar o serviço VIA CEP", e);
        }
    }

    private void validarCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new CepInvalidoException("CEP não pode ser vazio");
        }

        String cepLimpo = cep.replaceAll("\\D", "");

        if (cepLimpo.length() != 8) {
            throw new CepInvalidoException("CEP deve conter exatamente 8 dígitos");
        }

        if (!cepLimpo.matches("\\d{8}")) {
            throw new CepInvalidoException("CEP deve conter apenas dígitos");
        }
    }
}
