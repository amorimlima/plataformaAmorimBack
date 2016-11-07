package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MuralAlunoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.MuralAluno;


public class MuralAlunoService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<MuralAluno> listarall() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por id
	 * @param id
	 * @return
	 */
	public List<MuralAluno> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarKey(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Deletar
	 * @param muralAluno
	 * @return
	 */
	public MuralAluno deletarMuralAluno(MuralAluno muralAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		dao.deletar(muralAluno);
		pc.commitAndClose();
		return muralAluno;
	}

	/**
	 * Criar
	 * @param muralAluno
	 * @return
	 */
	public MuralAluno criarMuralAluno(MuralAluno muralAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		dao.criar(muralAluno);
		pc.commitAndClose();
		return muralAluno;
	}
	
	/**
	 * Lista Aluno
	 * @param idaluno
	 * @return
	 */
	public List<MuralAluno> listarAluno(int idaluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarAluno(idaluno);
		pc.commitAndClose();
		return result;
	}

	public List<MuralAluno> listarMural(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralAlunoDAO dao = DAOFactory.geMuralAlunoDAO(pc);
		List<MuralAluno> result = dao.listarMural(id);
		pc.commitAndClose();
		return result;
	}

}
