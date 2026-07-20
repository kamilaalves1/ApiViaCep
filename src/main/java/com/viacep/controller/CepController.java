package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.EnderecoDto;
import com.viacep.exception.CepInvalidoException;
import com.viacep.service.ViacepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CepController {

    private final ViacepService viacepService;

    @GetMapping("/{cep}")
    public ResponseEntity<ApiResponse<EnderecoDto>> buscarCep(@PathVariable String cep) {
        log.info("Requisição recebida para buscar CEP: {}", cep);

        EnderecoDto endereco = viacepService.buscarPorCep(cep);

        ApiResponse<EnderecoDto> response = ApiResponse.<EnderecoDto>builder()
                .success(true)
                .data(endereco)
                .message("CEP encontrado com sucesso")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/{cep}")
    public ResponseEntity<ApiResponse<Boolean>> validarCep(@PathVariable String cep) {
        log.info("Validação de CEP recebida: {}", cep);

        try {
            viacepService.buscarPorCep(cep);
            ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                    .success(true)
                    .data(true)
                    .message("CEP válido")
                    .build();
            return ResponseEntity.ok(response);
        } catch (CepInvalidoException e) {
            ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                    .success(false)
                    .data(false)
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
