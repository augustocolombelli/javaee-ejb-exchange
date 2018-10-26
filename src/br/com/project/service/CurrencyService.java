package br.com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.project.dao.CurrencyDao;
import br.com.project.model.Currency;

@Stateless
public class CurrencyService {

	@Inject
	private CurrencyDao dao;

	public void insert(Currency currency) {
		this.dao.save(currency);
	}

	public List<Currency> getAll() {
		return this.dao.getAll();
	}

	public Currency findById(Integer currencyId) {
		return this.dao.findById(currencyId);
	}
}
