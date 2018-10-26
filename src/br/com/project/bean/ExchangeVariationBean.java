package br.com.project.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.resteasy.util.DateUtil;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.util.DateUtils;

import br.com.project.bean.charts.ExchangeVariationSerie;
import br.com.project.bean.charts.ExchangeVariationValue;
import br.com.project.model.Exchange;
import br.com.project.service.ExchangeService;

@Model
public class ExchangeVariationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private LineChartModel dataModel;

	@Inject
	private ExchangeService exchangeService;
	
	
	private void generateChart() {
		List<Exchange> exchanges = exchangeService.getAll();
		List<ExchangeVariationSerie> exchangeSeries = generateDataFromChart(exchanges);
		
		dataModel = new LineChartModel();

		for (ExchangeVariationSerie exchangeSerie : exchangeSeries) {
			LineChartSeries serie = new LineChartSeries();
			serie.setLabel(exchangeSerie.getLabel());

			for (ExchangeVariationValue exchangeValue : exchangeSerie.getExchangeValues()) {
				serie.set(exchangeValue.getDate(), exchangeValue.getValue());
			}
			dataModel.addSeries(serie);
		}
		
		dataModel.setTitle("Title...");
		dataModel.setZoom(true);
		dataModel.setLegendPosition("e");
		dataModel.getAxis(AxisType.Y).setLabel("Values");

		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%b %#d, %y");

		dataModel.getAxes().put(AxisType.X, axis);
		
	}
	
	private List<ExchangeVariationSerie> generateDataFromChart(List<Exchange> exchanges){
		List<ExchangeVariationSerie> exchangeSeries = new ArrayList<ExchangeVariationSerie>();
		
		for(Exchange exchange : exchanges) {
			if(containSerieWithLabel(exchange.getCurrency().getName(), exchangeSeries)) {
				ExchangeVariationSerie exchangeSerie = returnExchangeSerieByLabel(exchange.getCurrency().getName(), exchangeSeries);
				exchangeSerie.addExchangeValue(new ExchangeVariationValue(exchange.getDate(), exchange.getValue()));
			}else {
				ExchangeVariationSerie exchangeSerie = new ExchangeVariationSerie();
				exchangeSerie.setLabel(exchange.getCurrency().getName());
				exchangeSerie.addExchangeValue(new ExchangeVariationValue(exchange.getDate(), exchange.getValue()));
				exchangeSeries.add(exchangeSerie);
			}
		}
		
		return exchangeSeries;
	}
	
	private boolean containSerieWithLabel(String label, List<ExchangeVariationSerie> exchangeSeries) {
		for(ExchangeVariationSerie exchangeSerie : exchangeSeries) {
			if(exchangeSerie.getLabel().equals(label)) {
				return true;
			}
		}
		return false;
	}
	
	private ExchangeVariationSerie returnExchangeSerieByLabel(String label, List<ExchangeVariationSerie> exchangeSeries) {
		for(ExchangeVariationSerie exchangeSerie : exchangeSeries) {
			if(exchangeSerie.getLabel().equals(label)) {
				return exchangeSerie;
			}
		}
		return null;
	}
	
	public LineChartModel getDataModel() {
		generateChart();
		return dataModel;
	}
//
//	private void createDateModel() {
//		dataModel = new LineChartModel();
//
//		LineChartSeries serie1 = new LineChartSeries();
//		serie1.setLabel("Dolar");
//		serie1.set("2014-01-01", 10.2);
//		serie1.set("2014-01-06", 2.4);
//		serie1.set("2014-01-12", 4.4);
//		serie1.set("2014-01-18", 34.2);
//
//		LineChartSeries serie2 = new LineChartSeries();
//		serie2.setLabel("Real");
//		serie2.set("2014-01-01", 61.2);
//		serie2.set("2014-01-06", 32.4);
//		serie2.set("2014-01-12", 15.4);
//		serie2.set("2014-01-18", 24.2);
//
//		LineChartSeries serie3 = new LineChartSeries();
//		serie3.setLabel("Guarani");
//		serie3.set("2014-01-01", 5.2);
//		serie3.set("2014-01-06", 57.4);
//		serie3.set("2014-01-12", 72.4);
//		serie3.set("2014-01-18", 7.2);
//
//		dataModel.addSeries(serie1);
//		dataModel.addSeries(serie2);
//		dataModel.addSeries(serie3);
//
//		dataModel.setTitle("Title...");
//		dataModel.setZoom(true);
//		dataModel.setLegendPosition("e");
//		dataModel.getAxis(AxisType.Y).setLabel("Values");
//
//		DateAxis axis = new DateAxis("Dates");
//		axis.setTickAngle(-50);
//		axis.setMax("2014-02-01");
//		axis.setTickFormat("%b %#d, %y");
//
//		dataModel.getAxes().put(AxisType.X, axis);
//	}

}
