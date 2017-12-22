package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.TipoOficinaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.TipoOficina;

public class TipoOficinaDAOImpl extends AbstractHibernateDAO implements TipoOficinaDAO {

	public TipoOficinaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoOficina> listAll() {
		
		Criteria criteria = getSession().createCriteria(TipoOficina.class);
		List<TipoOficina> result = criteria.list();
		
		
		return result;
	}

	@Override
	public TipoOficina listarKey(int key) {
		
		Criteria criteria = getSession().createCriteria(TipoOficina.class);
		criteria.add(Restrictions.eq("idTipoOficina", key));
		TipoOficina result = (TipoOficina)criteria.list().get(0);
		return result;
	}

}
