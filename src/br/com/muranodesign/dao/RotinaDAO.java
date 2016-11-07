package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Rotina;

public interface RotinaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Rotina> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Rotina p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Rotina p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Rotina p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Rotina> listarKey(int key);
	
	/**
	 * 
	 */
	public List<Rotina> listarInconsistencia(long hora, int idSemana/*,int idSala*/);
	
	/**
	 * Listar por oficina
	 * @param idOficina
	 * @return
	 */
	public List<Rotina> listarPorOficina(int idOficina);
	
	/**
	 * Listar por id de agrupamento
	 * @param idAgrupamento
	 * @return
	 */
	public List<Rotina> listarPorAgrupamento(int idAgrupamento);
}
