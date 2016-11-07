package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.ForumGeralQuestao;

public interface ForumGeralQuestaoDAO {
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<ForumGeralQuestao> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(ForumGeralQuestao p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(ForumGeralQuestao p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(ForumGeralQuestao p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<ForumGeralQuestao> listarKey(int key);

}
