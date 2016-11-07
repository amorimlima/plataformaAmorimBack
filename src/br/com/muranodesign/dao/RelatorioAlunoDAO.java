package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.RelatorioAluno;

public interface RelatorioAlunoDAO {

	List<RelatorioAluno> listKey(int id);

	void deletar(RelatorioAluno relatorioAluno);

	void criar(RelatorioAluno relatorioAluno);

	void atualizar(RelatorioAluno relatorioAluno);

	List<RelatorioAluno> listAll();

	List<RelatorioAluno> listarAluno(int aluno);

	List<RelatorioAluno> listarProfessor(int professor);

}
