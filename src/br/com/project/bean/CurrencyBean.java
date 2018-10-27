package br.com.project.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.print.attribute.standard.Severity;

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

	public void remove(Currency currency) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			service.remove(currency);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getCause().getMessage()));
		}
	}

	public List<Currency> getAllCurrencies() {
		return service.getAll();
	}

	public Currency getCurrency() {
		return currency;
	}
}
