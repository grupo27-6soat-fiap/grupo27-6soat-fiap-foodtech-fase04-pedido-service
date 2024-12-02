package br.com.fiap.lanchonete.pedidoservicefase4.app.exception;

import br.com.fiap.lanchonete.pedidoservicefase4.app.validation.ValidationErrorResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void shouldHandleEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Entity not found");

        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = globalExceptionHandler.handleEntityNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody().getMessage());
    }

    @Test
    public void shouldHandleMethodArgumentNotValidException() {
        FieldError fieldError = new FieldError("object", "field", "default message");
        Object target = new Object();
        String objectName = "object";
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, objectName);
        bindingResult.addError(fieldError);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ValidationErrorResponse> response = globalExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Arrays.asList("Field 'field': default message"), response.getBody().getErrors());
    }

    @Test
    public void shouldHandleEnumValidationException() {
        EnumValidationException exception = new EnumValidationException("field", "invalid value");

        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = globalExceptionHandler.handleEnumValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid value for field 'field': invalid value", response.getBody().getMessage());
    }

    @Test
    public void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Message not readable");

        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = globalExceptionHandler.handleHttpMessageNotReadableException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message not readable", response.getBody().getMessage());
    }

    @Test
    public void shouldHandleEntityExistsException() {
        EntityExistsException exception = new EntityExistsException("Entity exists");

        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = globalExceptionHandler.handleEntityExistsException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Entity exists", response.getBody().getMessage());
    }
}
