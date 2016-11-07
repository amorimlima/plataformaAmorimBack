package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.AlunoAgrupamento;

public interface AlunoAgrupamentoDAO {
	

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<AlunoAgrupamento> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(AlunoAgrupamento p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(AlunoAgrupamento p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(AlunoAgrupamento p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<AlunoAgrupamento> listarKey(int key);
	
	/**
	 * Lista por id agrupamento
	 * @param id
	 * @return
	 */
	public List<AlunoAgrupamento> listarAgrupamento(int id);
	
	/**
	 * Lista por id agrupamento e id de aluno
	 * @param idAluno
	 * @param idAgrupamento
	 * @return
	 */
	public List<AlunoAgrupamento> listarAlunoAgrupamento(int idAluno, int idAgrupamento);
	
	/**
	 * Lista por id de aluno
	 * @param idAluno
	 * @return
	 */
	public List<AlunoAgrupamento> listarAluno(int idAluno);
	

}
