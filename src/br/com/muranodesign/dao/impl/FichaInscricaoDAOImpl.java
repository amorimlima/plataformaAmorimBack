package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.dao.FichaInscricaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.FichaInscricao;

public class FichaInscricaoDAOImpl extends AbstractHibernateDAO implements FichaInscricaoDAO {
	
	public FichaInscricaoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.FichaInscricaoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<FichaInscricao> listAll() {
		
		Criteria criteria = getSession().createCriteria(FichaInscricao.class);
		List<FichaInscricao> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.FichaInscricaoDAO#criar(br.com.muranodesign.model.FichaInscricao)
	 */
	public void criar(FichaInscricao c) {
		synchronized (FichaInscricaoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.FichaInscricaoDAO#deletar(br.com.muranodesign.model.FichaInscricao)
	 */
	public void deletar(FichaInscricao c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.FichaInscricaoDAO#atualizar(br.com.muranodesign.model.FichaInscricao)
	 */
	public void atualizar(FichaInscricao p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.FichaInscricaoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<FichaInscricao> listarKey(int key){
		Criteria criteria = getSession().createCriteria(FichaInscricao.class);
		criteria.add(Restrictions.eq("roteiro", new RoteiroService().listarkey(key).get(0)));
		List<FichaInscricao> result = criteria.list();
		return result;
	}
	
}
