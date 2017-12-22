package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AprovacaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Aprovacao;
import br.com.muranodesign.model.Compensacao;
import br.com.muranodesign.model.Conceito;

public class AprovacaoDAOImpl extends AbstractHibernateDAO implements AprovacaoDAO {

	public AprovacaoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}

	@Override
	public List<Aprovacao> listAll() {
		Criteria criteria = getSession().createCriteria(Conceito.class);
		List <Aprovacao> result = criteria.list();
		return result;
	}

	@Override
	public List<Aprovacao> listarKey(int id) {
		Criteria criteria = getSession().createCriteria(Compensacao.class);
		criteria.add(Restrictions.eq("idAprovacao", id));
		List <Aprovacao> result = criteria.list();
		return result;
	}
	
}
