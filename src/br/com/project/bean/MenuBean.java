package br.com.project.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MenuBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public String indexPage() {
		return "index?faces-redirect=true";
	}
	
	public String currencyPage() {
		return "currency?faces-redirect=true";
	}

	public String exchangePage() {
		return "exchange?faces-redirect=true";
	}
	
	public String exchangeVariationPage() {
		return "exchangeVariation?faces-redirect=true";
	}
}
