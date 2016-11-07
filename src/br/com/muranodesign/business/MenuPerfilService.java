package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MenuPerfilDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.MenuPerfil;

public class MenuPerfilService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<MenuPerfil> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<MenuPerfil> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public MenuPerfil criarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public MenuPerfil atualizarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public MenuPerfil deletarMenuPerfil(MenuPerfil p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar user 
	 * @param id
	 * @return
	 */
	public List<MenuPerfil> listarUser(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuPerfilDAO dao = DAOFactory.getMenuPerfilDAO(pc);
		List<MenuPerfil> result = dao.listarUser(id);
		pc.commitAndClose();
		return result;
	}
}
