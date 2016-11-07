package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SemanaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Semana;

public class SemanaService {
	
	/**
	 * 
	 * @return
	 */
	public List<Semana> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SemanaDAO dao = DAOFactory.getSemanaDAO(pc);
		List<Semana> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public List<Semana> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SemanaDAO dao = DAOFactory.getSemanaDAO(pc);
		List<Semana> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Semana criarSemana(Semana p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SemanaDAO dao = DAOFactory.getSemanaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Semana atualizarSemana(Semana p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SemanaDAO dao = DAOFactory.getSemanaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Semana deletarSemana(Semana p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SemanaDAO dao = DAOFactory.getSemanaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
