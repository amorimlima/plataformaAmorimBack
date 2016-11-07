package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.ObjetivoAula;

public interface ObjetivoAulaDAO {
	

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<ObjetivoAula> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(ObjetivoAula p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(ObjetivoAula p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(ObjetivoAula p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<ObjetivoAula> listarKey(int key);
	
	/**
	 * Listar por roteiro
	 * @param idroteiro
	 * @return
	 */
	public List<ObjetivoAula> listarPorRoteiro(int idroteiro);
	
	/**
	 * Listar por roteiro lazy
	 * @return
	 */
	public List<ObjetivoAula> listarPorRoteiroLazy(int id);
	

}
