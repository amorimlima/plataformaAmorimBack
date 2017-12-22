package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.OficinaProfessorDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.OficinaProfessor;
import br.com.muranodesign.resources.ProfessorFuncionario;

public class OficinaProfessorService {
	
	/**
	 * 
	 * @return
	 */
	public List<OficinaProfessor> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<OficinaProfessor> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public OficinaProfessor criarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public OficinaProfessor atualizarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public OficinaProfessor deletarOficinaProfessor(OficinaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<OficinaProfessor> listarProfessor(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarProfessor(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idOficina
	 * @param idProfessor
	 * @return
	 */
	public List<OficinaProfessor> listarOficina(int idOficina, int idProfessor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarOficina(idOficina, idProfessor);
		pc.commitAndClose();
		return result;
	}

	/**
	 * 
	 * @param idOficina
	 * @return
	 */
	public List<OficinaProfessor> listarPorOficina(int idOficina){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarPorOficina(idOficina);
		pc.commitAndClose();
		return result;
	}

	public List<ProfessorFuncionario> listarOficineiros() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<ProfessorFuncionario> result = dao.listarOficinerios();
		pc.commitAndClose();
		return result;
	}

	public List<ProfessorFuncionario> listarProfessoresPorOficina(int idOficina) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<ProfessorFuncionario> result = dao.listarProfessoresPorOficina(idOficina);
		pc.commitAndClose();
		return result;
	}

	public List<OficinaProfessor> listarOficinaProfessor(int idOficina,
			int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		OficinaProfessorDAO dao = DAOFactory.getOficinaProfessorDAO(pc);
		List<OficinaProfessor> result = dao.listarOficinaProfessor(idOficina, idProfessor);
		pc.commitAndClose();
		return result;
	}
}
