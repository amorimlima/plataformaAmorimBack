package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PendenciasProducaoAlunoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PendenciasProducaoAluno;


public class PendenciasProducaoAlunoService {

	public List<PendenciasProducaoAluno> listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		List<PendenciasProducaoAluno> result = dao.listarKey(id);
		pc.commitAndClose();
		return result;
	}
	
	public PendenciasProducaoAluno criarPendenciasProducaoAluno(PendenciasProducaoAluno p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}

	public PendenciasProducaoAluno deletarPendenciasProducaoAluno(PendenciasProducaoAluno p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	public PendenciasProducaoAluno atualizarPendenciasProducaoAluno(PendenciasProducaoAluno p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}

	public List<PendenciasProducaoAluno> listarAluno(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		List<PendenciasProducaoAluno> result = dao.listarAluno(id);
		pc.commitAndClose();
		return result;
	}

	public List<PendenciasProducaoAluno> listarAlunoRoteiro(int idaluno, int idroteiro) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		List<PendenciasProducaoAluno> result = dao.listarAlunoRoteiro(idaluno, idroteiro);
		pc.commitAndClose();
		return result;
	}

	public List<PendenciasProducaoAluno> listarall() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PendenciasProducaoAlunoDAO dao = DAOFactory.getPendenciasProducaoAlunoDAO(pc);
		List<PendenciasProducaoAluno> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}

}
