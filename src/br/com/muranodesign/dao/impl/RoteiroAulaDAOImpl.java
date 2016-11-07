package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.RoteiroAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.RoteiroAula;

public class RoteiroAulaDAOImpl extends AbstractHibernateDAO implements  RoteiroAulaDAO{
	
	public RoteiroAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarLikeRoteiro(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarLikeRoteiro(String letra){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.add(Restrictions.like("roteiro", letra, MatchMode.START));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		
		
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#criar(br.com.muranodesign.model.RoteiroAula)
	 */
	public void criar(RoteiroAula c) {
		synchronized (RoteiroAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
/*
 * (non-Javadoc)
 * @see br.com.muranodesign.dao.RoteiroAulaDAO#deletar(br.com.muranodesign.model.RoteiroAula)
 */
	public void deletar(RoteiroAula c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#atualizar(br.com.muranodesign.model.RoteiroAula)
	 */
	public void atualizar(RoteiroAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.add(Restrictions.eq("Idroteiro_aula", key));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarOficinaProfessor(int)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarOficinaProfessor(int id){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.createAlias("Oficinaprofessor", "Oficinaprofessor");
		criteria.add(Restrictions.eq("Oficinaprofessor.Idoficina_professor", id));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarNaoOficinaProfessor(int)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarNaoOficinaProfessor(int id){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.createAlias("Oficinaprofessor", "Oficinaprofessor");
		criteria.add(Restrictions.ne("Oficinaprofessor.Idoficina_professor", id));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarLike(int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarLike(int idOficinaProfessor, String letras){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.createAlias("Oficinaprofessor", "Oficinaprofessor");
		criteria.add(Restrictions.eq("Oficinaprofessor.Idoficina_professor", idOficinaProfessor));
		criteria.add(Restrictions.like("roteiro", letras, MatchMode.START));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;

	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarOficinaProfessorLike(int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarOficinaProfessorLike(int id, String letra){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.createAlias("Oficinaprofessor", "Oficinaprofessor");
		criteria.add(Restrictions.eq("Oficinaprofessor.Idoficina_professor", id));
		criteria.add(Restrictions.like("roteiro", letra, MatchMode.START));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RoteiroAulaDAO#listarNaoOficinaProfessorLike(int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoteiroAula> listarNaoOficinaProfessorLike(int id, String letra){
		Criteria criteria = getSession().createCriteria(RoteiroAula.class);
		criteria.createAlias("Oficinaprofessor", "Oficinaprofessor");
		criteria.add(Restrictions.ne("Oficinaprofessor.Idoficina_professor", id));
		criteria.add(Restrictions.like("roteiro", letra, MatchMode.START));
		criteria.add(Restrictions.eq("status", 0));
		List<RoteiroAula> result = criteria.list();
		return result;
	}
	
}
