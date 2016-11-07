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

import br.com.muranodesign.dao.ObjetivoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Objetivo;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class ObjetivoDAOImpl extends AbstractHibernateDAO implements ObjetivoDAO {

	/**
	 * Instantiates a new objetivo dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public ObjetivoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Objetivo> listAll() {
		
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.add(Restrictions.eq("ativo",1));
		//criteria.setCacheable(true);
		
		List<Objetivo> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listAllTeste()
	 */
	@SuppressWarnings("unchecked")
	public List<Objetivo> listAllTeste(){
		
		Criteria criteria = getSession().createCriteria(Objetivo.class);
	    ProjectionList projList = Projections.projectionList();  
	    projList.add(Projections.property("descricao"),"descricao"); 
	    criteria.createAlias("roteiro", "roteiro");
	    projList.add(Projections.property("roteiro.idroteiro"));  
	    criteria.setProjection(projList).setCacheable(true);
	    criteria.setResultTransformer(Transformers.aliasToBean(Objetivo.class));  
	    
	    List<Objetivo> results = criteria.list();
	    
		return results; 
	
	}
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#criar(br.com.muranodesign.model.Objetivo)
	 */
	public void criar(Objetivo c) {
		synchronized (ObjetivoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#deletar(br.com.muranodesign.model.Objetivo)
	 */
	public void deletar(Objetivo c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#atualizar(br.com.muranodesign.model.Objetivo)
	 */
	public void atualizar(Objetivo p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Objetivo> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.add(Restrictions.eq("idobjetivo", key));
		List<Objetivo> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listarRoteiro(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Objetivo> listarRoteiro(int id){
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.idroteiro", id));
		
		
		criteria.add(Restrictions.eq("ativo", 1));
		List<Objetivo> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listarRoteiroTotal(int)
	 */
	public long listarRoteiroTotal(int id){
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.idroteiro", id));
		criteria.add(Restrictions.eq("roteiro.ativo", 1));
		
		criteria.setProjection(Projections.count("ativo"));
		
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listarGrafico(int)
	 */
	public long listarGrafico(int id){
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.add(Restrictions.eq("ativo",1));
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.ativo", 1));
		criteria.add(Restrictions.eq("roteiro.anoEstudo.idanoEstudo", id));
		criteria.setProjection(Projections.count("ativo"));
		
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ObjetivoDAO#listarGraficoEntregues(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Objetivo> listarGraficoEntregues(int id){
		Criteria criteria = getSession().createCriteria(Objetivo.class);
		criteria.add(Restrictions.eq("ativo",1));
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.ativo", 1));
		criteria.add(Restrictions.eq("roteiro.anoEstudo.idanoEstudo", id));
		
		List<Objetivo> result = criteria.list();
		return result;
	}
	



}