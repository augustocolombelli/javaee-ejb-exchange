package br.com.project.bean.charts;

import java.util.ArrayList;
import java.util.List;

public class ExchangeVariationSerie {

	private String label;
	private List<ExchangeVariationValue> exchangeValues;

	public ExchangeVariationSerie() {
		exchangeValues = new ArrayList<ExchangeVariationValue>();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ExchangeVariationValue> getExchangeValues() {
		return exchangeValues;
	}

	public void addExchangeValue(ExchangeVariationValue exchangeValue) {
		this.exchangeValues.add(exchangeValue);
	}

}
