package br.com.project.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.project.model.Exchange;

@Stateless
public class ExchangeDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Exchange exchange) {
		manager.persist(exchange);
	}
	
	public void remove(Exchange exchange) {
		manager.remove(manager.contains(exchange) ? exchange : manager.merge(exchange));
	}

	public List<Exchange> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e ");
		sql.append("FROM Exchange e ");
		sql.append("ORDER BY e.date DESC ");

		return manager.createQuery(sql.toString(), Exchange.class).getResultList();
	}
}
