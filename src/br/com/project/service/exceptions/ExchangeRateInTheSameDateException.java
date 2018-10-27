package br.com.project.service.exceptions;

public class ExchangeRateInTheSameDateException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExchangeRateInTheSameDateException() {
        super("It is not possible insert because there is exchange rate in the same date.");
    }
}
