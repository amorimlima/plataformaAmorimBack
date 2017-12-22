package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.JeiffPeaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.JeiffPea;

public class JeiffPeaService {

	public List<JeiffPea> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		List<JeiffPea> result = dao.listarTodos();
		pc.commitAndClose();
		return result;
	}

	public List<JeiffPea> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		List<JeiffPea> result = dao.listarkey(id);
		pc.commitAndClose();
		return result;
	}
	
	public JeiffPea criarJeiffPea(JeiffPea jp) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		dao.criar(jp);
		pc.commitAndClose();
		return jp;
	}

	public JeiffPea atualizarJeiffPea(JeiffPea jp) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		dao.atualizar(jp);
		pc.commitAndClose();
		return jp;
	}

	public JeiffPea deletarJeiffPea(JeiffPea jp) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		dao.deletar(jp);
		pc.commitAndClose();
		return jp;
	}

	public List<JeiffPea> listarPeriodo(int idperiodo) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		List<JeiffPea> result = dao.listarPeriodo(idperiodo);
		pc.commitAndClose();
		return result;
	}

	public List<Object> listarAnexos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JeiffPeaDAO dao = DAOFactory.getJeiffPeaDAO(pc);
		List<Object> result = dao.listarAnexos();
		pc.commitAndClose();
		return result;
	}

}
