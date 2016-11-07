package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ObjetivoAulaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ObjetivoAula;

public class ObjetivoAulaService {
	
	/**
	 * 
	 * @return
	 */
	public List<ObjetivoAula> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<ObjetivoAula> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public ObjetivoAula criarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public ObjetivoAula atualizarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public ObjetivoAula deletarObjetivoAula(ObjetivoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param idroteiro
	 * @return
	 */
	public List<ObjetivoAula> listarPorRoteiro(int idroteiro){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listarPorRoteiro(idroteiro);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<ObjetivoAula> listarPorRoteiroLazy(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoAulaDAO dao = DAOFactory.getObjetivoAulaDAO(pc);
		List<ObjetivoAula> result = dao.listarPorRoteiroLazy(id);
		pc.commitAndClose();
		return result;
	}
}
