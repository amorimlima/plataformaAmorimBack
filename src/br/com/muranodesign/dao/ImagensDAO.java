package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Imagens;

public interface ImagensDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Imagens> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Imagens p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Imagens p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Imagens p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Imagens> listarKey(int key);

}
