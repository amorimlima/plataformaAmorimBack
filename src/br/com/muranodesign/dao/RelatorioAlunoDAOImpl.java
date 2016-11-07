package br.com.muranodesign.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
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
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", aluno));
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

}
