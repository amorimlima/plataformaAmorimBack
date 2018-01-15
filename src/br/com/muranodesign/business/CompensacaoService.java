package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CompensacaoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Compensacao;

public class CompensacaoService {

	public Compensacao criar(Compensacao resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CompensacaoDAO dao = DAOFactory.getCompensacaoDAO(pc);
		dao.criar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<Compensacao> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CompensacaoDAO dao = DAOFactory.getCompensacaoDAO(pc);
		List<Compensacao> result = dao.listarKey(id);
		pc.commitAndClose();
		return result;
	}

	public Compensacao update(Compensacao resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CompensacaoDAO dao = DAOFactory.getCompensacaoDAO(pc);
		dao.update(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public Compensacao deletar(Compensacao resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CompensacaoDAO dao = DAOFactory.getCompensacaoDAO(pc);
		dao.deletar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<Compensacao> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CompensacaoDAO dao = DAOFactory.getCompensacaoDAO(pc);
		List<Compensacao> result = dao.listAll();	
		pc.commitAndClose();
		return result;
	}

}
