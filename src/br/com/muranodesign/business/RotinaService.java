package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.RotinaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Rotina;

public class RotinaService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Rotina> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		List<Rotina> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por id
	 * @param key
	 * @return
	 */
	public List<Rotina> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		List<Rotina> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar Rotina
	 * @param p
	 * @return
	 */
	public Rotina criarRotina(Rotina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Rotina atualizarRotina(Rotina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Rotina deletarRotina(Rotina p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar inconsistencia
	 * @param hora
	 * @param idSemana
	 * @return
	 */
	public List<Rotina> listarInconsistencia(long hora, int idSemana/*,int idSala*/){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		List<Rotina> result = dao.listarInconsistencia(hora, idSemana/*, idSala*/);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por oficina
	 * @param idOficina
	 * @return
	 */
	public List<Rotina> listarPorOficina(int idOficina){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		List<Rotina> result = dao.listarPorOficina(idOficina);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por agrupamento
	 * @param idAgrupamento
	 * @return
	 */
	public List<Rotina> listarPorAgrupamento(int idAgrupamento){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		RotinaDAO dao = DAOFactory.getRotinaDAO(pc);
		List<Rotina> result = dao.listarPorAgrupamento(idAgrupamento);
		pc.commitAndClose();
		return result;
	}

}
