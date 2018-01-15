package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.TipoOficinaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.TipoOficina;

public class TipoOficinaService {

	/**
	 * 
	 * @return
	 */
	public List<TipoOficina> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TipoOficinaDAO dao = DAOFactory.getTipoOficinaDAO(pc);
		List<TipoOficina> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public TipoOficina listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TipoOficinaDAO dao = DAOFactory.getTipoOficinaDAO(pc);
		TipoOficina result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}	
	
}
