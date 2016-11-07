package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SemanaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Semana;

public class SemanaDAOImpl extends AbstractHibernateDAO implements SemanaDAO{
	
	public SemanaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SemanaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Semana> listAll() {
		
		Criteria criteria = getSession().createCriteria(Semana.class);
		List<Semana> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SemanaDAO#criar(br.com.muranodesign.model.Semana)
	 */
	public void criar(Semana c) {
		synchronized (SemanaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SemanaDAO#deletar(br.com.muranodesign.model.Semana)
	 */
	public void deletar(Semana c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SemanaDAO#atualizar(br.com.muranodesign.model.Semana)
	 */
	public void atualizar(Semana p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SemanaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Semana> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Semana.class);
		criteria.add(Restrictions.eq("Idsemana", key));
		List<Semana> result = criteria.list();
		return result;
	}

}
