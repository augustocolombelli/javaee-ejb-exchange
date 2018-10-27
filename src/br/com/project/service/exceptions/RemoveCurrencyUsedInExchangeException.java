package br.com.project.service.exceptions;

public class RemoveCurrencyUsedInExchangeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RemoveCurrencyUsedInExchangeException() {
        super("It is not possible to remove this currency because there are one or more exchange rate associated.");
    }
}
