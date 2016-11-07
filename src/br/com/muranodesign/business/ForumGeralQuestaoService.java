package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ForumGeralQuestaoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ForumGeralQuestao;

public class ForumGeralQuestaoService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<ForumGeralQuestao> listarTodos(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralQuestaoDAO dao = DAOFactory.getForumGeralQuestaoDAO(pc);
		List<ForumGeralQuestao> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<ForumGeralQuestao> listarkey(int key){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralQuestaoDAO dao = DAOFactory.getForumGeralQuestaoDAO(pc);
		List<ForumGeralQuestao> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public ForumGeralQuestao criarForumQuestao(ForumGeralQuestao p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralQuestaoDAO dao = DAOFactory.getForumGeralQuestaoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public ForumGeralQuestao atualizarForumQuestao(ForumGeralQuestao p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralQuestaoDAO dao = DAOFactory.getForumGeralQuestaoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public ForumGeralQuestao deletarForumQuestao(ForumGeralQuestao p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralQuestaoDAO dao = DAOFactory.getForumGeralQuestaoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
}

