package br.com.project.service.validators;

import java.util.List;

import br.com.project.model.Exchange;
import br.com.project.service.exceptions.ExchangeRateInTheSameDateException;

public class ExchangeRateInTheSameDateValidator {

	public void validate(Exchange exchange, List<Exchange> exchangesInTheSameCurrency) {
		for (Exchange e : exchangesInTheSameCurrency) {
			if (e.getDate().compareTo(exchange.getDate()) == 0) {
				throw new ExchangeRateInTheSameDateException();
			}
		}
	}

}
