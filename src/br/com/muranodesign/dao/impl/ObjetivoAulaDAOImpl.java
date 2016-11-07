package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.muranodesign.dao.ObjetivoAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ObjetivoAula;

public class ObjetivoAulaDAOImpl extends AbstractHibernateDAO implements ObjetivoAulaDAO{

	public ObjetivoAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ObjetivoAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(ObjetivoAula.class);
		List<ObjetivoAula> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#criar(br.com.muranodesign.model.ObjetivoAula)
	 */
	public void criar(ObjetivoAula c) {
		synchronized (ObjetivoAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#deletar(br.com.muranodesign.model.ObjetivoAula)
	 */
	public void deletar(ObjetivoAula c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#atualizar(br.com.muranodesign.model.ObjetivoAula)
	 */
	public void atualizar(ObjetivoAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ObjetivoAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ObjetivoAula.class);
		criteria.add(Restrictions.eq("Idobjetivo_aula", key));
		List<ObjetivoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#listarPorRoteiro(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ObjetivoAula> listarPorRoteiro(int idroteiro){
		Criteria criteria = getSession().createCriteria(ObjetivoAula.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.Idroteiro_aula", idroteiro));
		List<ObjetivoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoAulaDAO#listarPorRoteiroLazy(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ObjetivoAula> listarPorRoteiroLazy(int id){
		
		Criteria criteria = getSession().createCriteria(ObjetivoAula.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.Idroteiro_aula", id));
	    ProjectionList projList = Projections.projectionList();  
	    projList.add(Projections.property("objetivo"),"objetivo"); 
	    criteria.setProjection(projList);
	    
	    criteria.setResultTransformer(Transformers.aliasToBean(ObjetivoAula.class));  
	    List<ObjetivoAula> results = criteria.list();
	    
	    return results;
	}
}
