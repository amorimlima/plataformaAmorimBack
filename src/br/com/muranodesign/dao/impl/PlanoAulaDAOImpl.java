package br.com.muranodesign.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanoAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanoAula;

public class PlanoAulaDAOImpl extends AbstractHibernateDAO implements PlanoAulaDAO{

	public PlanoAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PlanoAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		List<PlanoAula> result = criteria.list();
		
		
		return result;
	} 

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#criar(br.com.muranodesign.model.PlanoAula)
	 */
	public void criar(PlanoAula c) {
		synchronized (PlanoAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();
	
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#deletar(br.com.muranodesign.model.PlanoAula)
	 */
	public void deletar(PlanoAula c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#atualizar(br.com.muranodesign.model.PlanoAula)
	 */
	public void atualizar(PlanoAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanoAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.add(Restrictions.eq("Idplano_aula", key));
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#range(java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanoAula> range(Date data){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.add(Restrictions.gt("data_fim",data));
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#listarUltimo(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanoAula> listarUltimo(int idProfessor){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		
		criteria.addOrder(Order.desc( "data_ini" ) ); 
		criteria.setMaxResults(1);
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanoAulaDAO#listarProfessor(int, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanoAula> listarProfessor(int idProfessor, Date data){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.add(Restrictions.gt("data_ini",data));
		List<PlanoAula> result = criteria.list();
		return result;
	}
}
