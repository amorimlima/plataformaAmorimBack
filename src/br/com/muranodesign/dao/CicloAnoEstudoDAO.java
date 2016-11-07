package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.CicloAnoEstudo;

public interface CicloAnoEstudoDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<CicloAnoEstudo> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(CicloAnoEstudo p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(CicloAnoEstudo p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(CicloAnoEstudo p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<CicloAnoEstudo> listarKey(int key);
	
	/**
	 * Listar por id de ciclo
	 * @param idCiclo
	 * @return
	 */
	public List<CicloAnoEstudo> listCiclo(int idCiclo);

}
