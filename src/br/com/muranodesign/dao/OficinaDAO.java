package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Oficina;

public interface OficinaDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Oficina> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Oficina p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Oficina p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Oficina p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Oficina> listarKey(int key);
	
	/**
	 * Listar por id de oficina e por id de ciclo
	 * @param id
	 * @param ciclo
	 * @return
	 */
	public List<Oficina> listarIdCiclo(int id, int ciclo);
	
	/**
	 * Listar por id de oficina
	 * @param id
	 * @param ciclo
	 * @return
	 */
	public List<Oficina> listarIdOficina(int id);
	
	/**
	 * Listar por nome de oficina
	 * @param nome
	 * @return
	 */
	public long listarNomeOficina(String nome);
	
	/**
	 * Listar por nome de oficina
	 * @param nome
	 * @return
	 */
	public List<Oficina> listarNomeOficinaid(String nome);
	
	/**
	 * Listar Nome e Id de oficinas
	 * @param id
	 * @return
	 */
	public List<Oficina> listarLazyNome(int id);
}
