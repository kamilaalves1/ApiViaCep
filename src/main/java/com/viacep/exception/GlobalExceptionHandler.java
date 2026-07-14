package com.viacep.exception;

import com.viacep.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ApiResponse<Void>> handleCepInvalidoException(CepInvalidoException ex) {
        log.warn("CEP inválido: {}", ex.getMessage());

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .error(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ViacepException.class)
    public ResponseEntity<ApiResponse<Void>> handleViacepException(ViacepException ex) {
        log.error("Erro ao consultar VIA CEP: {}", ex.getMessage(), ex);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .error(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException ex) {
        log.warn("Endpoint não encontrado: {}", ex.getRequestURL());

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .error("Endpoint não encontrado")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex) {
        log.error("Erro interno: ", ex);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .error("Erro interno do servidor")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
