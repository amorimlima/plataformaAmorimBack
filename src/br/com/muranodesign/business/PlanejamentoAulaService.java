package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PlanejamentoAulaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PlanejamentoAula;

public class PlanejamentoAulaService {

	/**
	 * 
	 * @return
	 */
	public List<PlanejamentoAula> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		List<PlanejamentoAula> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<PlanejamentoAula> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		List<PlanejamentoAula> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanejamentoAula criarPlanejamentoAula(PlanejamentoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanejamentoAula atualizarPlanejamentoAula(PlanejamentoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public PlanejamentoAula deletarPlanejamentoAula(PlanejamentoAula p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<PlanejamentoAula> listarProfessor(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		List<PlanejamentoAula> result = dao.listarProfessor(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<PlanejamentoAula> listarPlanoAula(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		List<PlanejamentoAula> result = dao.listarPlanoAula(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idProfessor
	 * @param idObjetivoAula
	 * @param idplanoAula
	 * @return
	 */
	public List<PlanejamentoAula> listarProfessorObjetivoAula(int idProfessor, int idObjetivoAula, int idplanoAula){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoAulaDAO dao = DAOFactory.getPlanejamentoAulaDAO(pc);
		List<PlanejamentoAula> result = dao.listarProfessorObjetivoAula(idProfessor, idObjetivoAula, idplanoAula);
		pc.commitAndClose();
		return result;
	}
}
