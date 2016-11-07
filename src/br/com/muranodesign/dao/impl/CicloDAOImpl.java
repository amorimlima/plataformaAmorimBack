package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CicloDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Ciclos;


public class CicloDAOImpl extends AbstractHibernateDAO implements CicloDAO{
	
	public CicloDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CicloDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Ciclos> listAll() {
		
		Criteria criteria = getSession().createCriteria(Ciclos.class);
		List<Ciclos> result = criteria.list();
		
		
		return result;
	} 

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CicloDAO#criar(br.com.muranodesign.model.Ciclos)
	 */
	public void criar(Ciclos c) {
		synchronized (CicloDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CicloDAO#deletar(br.com.muranodesign.model.Ciclos)
	 */
	public void deletar(Ciclos c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CicloDAO#atualizar(br.com.muranodesign.model.Ciclos)
	 */
	public void atualizar(Ciclos p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CicloDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Ciclos> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Ciclos.class);
		criteria.add(Restrictions.eq("Idciclos", key));
		List<Ciclos> result = criteria.list();
		return result;
	}
}
