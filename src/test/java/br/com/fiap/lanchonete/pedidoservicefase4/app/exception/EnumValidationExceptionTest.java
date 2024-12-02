package br.com.fiap.lanchonete.pedidoservicefase4.app.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumValidationExceptionTest {

    @Test
    public void shouldReturnCorrectMessageWhenInvalidValueProvided() {
        EnumValidationException exception = new EnumValidationException("status", "INVALID");
        assertEquals("Invalid value for field 'status': INVALID", exception.getMessage());
    }

    @Test
    public void shouldReturnCorrectMessageWhenEmptyValueProvided() {
        EnumValidationException exception = new EnumValidationException("status", "");
        assertEquals("Invalid value for field 'status': ", exception.getMessage());
    }

    @Test
    public void shouldReturnCorrectMessageWhenNullValueProvided() {
        EnumValidationException exception = new EnumValidationException("status", null);
        assertEquals("Invalid value for field 'status': null", exception.getMessage());
    }
}
