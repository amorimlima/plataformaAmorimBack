package br.com.muranodesign.business;


import java.util.List;

import br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Roteiro;

public class AtribuicaoRoteiroExtraService {

	/**
	 * Criar roteiro extra
	 * @param p
	 * @return p
	 */
	public AtribuicaoRoteiroExtra criarRoteiroExtra(AtribuicaoRoteiroExtra p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Alterar roteiro extra
	 * @param p
	 * @return
	 */
	public AtribuicaoRoteiroExtra  alterarRoteiro(AtribuicaoRoteiroExtra p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar Roteiro
	 * @param p
	 * @return
	 */
	public AtribuicaoRoteiroExtra deletarRoteiroExtra(AtribuicaoRoteiroExtra p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar todos
	 * @return list
	 */
	public List<AtribuicaoRoteiroExtra> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		List<AtribuicaoRoteiroExtra> result = dao.listAll();
		pc.commitAndClose();
		return result;
		
	}
	
	/**
	 * Listar por aluno e roteiro
	 * @param aluno
	 * @param roteiro
	 * @return list
	 */
	public List<AtribuicaoRoteiroExtra> listarkey(Aluno aluno, Roteiro roteiro) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		List<AtribuicaoRoteiroExtra> result = dao.listarKey(aluno, roteiro);
		pc.commitAndClose();
		return result;
		
	}
	
	/**
	 * Listar por id
	 * @param id
	 * @return list
	 */
	public List<AtribuicaoRoteiroExtra> listarid(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		List<AtribuicaoRoteiroExtra> result = dao.listarid(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por aluno e ano letivo
	 * @param aluno
	 * @param ano
	 * @return list
	 */
	public List<AtribuicaoRoteiroExtra> listarAluno(Aluno aluno, AnoLetivo ano){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		List<AtribuicaoRoteiroExtra> result = dao.listarRoteiro(aluno, ano);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por aluno
	 * @param aluno
	 * @return list
	 */
	public List<AtribuicaoRoteiroExtra> listarAluno(Aluno aluno){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AtribuicaoRoteiroExtraDAO dao = DAOFactory.getAtribuicaoRoteiroExtraDAO(pc);
		List<AtribuicaoRoteiroExtra> result = dao.listarAluno(aluno);
		pc.commitAndClose();
		return result;
	}
	
}
