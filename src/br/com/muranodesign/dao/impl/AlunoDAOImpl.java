/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package br.com.muranodesign.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.muranodesign.dao.AlunoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Aluno;

/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

public class AlunoDAOImpl extends AbstractHibernateDAO implements AlunoDAO {

	/**
	 * Instantiates a new aluno dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public AlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listAll() {
		
		Criteria criteria = getSession().createCriteria(Aluno.class);
		List<Aluno> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#listIntervalo(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listIntervalo(int primeiro, int ultimo){
		Criteria criteria = getSession().createCriteria(Aluno.class);
		
		criteria.add(Restrictions.eq("ativo", "s"));
		
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		
		List<Aluno> result = criteria.list();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#listAllLike(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listAllLike(String letra){
		Criteria criteria = getSession().createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("ativo", "s"));
		criteria.add(Restrictions.like("nome", letra, MatchMode.ANYWHERE));
		List<Aluno> result = criteria.list();
		return result;
	}
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#criar(br.com.muranodesign.model.Aluno)
	 */
	public void criar(Aluno c) {
		synchronized (AlunoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#deletar(br.com.muranodesign.model.Aluno)
	 */
	public void deletar(Aluno c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#atualizar(br.com.muranodesign.model.Aluno)
	 */
	public void atualizar(Aluno p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("idAluno", key));
		List<Aluno> result = criteria.list();
		return result;
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#listarAluno(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listarAluno(String user) {
		
		Criteria criteria = getSession().createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("userName", user));
		//.add(Order.asc("alarmeLevel"));
		List<Aluno> result = criteria.list();
		
		
		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoDAO#ListarNomeId()
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> ListarNomeId(){
		Criteria criteria = getSession().createCriteria(Aluno.class);
		 ProjectionList projList = Projections.projectionList();  
		 projList.add(Projections.property("idAluno"),"idAluno"); 
		 projList.add(Projections.property("nome"),"nome"); 
		 
		 criteria.setProjection(projList);
		    
	    criteria.setResultTransformer(Transformers.aliasToBean(Aluno.class));  
	    List<Aluno> results = criteria.list();
	    
	    return results;
	}

}