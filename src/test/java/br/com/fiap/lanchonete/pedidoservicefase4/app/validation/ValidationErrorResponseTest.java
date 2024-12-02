package br.com.fiap.lanchonete.pedidoservicefase4.app.validation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationErrorResponseTest {


    @Test
    public void testValidationErrorResponse() {
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(errors);

        assertEquals(errors, validationErrorResponse.getErrors());
    }
}
