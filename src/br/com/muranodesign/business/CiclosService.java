package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CicloDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Ciclos;

public class CiclosService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Ciclos> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		List<Ciclos> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<Ciclos> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		List<Ciclos> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Ciclos criarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Ciclos atualizarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public Ciclos deletarCiclos(Ciclos p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloDAO dao = DAOFactory.getCicloDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
