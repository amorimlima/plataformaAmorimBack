package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.MenuPerfil;

public interface MenuPerfilDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<MenuPerfil> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(MenuPerfil p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(MenuPerfil p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(MenuPerfil p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<MenuPerfil> listarKey(int key);
	
	/**
	 * Listar por id perfil
	 * @param id
	 * @return
	 */
	public List<MenuPerfil> listarUser(int id);
}
