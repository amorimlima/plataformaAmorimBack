package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.ComunicadoOficinas;

public interface ComunicadoOficinasDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<ComunicadoOficinas> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(ComunicadoOficinas p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(ComunicadoOficinas p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(ComunicadoOficinas p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<ComunicadoOficinas> listarKey(int key);


}
