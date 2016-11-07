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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.muranodesign.dao.ProfessorFuncionarioDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ProfessorFuncionario;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class ProfessorFuncionarioDAOImpl extends AbstractHibernateDAO implements ProfessorFuncionarioDAO {

	/**
	 * Instantiates a new professor funcionario dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public ProfessorFuncionarioDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ProfessorFuncionario> listAll() {
		
		
		Criteria criteria = getSession().createCriteria(ProfessorFuncionario.class);
	    ProjectionList projList = Projections.projectionList();  
	    projList.add(Projections.property("idprofessorFuncionario"),"idprofessorFuncionario"); 
	    projList.add(Projections.property("nome"),"nome"); 
	    criteria.setProjection(projList);
	    
	    criteria.setResultTransformer(Transformers.aliasToBean(ProfessorFuncionario.class));  
	    List<ProfessorFuncionario> results = criteria.list();
	  
	    
		return results;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#criar(br.com.muranodesign.model.ProfessorFuncionario)
	 */
	public void criar(ProfessorFuncionario c) {
		synchronized (ProfessorFuncionarioDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#deletar(br.com.muranodesign.model.ProfessorFuncionario)
	 */
	public void deletar(ProfessorFuncionario c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#atualizar(br.com.muranodesign.model.ProfessorFuncionario)
	 */
	public void atualizar(ProfessorFuncionario p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProfessorFuncionario> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ProfessorFuncionario.class);
		criteria.add(Restrictions.eq("idprofessorFuncionario", key));
		List<ProfessorFuncionario> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProfessorFuncionarioDAO#listarIdNome()
	 */
	@SuppressWarnings("unchecked")
	public List<ProfessorFuncionario> listarIdNome(){
		Criteria criteria = getSession().createCriteria(ProfessorFuncionario.class);
		 ProjectionList projList = Projections.projectionList();  
		 projList.add(Projections.property("idprofessorFuncionario"),"idprofessorFuncionario"); 
		 projList.add(Projections.property("nome"),"nome"); 
		 
		 criteria.setProjection(projList);
		    
		 criteria.setResultTransformer(Transformers.aliasToBean(ProfessorFuncionario.class));  
		 List<ProfessorFuncionario> results = criteria.list();
		
		 return results;
	}
	
}