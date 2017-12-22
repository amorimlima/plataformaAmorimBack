package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.ConceitoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Conceito;

public class ConceitoService {

	public List<Conceito> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ConceitoDAO dao = DAOFactory.getConceitoDAO(pc);
		List<Conceito> result = dao.listAll();	
		pc.commitAndClose();
		return result;
	}

	public List<Conceito> listarKey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ConceitoDAO dao = DAOFactory.getConceitoDAO(pc);
		List<Conceito> result = dao.listarKey(id);
		pc.commitAndClose();
		return result;
	}

}
