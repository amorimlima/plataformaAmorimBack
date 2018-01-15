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
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.dao.TutoriaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.Tutoria;


/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class TutoriaDAOImpl extends AbstractHibernateDAO implements TutoriaDAO {

	/**
	 * Instantiates a new tutoria dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public TutoriaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listAll() {
		
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.addOrder(Order.asc("tutoria"));
		List<Tutoria> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#criar(br.com.muranodesign.model.Tutoria)
	 */
	public void criar(Tutoria c) {
		synchronized (TutoriaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#deletar(br.com.muranodesign.model.Tutoria)
	 */
	public void deletar(Tutoria c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#atualizar(br.com.muranodesign.model.Tutoria)
	 */
	public void atualizar(Tutoria p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		criteria.add(Restrictions.eq("idtutoria", key));
		List<Tutoria> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarProfessor(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarProfessor(String tutoria){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("tutoria", tutoria));
		List<Tutoria> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarProfessorId(int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarProfessorId(int tutor, String ano){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		criteria.createAlias("tutor", "tutor");
		criteria.add(Restrictions.eq("tutor.idprofessorFuncionario", tutor));
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", ano)); 
		List<Tutoria> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarProfessorId(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarProfessorId(int tutor){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.createAlias("tutor", "tutor");
		criteria.add(Restrictions.eq("tutor.idprofessorFuncionario", tutor));
		List<Tutoria> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarAno(java.lang.String)
	 */
	
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarAno(String ano){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", ano)); 
		criteria.addOrder(Order.asc("tutoria"));
		List<Tutoria> result = criteria.list();
		
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarAnoid(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarAnoid(int id){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.idanoLetivo", id)); 
		criteria.addOrder(Order.asc("tutoria"));
		List<Tutoria> result = criteria.list();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarPerido(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarPeriodo(int id){
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", id));
		criteria.addOrder(Order.asc("tutoria"));
		List<Tutoria> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.TutoriaDAO#listarDadosPertinentes()
	 */
	@SuppressWarnings("unchecked")
	public List<Tutoria> listarDadosPertinentes(){
		
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		
	    ProjectionList projList = Projections.projectionList();  
	    
	    projList.add(Projections.property("idtutoria"),"idtutoria"); 
	    projList.add(Projections.property("tutoria"),"tutoria"); 
	     
	    
	    criteria.setProjection(projList).setCacheable(true);
	    
	    criteria.setResultTransformer(Transformers.aliasToBean(Tutoria.class));  
	    
	    List<Tutoria> results = criteria.list();
	    
		return results; 
	}

	@Override
	public List<Tutoria> listarTodasAnos() {
		Criteria criteria = getSession().createCriteria(Tutoria.class);
		List<Tutoria> result = criteria.list();		
		return result;
	}

}