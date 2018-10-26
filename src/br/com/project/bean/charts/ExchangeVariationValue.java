package br.com.project.bean.chart;

import java.util.Date;

public class ExchangeValue {

	private Date date;
	private Double value;

	public ExchangeValue(Date date, Double value) {
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
