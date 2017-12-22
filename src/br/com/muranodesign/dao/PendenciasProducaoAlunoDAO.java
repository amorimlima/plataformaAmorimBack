package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.PendenciasProducaoAluno;

public interface PendenciasProducaoAlunoDAO {
	
	List<PendenciasProducaoAluno> listAll();

	List<PendenciasProducaoAluno> listarKey(int key);

	void criar(PendenciasProducaoAluno p);

	void deletar(PendenciasProducaoAluno p);
	
	void atualizar(PendenciasProducaoAluno p);

	List<PendenciasProducaoAluno> listarAluno(int id);

	public List<PendenciasProducaoAluno> listarAlunoRoteiro(int idaluno, int idroteiro);
	
	List<PendenciasProducaoAluno> listarAlunoRoteiroAno(int idAluno, int idRoteiro, String ano);

	List<PendenciasProducaoAluno> listarAlunoAnoAnterior(int id);

}
