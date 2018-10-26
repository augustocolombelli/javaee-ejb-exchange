package br.com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.project.dao.ExchangeDao;
import br.com.project.model.Exchange;

@Stateless
public class ExchangeService {

	@Inject
	private ExchangeDao dao;

	public void insert(Exchange exchange) {
		this.dao.save(exchange);
	}

	public void remove(Exchange exchange) {
		this.dao.remove(exchange);
	}
	
	public List<Exchange> getAll() {
		return this.dao.getAll();
	}
}
