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
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ProducaoAlunoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ProducaoAluno;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class ProducaoAlunoDAOImpl extends AbstractHibernateDAO implements ProducaoAlunoDAO {

	/**
	 * Instantiates a new producao aluno dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public ProducaoAlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listAll() {
		
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		List<ProducaoAluno> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#criar(br.com.muranodesign.model.ProducaoAluno)
	 */
	public void criar(ProducaoAluno c) {
		synchronized (ProducaoAlunoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#deletar(br.com.muranodesign.model.ProducaoAluno)
	 */
	public void deletar(ProducaoAluno c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#atualizar(br.com.muranodesign.model.ProducaoAluno)
	 */
	public void atualizar(ProducaoAluno p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.add(Restrictions.eq("idproducaoAluno", key));
		List<ProducaoAluno> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listarFiltro(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listarFiltro(int id, int tipo, int roteiro){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		
		
		criteria.createAlias("tipo", "tipo");
		criteria.add(Restrictions.eq("tipo.idtipoProducaoAluno", tipo));
		
		if (roteiro != 0)
		{
			criteria.createAlias("roteiro", "roteiro");
			criteria.add(Restrictions.eq("roteiro.idroteiro", roteiro));
		}
		List<ProducaoAluno> result = criteria.list();
		return result;
	}
		
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listarAluno(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listarAluno(int id){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		List<ProducaoAluno> result = criteria.list();
		return result;
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listarPortifolio(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listarPortifolio(int id){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.createAlias("tipo", "tipo");
		criteria.add(Restrictions.eq("tipo.idtipoProducaoAluno", 5));
		List<ProducaoAluno> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#listaAlunoRoteiroTipo(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listaAlunoRoteiroTipo(int idAluno, int idRoteiro, int idTipo){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.idroteiro", idRoteiro));
		
		criteria.createAlias("tipo", "tipo");
		criteria.add(Restrictions.eq("tipo.idtipoProducaoAluno", idTipo));
		List<ProducaoAluno> result = criteria.list();
		return result;
	}
	
	/*	
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ProducaoAlunoDAO#NCapa(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> NCapa(int id){
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.add(Restrictions.isNotNull("capa"));
		List<ProducaoAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ProducaoAluno> listaOficinaAluno(int idOficina, int idAluno) {
		Criteria criteria = getSession().createCriteria(ProducaoAluno.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		
		List<ProducaoAluno> result = criteria.list();
		
		return result;
	}

}