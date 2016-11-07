package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Menu;

public interface MenuDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Menu> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Menu p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Menu p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Menu p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Menu> listarKey(int key);

}
