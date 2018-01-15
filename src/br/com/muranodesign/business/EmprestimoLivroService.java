package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.EmprestimoLivroDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.EmprestimoLivro;

public class EmprestimoLivroService {

	public EmprestimoLivro update(EmprestimoLivro resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		EmprestimoLivroDAO dao = DAOFactory.getEmprestimoLivroDAO(pc);
		dao.update(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public EmprestimoLivro criar(EmprestimoLivro resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		EmprestimoLivroDAO dao = DAOFactory.getEmprestimoLivroDAO(pc);
		dao.criar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<EmprestimoLivro> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		EmprestimoLivroDAO dao = DAOFactory.getEmprestimoLivroDAO(pc);
		List<EmprestimoLivro> resultado = dao.listarkey(id);
		pc.commitAndClose();
		return resultado;
	}

	public EmprestimoLivro deletar(EmprestimoLivro resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		EmprestimoLivroDAO dao = DAOFactory.getEmprestimoLivroDAO(pc);
		dao.deletar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<EmprestimoLivro> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		EmprestimoLivroDAO dao = DAOFactory.getEmprestimoLivroDAO(pc);
		List<EmprestimoLivro> resultado = dao.listarTodos();
		pc.commitAndClose();
		return resultado;
	}

}
