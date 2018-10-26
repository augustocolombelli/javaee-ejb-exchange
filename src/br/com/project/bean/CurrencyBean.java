package br.com.project.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.project.model.Currency;
import br.com.project.service.CurrencyService;

@Model
public class CurrencyBean {

	private Currency currency = new Currency();

	@Inject
	private CurrencyService service;
	
	public void insert() {
		service.insert(this.currency);
		this.currency = new Currency();
	}
	
	public List<Currency> getAllCurrencies(){
		return service.getAll();
	}
	
	public Currency getCurrency() {
		return currency;
	}
}
