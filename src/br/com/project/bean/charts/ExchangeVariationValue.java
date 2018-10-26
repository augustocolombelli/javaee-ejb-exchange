package br.com.project.bean.charts;

import java.util.Date;

public class ExchangeVariationValue {

	private Date date;
	private Double value;

	public ExchangeVariationValue(Date date, Double value) {
		this.date = date;
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
