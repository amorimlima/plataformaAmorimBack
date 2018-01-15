package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AlunoAgrupamentoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AlunoAgrupamento;



public class AlunoAgrupamentoDAOImpl extends AbstractHibernateDAO implements AlunoAgrupamentoDAO{
	
	public AlunoAgrupamentoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listAll() {
		
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		List<AlunoAgrupamento> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#criar(br.com.muranodesign.model.AlunoAgrupamento)
	 */
	public void criar(AlunoAgrupamento c) {
		synchronized (AlunoAgrupamentoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#deletar(br.com.muranodesign.model.AlunoAgrupamento)
	 */
	public void deletar(AlunoAgrupamento c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#atualizar(br.com.muranodesign.model.AlunoAgrupamento)
	 */
	public void atualizar(AlunoAgrupamento p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listarKey(int key){
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		criteria.add(Restrictions.eq("Idaluno_agrupamento", key));
		List<AlunoAgrupamento> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#listarAgrupamento(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listarAgrupamento(int id){
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", id));
		List<AlunoAgrupamento> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#listarAlunoAgrupamento(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listarAlunoAgrupamento(int idAluno, int idAgrupamento){
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", idAgrupamento));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idalunoVariavel", idAluno));
		List<AlunoAgrupamento> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoAgrupamentoDAO#listarAluno(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listarAluno(int idAluno){
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idalunoVariavel", idAluno));
		List<AlunoAgrupamento> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AlunoAgrupamento> listarLikeAluno(String nome) {
		Criteria criteria = getSession().createCriteria(AlunoAgrupamento.class);
		criteria.createAlias("aluno", "alunoVariavel");
		criteria.createAlias("alunoVariavel.aluno", "aluno");
		criteria.add(Restrictions.like("aluno.nome", nome, MatchMode.ANYWHERE));
		criteria.setProjection(Projections.distinct(Projections.property("agrupamento")));
		List<AlunoAgrupamento> result = criteria.list();
		return result;
	}

}
