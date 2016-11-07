package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AgendamentoSalaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AgendamentoSala;



public class AgendamentoSalaDAOImpl extends AbstractHibernateDAO implements AgendamentoSalaDAO{

	public AgendamentoSalaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AgendamentoSala> listAll() {
		
		Criteria criteria = getSession().createCriteria(AgendamentoSala.class);
		List<AgendamentoSala> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#criar(br.com.muranodesign.model.AgendamentoSala)
	 */
	public void criar(AgendamentoSala c) {
		synchronized (AgendamentoSalaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#deletar(br.com.muranodesign.model.AgendamentoSala)
	 */
	public void deletar(AgendamentoSala c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#atualizar(br.com.muranodesign.model.AgendamentoSala)
	 */
	public void atualizar(AgendamentoSala p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AgendamentoSala> listarKey(int key){
		Criteria criteria = getSession().createCriteria(AgendamentoSala.class);
		criteria.add(Restrictions.eq("Idagendamento_sala", key));
		List<AgendamentoSala> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgendamentoSalaDAO#listarValidacao(int, int, long)
	 */
	@SuppressWarnings("unchecked")
	public List<AgendamentoSala> listarValidacao(int dia, int sala, long hora){
		Criteria criteria = getSession().createCriteria(AgendamentoSala.class);
		criteria.add(Restrictions.eq("hora", hora));
		criteria.createAlias("dia", "dia");
		criteria.add(Restrictions.eq("dia.Idsemana", dia));
		criteria.createAlias("sala", "sala");
		criteria.add(Restrictions.eq("sala.Idsalas", sala));
		List<AgendamentoSala> result = criteria.list();
		return result;
	}
}
