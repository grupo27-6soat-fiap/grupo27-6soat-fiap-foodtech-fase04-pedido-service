package br.com.fiap.lanchonete.pedidoservicefase4.app.exception;

public class EnumValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EnumValidationException(String fieldName, String invalidValue) {
        super("Invalid value for field '" + fieldName + "': " + invalidValue);
    }
}
