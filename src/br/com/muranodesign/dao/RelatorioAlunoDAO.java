package br.com.muranodesign.dao;

import java.util.Date;
import java.util.List;

import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.RelatorioAluno;

public interface RelatorioAlunoDAO {

	List<RelatorioAluno> listKey(int id);

	void deletar(RelatorioAluno relatorioAluno);

	void criar(RelatorioAluno relatorioAluno);

	void atualizar(RelatorioAluno relatorioAluno);

	List<RelatorioAluno> listAll();

	List<RelatorioAluno> listarAluno(int aluno);

	List<RelatorioAluno> listarProfessor(int professor);

	List<RelatorioAluno> listarAgrupamento(int agrupamento);

	List<RelatorioAluno> listarAlunoProfessor(int professor);
	
	List<RelatorioAluno> listarAgrupamentoProfessor(int professor);

	List<RelatorioAluno> relatoriosAlunoProfessor(int idAluno, int idProfessor);

	List<RelatorioAluno> relatoriosAgrupamentoProfessor(int idAgrupamento,
			int idProfessor);

	List<RelatorioAluno> RelatoriosAlunoData(AlunoVariavel alunoVariavel,
			Integer[] idOficinas, Date inicio, Date fim);

	List<RelatorioAluno> RelatoriosAgrupamentoData(AlunoVariavel alunoVariavel,
			Integer[] idOficinas, Date inicio, Date fim);

}
