package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.AlunoConceito;

public interface AlunoConceitoDAO {

	void criar(AlunoConceito resultado);

	void update(AlunoConceito resultado);

	List<AlunoConceito> listarKey(int id);

	void deletar(AlunoConceito resultado);

	List<AlunoConceito> listarTodos();

}
