package br.com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.project.dao.ExchangeDao;
import br.com.project.model.Exchange;
import br.com.project.service.interceptors.ExchangeLogInterceptor;
import br.com.project.service.validators.ExchangeRateInTheSameDateValidator;

@Stateless
@Interceptors({ExchangeLogInterceptor.class})
public class ExchangeService {

	@Inject
	private ExchangeDao dao;

	public void insert(Exchange exchange) {
		List<Exchange> exchangesInTheSameCurrency = dao.findByCurrency(exchange.getCurrency());
		new ExchangeRateInTheSameDateValidator().validate(exchange, exchangesInTheSameCurrency);
		
		this.dao.save(exchange);
	}

	public void remove(Exchange exchange) {
		this.dao.remove(exchange);
	}

	public List<Exchange> getAll() {
		return this.dao.getAll();
	}
}
