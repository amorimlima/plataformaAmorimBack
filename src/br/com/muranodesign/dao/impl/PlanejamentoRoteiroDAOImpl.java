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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanejamentoRoteiroDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanejamentoRoteiro;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class PlanejamentoRoteiroDAOImpl extends AbstractHibernateDAO implements PlanejamentoRoteiroDAO {

	/**
	 * Instantiates a new planejamento roteiro dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public PlanejamentoRoteiroDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listAll() {
		
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		List<PlanejamentoRoteiro> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#criar(br.com.muranodesign.model.PlanejamentoRoteiro)
	 */
	public void criar(PlanejamentoRoteiro c) {
		synchronized (PlanejamentoRoteiroDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#deletar(br.com.muranodesign.model.PlanejamentoRoteiro)
	 */
	public void deletar(PlanejamentoRoteiro c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#atualizar(br.com.muranodesign.model.PlanejamentoRoteiro)
	 */
	public void atualizar(PlanejamentoRoteiro p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarKey(int key){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idplanejamentoRoteiro", key));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarAlunoTotal(int)
	 */
	public long listarAlunoTotal(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", id));
		criteria.add(Restrictions.isNotNull("objetivo"));
		criteria.setProjection(Projections.count("idAluno"));
	
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarAlunoCompletos(int)
	 */
	public /*List<PlanejamentoRoteiro>*/long listarAlunoCompletos(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", id));
		criteria.add(Restrictions.isNotNull("objetivo"));
		criteria.add(Restrictions.eq("status", "2"));
		criteria.setProjection(Projections.count("idAluno"));
		//List<PlanejamentoRoteiro> result = criteria.list();
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarAlunoCorrigidos(int)
	 */
	public /*List<PlanejamentoRoteiro>*/long listarAlunoCorrigidos(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", id));
		criteria.add(Restrictions.isNotNull("objetivo"));
		criteria.add(Restrictions.eq("status", "3"));
		criteria.setProjection(Projections.count("idAluno"));
		//List<PlanejamentoRoteiro> result = criteria.list();
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarIdAluno(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", id));
		criteria.add(Restrictions.isNotNull("objetivo"));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarStatus(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarStatus(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", id));
		criteria.add(Restrictions.eq("status", "1"));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarPendente(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarPendente(int aluno, int objetivo){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("idAluno", aluno));
		criteria.createAlias("objetivo", "objetivo");
		criteria.add(Restrictions.eq("objetivo.idobjetivo",objetivo));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	
	}
		
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarPlanoEstudo(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarPlanoEstudo(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.createAlias("planoEstudo", "planoEstudo");
		criteria.add(Restrictions.eq("planoEstudo.idplanoEstudo", id));
		criteria.add(Restrictions.eq("status", "2"));
		criteria.add(Restrictions.eq("status", "3"));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarPlanoEstudoTotal(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarPlanoEstudoTotal(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.createAlias("planoEstudo", "planoEstudo");
		criteria.add(Restrictions.eq("planoEstudo.idplanoEstudo", id));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarObjetivoPendente(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarObjetivoPendente(int obj){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("status", "2"));
		criteria.createAlias("objetivo", "objetivo");
		criteria.add(Restrictions.eq("objetivo.idobjetivo",obj));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarObjetivoCompleto(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarObjetivoCompleto(int obj){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("status", "3"));
		criteria.createAlias("objetivo", "objetivo");
		criteria.add(Restrictions.eq("objetivo.idobjetivo",obj));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoRoteiroDAO#listarObjetivoTotal(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoRoteiro> listarObjetivoTotal(int obj){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.createAlias("objetivo", "objetivo");
		criteria.add(Restrictions.eq("objetivo.idobjetivo",obj));
		List<PlanejamentoRoteiro> result = criteria.list();
		return result;
	}



}