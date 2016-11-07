package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MuralDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Mural;

public class MuralService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Mural> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<Mural> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Mural criarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Mural atualizarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public Mural deletarMural(Mural p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Range
	 * @param data2
	 * @param data
	 * @param idProfessor
	 * @return
	 */
	public List<Mural>Range(String data2, String data, int idProfessor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralDAO dao = DAOFactory.getMuralDAO(pc);
		List<Mural> result = dao.Range(data2, data,idProfessor);
		pc.commitAndClose();
		return result;
		
	}
}
