package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CoresDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Cores;

public class CoresService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Cores> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		List<Cores> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<Cores> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		List<Cores> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Cores criarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Cores atualizarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public Cores deletarCores(Cores p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CoresDAO dao = DAOFactory.getCorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
