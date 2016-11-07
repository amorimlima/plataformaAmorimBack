package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.SessaoForumGeralDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.SessaoForumGeral;

public class SessaoForumGeralService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<SessaoForumGeral> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		List<SessaoForumGeral> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por id
	 * @param key
	 * @return
	 */
	public List<SessaoForumGeral> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		List<SessaoForumGeral> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public SessaoForumGeral criarSessao(SessaoForumGeral p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public SessaoForumGeral atualizarSessao(SessaoForumGeral p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Delete
	 * @param p
	 * @return
	 */
	public SessaoForumGeral deletarSessao(SessaoForumGeral p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		SessaoForumGeralDAO dao = DAOFactory.getSessaoForumGeralDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
}
