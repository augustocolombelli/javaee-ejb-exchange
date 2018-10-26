package br.com.project.bean.chart;

import java.util.ArrayList;
import java.util.List;

public class ExchangeSerie {

	private String label;
	private List<ExchangeValue> exchangeValues;

	public ExchangeSerie() {
		exchangeValues = new ArrayList<ExchangeValue>();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ExchangeValue> getExchangeValues() {
		return exchangeValues;
	}

	public void addExchangeValue(ExchangeValue exchangeValue) {
		this.exchangeValues.add(exchangeValue);
	}

}
