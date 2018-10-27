package br.com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.project.dao.CurrencyDao;
import br.com.project.dao.ExchangeDao;
import br.com.project.model.Currency;
import br.com.project.service.exceptions.RemoveCurrencyUsedInExchangeException;

@Stateless
public class CurrencyService {

	@Inject
	private CurrencyDao dao;
	
	@Inject
	private ExchangeDao exchangeDao;

	public void insert(Currency currency) {
		this.dao.save(currency);
	}
	
	public void remove(Currency currency) {
		if (exchangeDao.findByCurrency(currency).size() > 0) {
			throw new RemoveCurrencyUsedInExchangeException();
		}
		this.dao.remove(currency);
	}

	public List<Currency> getAll() {
		return this.dao.getAll();
	}

	public Currency findById(Integer currencyId) {
		return this.dao.findById(currencyId);
	}
}
