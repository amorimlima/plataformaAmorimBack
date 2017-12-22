package br.com.muranodesign.business;

import java.util.Date;
import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.RelatorioAlunoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.RelatorioAluno;

public class RelatorioAlunoService {

	public List<RelatorioAluno> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listKey(id);
		pc.commitAndClose();
		return resultado;
	}

	public RelatorioAluno deletarOficina(RelatorioAluno relatorioAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		dao.deletar(relatorioAluno);
		pc.commitAndClose();
		return relatorioAluno;
	}

	public RelatorioAluno criarRelatorioAluno(RelatorioAluno relatorioAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		dao.criar(relatorioAluno);
		pc.commitAndClose();
		return relatorioAluno;
	}

	public RelatorioAluno atualizarRelatorioAluno(RelatorioAluno relatorioAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		dao.atualizar(relatorioAluno);
		pc.commitAndClose();
		return relatorioAluno;
	}

	public List<RelatorioAluno> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listAll();
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> listarAluno(int aluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listarAluno(aluno);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> listarProfessor(int professor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listarProfessor(professor);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> listarAgrupamento(int agrupamento) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listarAgrupamento(agrupamento);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> listarAlunoProfessor(int professor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listarAlunoProfessor(professor);
		pc.commitAndClose();
		return resultado;
	}
	
	public List<RelatorioAluno> listarAgrupamentoProfessor(int professor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.listarAgrupamentoProfessor(professor);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> relatoriosAlunoProfessor(int idAluno,
			int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.relatoriosAlunoProfessor(idAluno, idProfessor);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> relatoriosAgrupamentoProfessor(
			int idAgrupamento, int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.relatoriosAgrupamentoProfessor(idAgrupamento, idProfessor);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> RelatoriosAlunoData(
			AlunoVariavel alunoVariavel, Integer[] idOficinas, Date inicio, Date fim) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.RelatoriosAlunoData(alunoVariavel, idOficinas, inicio, fim);
		pc.commitAndClose();
		return resultado;
	}

	public List<RelatorioAluno> RelatoriosAgrupamentoData(
			AlunoVariavel alunoVariavel, Integer[] idOficinas, Date inicio,
			Date fim) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RelatorioAlunoDAO dao = DAOFactory.getRelatorioAlunoDAO(pc);
		List<RelatorioAluno> resultado = dao.RelatoriosAgrupamentoData(alunoVariavel, idOficinas, inicio, fim);
		pc.commitAndClose();
		return resultado;
	}

}
