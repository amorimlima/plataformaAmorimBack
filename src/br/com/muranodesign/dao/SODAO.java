package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.SO;

public interface SODAO {
	
	public List<SO> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(SO p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(SO p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(SO p);
	
	/**
	 * Listar por so.
	 *
	 * @param key the key
	 * @return the list
	 */
	//era int 
	public List<SO> listarSo(String so);
	
	/**
	 * Listar por id
	 * @param idso
	 * @return
	 */
	public List<SO> listarid(int idso);
	


}
