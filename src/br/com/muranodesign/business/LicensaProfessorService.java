package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.LicensaProfessorDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.LicensaProfessor;

public class LicensaProfessorService {

	public LicensaProfessor criarLicensaProfessor(LicensaProfessor result) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		LicensaProfessorDAO dao = DAOFactory.getLicensaProfessorDAO(pc);
		dao.criar(result);
		pc.commitAndClose();
		return result;
	}

	public List<LicensaProfessor> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		LicensaProfessorDAO dao = DAOFactory.getLicensaProfessorDAO(pc);
		List<LicensaProfessor> result = dao.listarkey(id);
		pc.commitAndClose();
		return result;
	}

	public LicensaProfessor deletarLicensaProfessor(LicensaProfessor result) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		LicensaProfessorDAO dao = DAOFactory.getLicensaProfessorDAO(pc);
		dao.deletar(result);
		pc.commitAndClose();
		return result;
	}

	public LicensaProfessor updateLicensaProfessor(LicensaProfessor result) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		LicensaProfessorDAO dao = DAOFactory.getLicensaProfessorDAO(pc);
		dao.update(result);
		pc.commitAndClose();
		return result;
	}

	public List<LicensaProfessor> listarProfessor(int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		LicensaProfessorDAO dao = DAOFactory.getLicensaProfessorDAO(pc);
		List<LicensaProfessor> result = dao.listarProfessor(idProfessor);
		pc.commitAndClose();
		return result;
	}

}
