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
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.muranodesign.dao.ChamadaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.Chamada;
import br.com.muranodesign.model.Compensacao;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class ChamadaDAOImpl extends AbstractHibernateDAO implements ChamadaDAO {

	/**
	 * Instantiates a new chamada dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public ChamadaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Chamada> listAll() {
		
		Criteria criteria = getSession().createCriteria(Chamada.class);
		List<Chamada> result = criteria.list();
		
		
		return result;
	} 
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Chamada> listaPrecenca(Aluno aluno , int precenca) {
		
		Criteria criteria = getSession().createCriteria(Chamada.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		criteria.add(Restrictions.ge("data", cal.getTime()));
		
		short shortPrecenca;
		shortPrecenca = (short) precenca;
		
		criteria.add(Restrictions.eq("aluno", aluno));
		criteria.add(Restrictions.eq("presenca", shortPrecenca));
		
		List<Chamada> result = criteria.list();
		
		
		return result;
	} 
	
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#criar(br.com.muranodesign.model.Chamada)
	 */
	public void criar(Chamada c) {
		synchronized (ChamadaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#deletar(br.com.muranodesign.model.Chamada)
	 */
	public void deletar(Chamada c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#atualizar(br.com.muranodesign.model.Chamada)
	 */
	public void atualizar(Chamada p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Chamada> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Chamada.class);
		criteria.add(Restrictions.eq("idchamada", key));
		List<Chamada> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#countFaltas(int)
	 */
	@SuppressWarnings("unchecked")
	public long countFaltas(int id){
		short t = 0;
		Criteria criteria = getSession().createCriteria(Chamada.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		criteria.add(Restrictions.gt("data", cal.getTime()));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("presenca", t));
		List<Chamada> result = criteria.list();
		long r =  result.size();
		return r;	
	}
	
	@SuppressWarnings("unchecked")
	public long countPresencas(int id){
		Criteria criteria = getSession().createCriteria(Chamada.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		criteria.add(Restrictions.gt("data", cal.getTime()));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("presenca", (short)1));
		List<Chamada> result = criteria.list();
		long r =  result.size();
		return r;	
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#dataPresenca(int, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Chamada> dataPresenca(int id, Date data){
		Criteria criteria = getSession().createCriteria(Chamada.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("data", data));
		List<Chamada> result = criteria.list();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ChamadaDAO#dataPresencaAtual(int, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Chamada> dataPresencaAtual(int id, Date data){
		Criteria criteria = getSession().createCriteria(Chamada.class);
		
		ProjectionList projList = Projections.projectionList();  
		projList.add(Projections.property("presenca"),"presenca");
		
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("data", data));
		
		criteria.setProjection(projList).setCacheable(true);
	    criteria.setResultTransformer(Transformers.aliasToBean(Chamada.class)); 
		
		List<Chamada> result = criteria.list();
		
	    
		return result; 
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> getFaltasSemana(int idAluno, int dia, int mes) {
		Criteria criteria = getSession().createCriteria(Chamada.class);
		
		Calendar primeiroDia = Calendar.getInstance();
		primeiroDia.set(Calendar.MONTH, mes);
		primeiroDia.set(Calendar.DATE, dia);
		primeiroDia.set(Calendar.WEEK_OF_MONTH, primeiroDia.get(Calendar.WEEK_OF_MONTH));
		primeiroDia.set(Calendar.DAY_OF_WEEK, primeiroDia.getFirstDayOfWeek());
		Calendar ultimoDia =  Calendar.getInstance();
		ultimoDia.set(Calendar.MONTH, mes);
		ultimoDia.set(Calendar.DATE, primeiroDia.get(Calendar.DATE) + 6);
		
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		
		//criteria.add(Restrictions.eq("presenca", (short)0));
		
		criteria.add(Restrictions.ge("data", primeiroDia.getTime()));
		criteria.add(Restrictions.le("data", ultimoDia.getTime()));
		criteria.addOrder(Order.asc("data"));
		
		List<Chamada> result = criteria.list();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> dataPresenca(int id, Calendar cal) {
		Criteria criteria = getSession().createCriteria(Chamada.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("data", cal.getTime()));
		List<Chamada> result = criteria.list();
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> listBetween(int idAluno, Date startDate, Date endDate) {
		Criteria criteria = getSession().createCriteria(Chamada.class);
		criteria.add(Restrictions.between("data", startDate, endDate));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public long countFaltasAno(int idAluno, int ano) {
		short t = 0;
		Criteria criteria = getSession().createCriteria(Chamada.class);
		Calendar calInicio = Calendar.getInstance();
		calInicio.set(Calendar.YEAR, ano);
		calInicio.set(Calendar.DAY_OF_YEAR, 1);
		Calendar calFim = Calendar.getInstance();
		calFim.set(Calendar.YEAR, ano);
		calFim.set(Calendar.DAY_OF_YEAR, calFim.getActualMaximum(Calendar.DAY_OF_YEAR));
		criteria.add(Restrictions.ge("data", calInicio.getTime()));
		criteria.add(Restrictions.le("data", calFim.getTime()));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		criteria.add(Restrictions.eq("presenca",t ));
		List<Chamada> result = criteria.list();
		long r =  result.size();
		return r;
	}

	@SuppressWarnings("unchecked")
	public List<Compensacao> compensacaoAluno(int idAluno) {
		Criteria criteria = getSession().createCriteria(Chamada.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		criteria.add(Restrictions.isNotNull("compensacao"));
		criteria.setProjection(Projections.distinct(Projections.property("compensacao")));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public long countCompensacao(Integer idAluno) {
		Criteria criteria = getSession().createCriteria(Chamada.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		criteria.add(Restrictions.gt("data", cal.getTime()));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		criteria.add(Restrictions.eq("presenca", (short)3));
		List<Chamada> result = criteria.list();
		long r =  result.size();
		return r;	
	}

}