package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SODAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.SO;



public class SOService {
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public SO criarSO(SO p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	/**
	 * 
	 * @param so
	 * @return
	 */
	public List<SO> listarSO(String so){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		List<SO> result = dao.listarSo(so);
		return result;
	}
	
	/**
	 * 
	 * @param idso
	 * @return
	 */
	public List<SO> listarBySO(int idso){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SODAO dao = DAOFactory.getSodao(pc);
		List<SO> result = dao.listarid(idso);
		return result;
	}
	
	
}
