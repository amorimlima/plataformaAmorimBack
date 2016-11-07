package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.HistoricoEventosDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.historicoEventos;
public class HistoricoEventosDAOImpl extends AbstractHibernateDAO implements HistoricoEventosDAO{
	
	public HistoricoEventosDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<historicoEventos> listAll() {
		
		Criteria criteria = getSession().createCriteria(historicoEventos.class);
		List<historicoEventos> result = criteria.list();
		
		
		return result;
	} 
	
/*
 * (non-Javadoc)
 * @see br.com.muranodesign.dao.HistoricoEventosDAO#criar(br.com.muranodesign.model.historicoEventos)
 */
	public void criar(historicoEventos c) {
		synchronized (HistoricoEventosDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoEventosDAO#deletar(br.com.muranodesign.model.historicoEventos)
	 */
	public void deletar(historicoEventos c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoEventosDAO#atualizar(br.com.muranodesign.model.historicoEventos)
	 */
	public void atualizar(historicoEventos p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoEventosDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<historicoEventos> listarKey(int key){
		Criteria criteria = getSession().createCriteria(historicoEventos.class);
		criteria.add(Restrictions.eq("idHistEventos", key));
		List<historicoEventos> result = criteria.list();
		return result;
	}
	
}
