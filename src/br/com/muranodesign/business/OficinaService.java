package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.OficinaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Oficina;

public class OficinaService {
	
	/**
	 * 
	 * @return
	 */
	public List<Oficina> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<Oficina> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Oficina criarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Oficina atualizarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Oficina deletarOficina(Oficina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

	/**
	 * 
	 * @param id
	 * @param ciclo
	 * @return
	 */
	public List<Oficina> listarIdCiclo(int id, int ciclo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarIdCiclo(id, ciclo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Oficina> listarIdOficina(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarIdOficina(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public long listarNomeOficina(String nome){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		long result = dao.listarNomeOficina(nome);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public List<Oficina> listarNomeOficinaid(String nome){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarNomeOficinaid(nome);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Oficina> listarLazyNome(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaDAO dao = DAOFactory.getOficinaDAO(pc);
		List<Oficina> result = dao.listarLazyNome(id);
		pc.commitAndClose();
		return result;
	}
}
