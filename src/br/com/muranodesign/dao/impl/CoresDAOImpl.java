package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CoresDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Cores;

public class CoresDAOImpl extends AbstractHibernateDAO implements CoresDAO {
	
	public CoresDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CoresDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Cores> listAll() {
		
		Criteria criteria = getSession().createCriteria(Cores.class);
		List<Cores> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#criar(br.com.muranodesign.model.Oficina)
	 */
	public void criar(Cores c) {
		synchronized (CoresDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#deletar(br.com.muranodesign.model.Oficina)
	 */
	public void deletar(Cores c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#atualizar(br.com.muranodesign.model.Oficina)
	 */
	public void atualizar(Cores p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Cores> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Cores.class);
		criteria.add(Restrictions.eq("Idcor", key));
		List<Cores> result = criteria.list();
		return result;
	}
	
}
