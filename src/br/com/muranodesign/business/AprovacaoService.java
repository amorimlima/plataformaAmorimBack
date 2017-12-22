package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AprovacaoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Aprovacao;

public class AprovacaoService {

	public List<Aprovacao> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AprovacaoDAO dao = DAOFactory.getAprovacaoDAO(pc);
		List<Aprovacao> result = dao.listAll();	
		pc.commitAndClose();
		return result;
	}

	public List<Aprovacao> listarKey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AprovacaoDAO dao = DAOFactory.getAprovacaoDAO(pc);
		List<Aprovacao> result = dao.listarKey(id);	
		pc.commitAndClose();
		return result;
	}

	
	
}
