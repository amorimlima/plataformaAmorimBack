package br.com.muranodesign.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.RelatorioAluno;

public class RelatorioAlunoDAOImpl extends AbstractHibernateDAO implements
		RelatorioAlunoDAO {

	public RelatorioAlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}

	public void deletar(RelatorioAluno relatorioAluno) {
		synchronized(RelatorioAlunoDAOImpl.class){
			getSession().delete(relatorioAluno);
			getSession().flush();
		}
	}

	public void criar(RelatorioAluno relatorioAluno) {
		synchronized(RelatorioAlunoDAOImpl.class){
			getSession().persist(relatorioAluno);
			getSession().flush();
		}
	}

	public void atualizar(RelatorioAluno relatorioAluno) {
		synchronized(RelatorioAlunoDAOImpl.class){
			getSession().merge(relatorioAluno);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listAll() {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		List<RelatorioAluno> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listKey(int id) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.add(Restrictions.eq("idrelatorioAluno", id));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listarAluno(int aluno) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("alunoVariavel", "alunoVariavel");
		criteria.add(Restrictions.eq("alunoVariavel.idalunoVariavel", aluno));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listarProfessor(int professor) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", professor));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listarAgrupamento(int agrupamento) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", agrupamento));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listarAlunoProfessor(int professor) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", professor));
		criteria.add(Restrictions.isNotNull("alunoVariavel"));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> listarAgrupamentoProfessor(int professor) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", professor));
		criteria.add(Restrictions.isNotNull("agrupamento"));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> relatoriosAlunoProfessor(int idAluno,
			int idProfessor) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("alunoVariavel", "alunoVariavel");
		criteria.add(Restrictions.eq("alunoVariavel.idalunoVariavel", idAluno));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAluno> relatoriosAgrupamentoProfessor(
			int idAgrupamento, int idProfessor) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", idAgrupamento));
		List<RelatorioAluno> result = criteria.list();
		return result;
	}

	@Override
	public List<RelatorioAluno> RelatoriosAlunoData(
			AlunoVariavel alunoVariavel, Integer[] idOficinas, Date inicio, Date fim) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.in("oficina.Idoficina", idOficinas));
		
		criteria.add(Restrictions.between("data", inicio, fim));
		criteria.add(Restrictions.eq("alunoVariavel", alunoVariavel));
		
		List<RelatorioAluno> result = criteria.list();
		return result;
	}
	
	public List<RelatorioAluno> RelatoriosAgrupamentoData(
			AlunoVariavel alunoVariavel, Integer[] idOficinas, Date inicio, Date fim) {
		Criteria criteria = getSession().createCriteria(RelatorioAluno.class);
		
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.in("oficina.Idoficina", idOficinas));
		
		criteria.add(Restrictions.between("data", inicio, fim));
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.createAlias("agrupamento.alunos", "alunos");
		
		criteria.add(Restrictions.eq("alunos.aluno", alunoVariavel));
		
		List<RelatorioAluno> result = criteria.list();
		return result;
	}
	
}
