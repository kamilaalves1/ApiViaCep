package com.viacep.exception;

import com.viacep.dto.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleCepInvalidoException() {
        CepInvalidoException exception = new CepInvalidoException("CEP inválido");
        ResponseEntity<ApiResponse<Void>> response = globalExceptionHandler.handleCepInvalidoException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("CEP inválido", response.getBody().getError());
    }

    @Test
    void testHandleViacepException() {
        ViacepException exception = new ViacepException("Erro ao consultar");
        ResponseEntity<ApiResponse<Void>> response = globalExceptionHandler.handleViacepException(exception);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Erro ao consultar", response.getBody().getError());
    }

    @Test
    void testHandleNotFoundException() {
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/api/unknown", null);
        ResponseEntity<ApiResponse<Void>> response = globalExceptionHandler.handleNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Endpoint não encontrado", response.getBody().getError());
    }

    @Test
    void testHandleGlobalException() {
        Exception exception = new RuntimeException("Erro inesperado");
        ResponseEntity<ApiResponse<Void>> response = globalExceptionHandler.handleGlobalException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Erro interno do servidor", response.getBody().getError());
    }

    @Test
    void testHandleGlobalException_WithCausa() {
        Exception exception = new Exception("Erro com causa", new RuntimeException("Causa"));
        ResponseEntity<ApiResponse<Void>> response = globalExceptionHandler.handleGlobalException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals("Erro interno do servidor", response.getBody().getError());
    }
}

