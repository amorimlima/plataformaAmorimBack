package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CompensacaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Compensacao;

public class CompensacaoDAOImpl extends AbstractHibernateDAO implements CompensacaoDAO {

	public CompensacaoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}

	@Override
	public void criar(Compensacao resultado) {
		synchronized (CompensacaoDAOImpl.class) {
			getSession().persist(resultado);
			getSession().flush();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compensacao> listarKey(int id) {
		Criteria criteria = getSession().createCriteria(Compensacao.class);
		criteria.add(Restrictions.eq("idCompensacao", id));
		List <Compensacao> result = criteria.list();
		return result;
	}

	@Override
	public void update(Compensacao resultado) {
		synchronized (CompensacaoDAOImpl.class) {
			getSession().merge(resultado);
			getSession().flush();

		}
	}

	@Override
	public void deletar(Compensacao resultado) {
		synchronized (CompensacaoDAOImpl.class) {
			getSession().delete(resultado);
			getSession().flush();

		}
	}

	@Override
	public List<Compensacao> listAll() {
		Criteria criteria = getSession().createCriteria(Compensacao.class);
		List <Compensacao> result = criteria.list();
		return result;
	}

}
