package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.AgendamentoSala;

public interface AgendamentoSalaDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<AgendamentoSala> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(AgendamentoSala p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(AgendamentoSala p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(AgendamentoSala p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<AgendamentoSala> listarKey(int key);
	
	/**
	 * 
	 * @param dia
	 * @param sala
	 * @param hora
	 * @return
	 */
	public List<AgendamentoSala> listarValidacao(int dia, int sala, long hora);
	

}
