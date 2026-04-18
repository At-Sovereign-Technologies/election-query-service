package com.electoral.election_query_service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleNotFound() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/test");

        ResourceNotFoundException ex = new ResourceNotFoundException("Not found");

        ErrorResponse response = handler.handleNotFound(ex, request);

        assertEquals(404, response.getStatus());
        assertEquals("NOT_FOUND", response.getError());
        assertEquals("Not found", response.getMessage());
        assertEquals("/api/test", response.getPath());
    }

    @Test
    void shouldHandleValidation() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/test");

        ConstraintViolationException ex =
                new ConstraintViolationException("Validation error", null);

        ErrorResponse response = handler.handleValidation(ex, request);

        assertEquals(400, response.getStatus());
        assertEquals("BAD_REQUEST", response.getError());
        assertEquals("Validation error", response.getMessage());
    }

    @Test
    void shouldHandleGenericException() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/test");

        Exception ex = new RuntimeException("boom");

        ErrorResponse response = handler.handleGeneric(ex, request);

        assertEquals(500, response.getStatus());
        assertEquals("INTERNAL_ERROR", response.getError());
        assertEquals("Unexpected error", response.getMessage());
    }
}