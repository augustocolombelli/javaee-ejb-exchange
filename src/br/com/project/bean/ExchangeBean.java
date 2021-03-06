package br.com.project.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.project.model.Currency;
import br.com.project.model.Exchange;
import br.com.project.service.CurrencyService;
import br.com.project.service.ExchangeService;

@Model
public class ExchangeBean {

	private Exchange exchange = new Exchange();
	private Integer currencyId;

	@Inject
	private ExchangeService service;

	@Inject
	private CurrencyService currencyService;

	public void insert() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Currency currency = currencyService.findById(currencyId);
			this.exchange.setCurrency(currency);
			service.insert(this.exchange);
			this.exchange = new Exchange();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getCause().getMessage()));
		}
	}
	
	public void remove(Exchange exchangeToRemove) {
		service.remove(exchangeToRemove);
	}

	public List<Exchange> getAllExchanges() {
		return service.getAll();
	}
	
	public List<Currency> getCurrencies(){
		return currencyService.getAll();
	}

	public Exchange getExchange() {
		return exchange;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	


}
