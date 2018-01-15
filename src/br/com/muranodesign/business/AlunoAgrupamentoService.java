package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AlunoAgrupamentoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.AlunoAgrupamento;

public class AlunoAgrupamentoService {
	
	/**
	 * Lista todos 
	 * @return list
	 */
	public List<AlunoAgrupamento> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista por id
	 * @param key
	 * @return list
	 */
	public List<AlunoAgrupamento> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Cria
	 * @param p
	 * @return p
	 */
	public AlunoAgrupamento criarAlunoAgrupamento(AlunoAgrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualiza
	 * @param p
	 * @return p
	 */
	public AlunoAgrupamento atualizarAlunoAgrupamento(AlunoAgrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deleta
	 * @param p
	 * @return p
	 */
	public AlunoAgrupamento deletarAlunoAgrupamento(AlunoAgrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Lista por id de agrupamento
	 * @param id
	 * @return list
	 */
	public List<AlunoAgrupamento> listarAgrupamento(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listarAgrupamento(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista por id de aluno e id de agrupamento
	 * @param idAluno
	 * @param idAgrupamento
	 * @return list
	 */
	public List<AlunoAgrupamento> listarAlunoAgrupamento(int idAluno, int idAgrupamento){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listarAlunoAgrupamento(idAluno, idAgrupamento);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista por id de agrupamento
	 * @param idAluno
	 * @return
	 */
	public List<AlunoAgrupamento> listarAluno(int idAluno){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listarAluno(idAluno);
		pc.commitAndClose();
		return result;
	}

	public List<AlunoAgrupamento> listarLikeAluno(String nome) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoAgrupamentoDAO dao = DAOFactory.getAlunoAgrupamentoDAO(pc);
		List<AlunoAgrupamento> result = dao.listarLikeAluno(nome);
		pc.commitAndClose();
		return result;
	}

}
