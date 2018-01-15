package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AlunoConceitoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.AlunoConceito;

public class AlunoConceitoService {

	public AlunoConceito criar(AlunoConceito resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoConceitoDAO dao = DAOFactory.getAlunoConceitoDAO(pc);
		dao.criar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public AlunoConceito update(AlunoConceito resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoConceitoDAO dao = DAOFactory.getAlunoConceitoDAO(pc);
		dao.update(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<AlunoConceito> listarKey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoConceitoDAO dao = DAOFactory.getAlunoConceitoDAO(pc);
		List<AlunoConceito> resultado = dao.listarKey(id);
		pc.commitAndClose();
		return resultado;
	}

	public AlunoConceito deletar(AlunoConceito resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoConceitoDAO dao = DAOFactory.getAlunoConceitoDAO(pc);
		dao.deletar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<AlunoConceito> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoConceitoDAO dao = DAOFactory.getAlunoConceitoDAO(pc);
		List<AlunoConceito> resultado = dao.listarTodos();
		pc.commitAndClose();
		return resultado;
	}


}
