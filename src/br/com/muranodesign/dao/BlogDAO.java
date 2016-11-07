package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Blog;

public interface BlogDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Blog> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Blog p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Blog p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Blog p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Blog> listarKey(int key);
	
	public List<Blog> listarOficina(int id);
	
}
