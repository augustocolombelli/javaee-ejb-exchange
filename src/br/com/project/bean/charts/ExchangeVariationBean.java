package br.com.project.bean.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.project.model.Exchange;
import br.com.project.service.ExchangeService;

@Model
public class ExchangeVariationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Inject
	private ExchangeService exchangeService;
	
//	public LineChartModel getDataModel() {
//		generateChart();
//		return dataModel;
//	}
	
	public LineChartModel getLineChartModel() {
		List<Exchange> exchanges = exchangeService.getAll();
		List<ExchangeVariationSerie> exchangeSeries = generateDataToChart(exchanges);
		
		LineChartModel dataModel;
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
		
		return dataModel;
	}
	
	private List<ExchangeVariationSerie> generateDataToChart(List<Exchange> exchanges){
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
	


}
