package br.com.project.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.project.model.Currency;

@Stateless
public class CurrencyDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Currency currency) {
		manager.persist(currency);
	}

	public List<Currency> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c ");
		sql.append("FROM Currency c ");

		return manager.createQuery(sql.toString(), Currency.class).getResultList();
	}

}
