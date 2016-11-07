package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MenuDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Menu;

public class MenuService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Menu> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuDAO dao = DAOFactory.getMenuDAO(pc);
		List<Menu> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<Menu> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuDAO dao = DAOFactory.getMenuDAO(pc);
		List<Menu> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Menu criarMenu(Menu p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuDAO dao = DAOFactory.getMenuDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Menu atualizarMenu(Menu p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuDAO dao = DAOFactory.getMenuDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public Menu deletarMenu(Menu p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MenuDAO dao = DAOFactory.getMenuDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
