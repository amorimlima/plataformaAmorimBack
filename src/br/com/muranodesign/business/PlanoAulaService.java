package br.com.muranodesign.business;

import java.util.Date;
import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PlanoAulaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PlanoAula;

public class PlanoAulaService {
	
	/**
	 * 
	 * @return
	 */
	public List<PlanoAula> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		List<PlanoAula> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<PlanoAula> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		List<PlanoAula> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanoAula criarPlanoAula(PlanoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanoAula atualizarPlanoAula(PlanoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanoAula deletarPlanoAula(PlanoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<PlanoAula> range(Date data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		List<PlanoAula> result = dao.range(data);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idProfessor
	 * @return
	 */
	public List<PlanoAula> listarUltimo(int idProfessor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		List<PlanoAula> result = dao.listarUltimo(idProfessor);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idProfessor
	 * @param data
	 * @return
	 */
	public List<PlanoAula> listarProfessor(int idProfessor,Date data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAulaDAO dao = DAOFactory.getPlanoAulaDAO(pc);
		List<PlanoAula> result = dao.listarProfessor(idProfessor, data);
		pc.commitAndClose();
		return result;
	}

}
