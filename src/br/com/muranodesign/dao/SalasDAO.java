package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Salas;

public interface SalasDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Salas> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Salas p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Salas p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Salas p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Salas> listarKey(int key);
	
	

}
