package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.FichaInscricao;

public interface FichaInscricaoDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<FichaInscricao> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(FichaInscricao p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(FichaInscricao p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(FichaInscricao p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<FichaInscricao> listarKey(int key);
	
	


}
