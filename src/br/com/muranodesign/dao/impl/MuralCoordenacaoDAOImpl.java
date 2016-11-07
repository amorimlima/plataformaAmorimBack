package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MuralCoordenacaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.MuralCoordenacao;

public class MuralCoordenacaoDAOImpl extends AbstractHibernateDAO implements MuralCoordenacaoDAO {

	public MuralCoordenacaoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralCoordenacaoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<MuralCoordenacao> listAll() {
		Criteria criteria = getSession().createCriteria(MuralCoordenacao.class);
		List<MuralCoordenacao> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralCoordenacaoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<MuralCoordenacao> listarKey(int key) {
		Criteria criteria = getSession().createCriteria(MuralCoordenacao.class);
		criteria.add(Restrictions.eq("IdMuralCoordenacao", key));
		List<MuralCoordenacao> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralCoordenacaoDAO#criar(br.com.muranodesign.model.MuralCoordenacao)
	 */
	public void criar(MuralCoordenacao p) {
		synchronized (MuralCoordenacaoDAOImpl.class){
			getSession().persist(p);
			getSession().flush();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralCoordenacaoDAO#atualizar(br.com.muranodesign.model.MuralCoordenacao)
	 */
	public void atualizar(MuralCoordenacao p) {
		getSession().merge(p);
		getSession().flush();
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralCoordenacaoDAO#deletar(br.com.muranodesign.model.MuralCoordenacao)
	 */
	public void deletar(MuralCoordenacao p) {
		getSession().delete(p);
		getSession().flush();
	}

}
