package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Semana;

public interface SemanaDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Semana> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Semana p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Semana p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Semana p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Semana> listarKey(int key);
	

}
