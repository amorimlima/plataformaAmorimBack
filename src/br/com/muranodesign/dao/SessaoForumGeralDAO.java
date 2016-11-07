package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.SessaoForumGeral;


public interface SessaoForumGeralDAO {
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<SessaoForumGeral> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(SessaoForumGeral p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(SessaoForumGeral p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(SessaoForumGeral p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<SessaoForumGeral> listarKey(int key);
	
}
