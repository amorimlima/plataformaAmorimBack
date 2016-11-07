package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Cores;

public interface CoresDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Cores> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Cores p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Cores p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Cores p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Cores> listarKey(int key);

}
