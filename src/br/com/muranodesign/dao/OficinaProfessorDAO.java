package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.OficinaProfessor;

public interface OficinaProfessorDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<OficinaProfessor> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(OficinaProfessor p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(OficinaProfessor p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(OficinaProfessor p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<OficinaProfessor> listarKey(int key);
	
	/**
	 * Listar por id de professor
	 * @param id
	 * @return
	 */
	public List<OficinaProfessor> listarProfessor(int id);
	
	/**
	 * Listar oficina professor por id de oficina
	 * @param idOficina
	 * @return
	 */
	public List<OficinaProfessor> listarOficina(int idOficina,int idProfessor);
	
	/**
	 * Listar por oficina
	 * @param idOficina
	 * @return
	 */
	public List<OficinaProfessor> listarPorOficina(int idOficina);
	

}
