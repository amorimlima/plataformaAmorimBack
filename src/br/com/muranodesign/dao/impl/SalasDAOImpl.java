package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SalasDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Salas;

public class SalasDAOImpl extends AbstractHibernateDAO implements SalasDAO{
	
	public SalasDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SalasDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Salas> listAll() {
		
		Criteria criteria = getSession().createCriteria(Salas.class);
		List<Salas> result = criteria.list();
		
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SalasDAO#criar(br.com.muranodesign.model.Salas)
	 */
	public void criar(Salas c) {
		synchronized (SalasDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SalasDAO#deletar(br.com.muranodesign.model.Salas)
	 */
	public void deletar(Salas c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SalasDAO#atualizar(br.com.muranodesign.model.Salas)
	 */
	public void atualizar(Salas p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SalasDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Salas> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Salas.class);
		criteria.add(Restrictions.eq("Idsalas", key));
		List<Salas> result = criteria.list();
		return result;
	}
	
}
