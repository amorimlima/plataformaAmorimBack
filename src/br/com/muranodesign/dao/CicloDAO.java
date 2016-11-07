package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Ciclos;

public interface CicloDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Ciclos> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Ciclos p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Ciclos p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Ciclos p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Ciclos> listarKey(int key);
}
