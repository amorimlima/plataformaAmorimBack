package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ConceitoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Compensacao;
import br.com.muranodesign.model.Conceito;

public class ConceitoDAOImpl extends AbstractHibernateDAO  implements ConceitoDAO {
	
	public ConceitoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conceito> listAll() {
		Criteria criteria = getSession().createCriteria(Conceito.class);
		List <Conceito> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conceito> listarKey(int id) {
		Criteria criteria = getSession().createCriteria(Compensacao.class);
		criteria.add(Restrictions.eq("idConceito", id));
		List <Conceito> result = criteria.list();
		return result;
	}
	
	

}
