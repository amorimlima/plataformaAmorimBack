package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.HistoricoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.SO;

public class HistoricoDAOImpl extends AbstractHibernateDAO implements HistoricoDAO{
	
	public HistoricoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Historico_conexao> listAll() {
		
		Criteria criteria = getSession().createCriteria(Historico_conexao.class);
		List<Historico_conexao> result = criteria.list();

		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#criar(br.com.muranodesign.model.SO)
	 */
	public void criar(Historico_conexao c) {
		synchronized (RoteiroDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#deletar(br.com.muranodesign.model.SO)
	 */
	public void deletar(Historico_conexao c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#atualizar(br.com.muranodesign.model.SO)
	 */
	public void atualizar(Historico_conexao p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.SODAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Historico_conexao> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Historico_conexao.class);
		criteria.add(Restrictions.eq("idConexao", key));
		List<Historico_conexao> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoDAO#listarQtdAcessoEscola(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Historico_conexao> listarQtdAcessoEscola(String data){
		Criteria criteria = getSession().createCriteria(Historico_conexao.class);
		criteria.add(Restrictions.isNotNull("cnx_escola"));
		criteria.add(Restrictions.eq("data", data));
		List<Historico_conexao> result = criteria.list();
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoDAO#listarQtdAcessoExterno(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Historico_conexao> listarQtdAcessoExterno(String data){
		Criteria criteria = getSession().createCriteria(Historico_conexao.class);
		criteria.add(Restrictions.isNotNull("cnx_externo"));
		criteria.add(Restrictions.eq("data", data));
		List<Historico_conexao> result = criteria.list();
		return result;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.HistoricoDAO#listarSo(br.com.muranodesign.model.SO)
	 */
	@SuppressWarnings("unchecked")
	public List<Historico_conexao> listarSo(SO so){
		Criteria criteria = getSession().createCriteria(Historico_conexao.class);
		criteria.add(Restrictions.eq("s_o", so));
		List<Historico_conexao> result = criteria.list();
		return result;
		
	}
	
}
