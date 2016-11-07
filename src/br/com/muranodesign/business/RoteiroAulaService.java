package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.RoteiroAulaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.RoteiroAula;

public class RoteiroAulaService {
	
	/**
	 * 
	 * @return
	 */
	public List<RoteiroAula> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<RoteiroAula> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public RoteiroAula criarRoteiroAula(RoteiroAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public RoteiroAula atualizarRoteiroAula(RoteiroAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public RoteiroAula deletarRoteiroAula(RoteiroAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

	/**
	 * 
	 * @param letra
	 * @return
	 */
	public List<RoteiroAula> listarLikeRoteiro(String letra){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarLikeRoteiro(letra);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<RoteiroAula> listarOficinaProfessor(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarOficinaProfessor(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<RoteiroAula> listarNaoOficinaProfessor(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarNaoOficinaProfessor(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idOficinaProfessor
	 * @param letras
	 * @return
	 */
	public List<RoteiroAula> listarLike(int idOficinaProfessor, String letras){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarLike(idOficinaProfessor, letras);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @param letra
	 * @return
	 */
	public List<RoteiroAula> listarOficinaProfessorLike(int id, String letra){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarOficinaProfessorLike(id, letra);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @param letra
	 * @return
	 */
	public List<RoteiroAula> listarNaoOficinaProfessorLike(int id, String letra){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RoteiroAulaDAO dao = DAOFactory.getRoteiroAulaDAO(pc);
		List<RoteiroAula> result = dao.listarNaoOficinaProfessorLike(id, letra);
		pc.commitAndClose();
		return result;
	}
}
