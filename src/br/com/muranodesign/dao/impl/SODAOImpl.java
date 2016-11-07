package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.SODAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.SO;

public class SODAOImpl extends AbstractHibernateDAO implements SODAO {
	
	public SODAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<SO> listAll(){
		Criteria criteria = getSession().createCriteria(SO.class);
		List<SO> result = criteria.list();

		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#criar(br.com.muranodesign.model.SO)
	 */
	public void criar(SO c) {
		synchronized (RoteiroDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#deletar(br.com.muranodesign.model.SO)
	 */
	public void deletar(SO c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#atualizar(br.com.muranodesign.model.SO)
	 */
	public void atualizar(SO p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<SO> listarSo(String so){
		Criteria criteria = getSession().createCriteria(SO.class);
		criteria.add(Restrictions.eq("so", so));
		List<SO> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#listarid(int)
	 */
	@SuppressWarnings("unchecked")
	public List<SO> listarid(int idso){
		Criteria criteria = getSession().createCriteria(SO.class);
		criteria.add(Restrictions.eq("idso", idso));
		List<SO> result = criteria.list();
		return result;
	}

	
}
