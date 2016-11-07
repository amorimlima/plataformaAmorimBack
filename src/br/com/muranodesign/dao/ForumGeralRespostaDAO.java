package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.ForumGeralResposta;

public interface ForumGeralRespostaDAO {
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<ForumGeralResposta> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(ForumGeralResposta p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(ForumGeralResposta p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(ForumGeralResposta p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<ForumGeralResposta> listarKey(int key);


}
