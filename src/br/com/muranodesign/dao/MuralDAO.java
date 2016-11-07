package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Mural;

public interface MuralDAO {
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Mural> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Mural p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Mural p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Mural p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Mural> listarKey(int key);
	
	/**
	 * Listar por range de data
	 * @param data2
	 * @param data
	 * @return
	 */
	public List<Mural>Range(String data2, String data, int idProfessor);
	
	}
