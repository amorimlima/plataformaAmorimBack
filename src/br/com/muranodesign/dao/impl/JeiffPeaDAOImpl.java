package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.JeiffPeaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.JeiffPea;

public class JeiffPeaDAOImpl extends AbstractHibernateDAO implements JeiffPeaDAO {

	public JeiffPeaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}
	
	@SuppressWarnings("unchecked")
	public List<JeiffPea> listarTodos() {
		Criteria criteria = getSession().createCriteria(JeiffPea.class);
		criteria.addOrder(Order.desc("data"));
		List<JeiffPea> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<JeiffPea> listarkey(int id) {
		Criteria criteria = getSession().createCriteria(JeiffPea.class);
		criteria.add(Restrictions.eq("idJeiffPea", id));
		List<JeiffPea> resultado = criteria.list();
		return resultado;
	}

	@Override
	public void criar(JeiffPea jp) {
		synchronized (JeiffPeaDAOImpl.class) {
			getSession().persist(jp);
			getSession().flush();
		}
	}

	@Override
	public void atualizar(JeiffPea jp) {
		synchronized (JeiffPeaDAOImpl.class) {
			getSession().merge(jp);
			getSession().flush();
		}
	}

	@Override
	public void deletar(JeiffPea jp) {
		synchronized (JeiffPeaDAOImpl.class) {
			getSession().delete(jp);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	public List<JeiffPea> listarPeriodo(int idperiodo) {
		Criteria criteria = getSession().createCriteria(JeiffPea.class);
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", idperiodo));
		criteria.addOrder(Order.desc("data"));
		List<JeiffPea> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<Object> listarAnexos() {
		Criteria criteria = getSession().createCriteria(JeiffPea.class);
		criteria.addOrder(Order.desc("data"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("arquivo")).add(Projections.property("descricao")));
		//criteria.setProjection(Projections.property("arquivo"));
		List<Object> resultado = criteria.list();
		return resultado;
	}

}
