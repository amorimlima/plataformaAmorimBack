package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.MuralAluno;

public interface MuralAlunoDAO {

	List<MuralAluno> listarAll();

	List<MuralAluno> listarKey(int id);

	void deletar(MuralAluno muralAluno);

	void criar(MuralAluno muralAluno);

	List<MuralAluno> listarAluno(int idaluno);

	List<MuralAluno> listarMural(int id);
}
