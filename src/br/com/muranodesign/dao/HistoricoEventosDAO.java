package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.historicoEventos;

public interface HistoricoEventosDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<historicoEventos> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(historicoEventos p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(historicoEventos p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(historicoEventos p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<historicoEventos> listarKey(int key);

}
