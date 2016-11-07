package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ForumGeralRespostaDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ForumGeralResposta;

public class ForumGeralRespostaService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<ForumGeralResposta> listarTodos(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		List<ForumGeralResposta> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<ForumGeralResposta> listarkey(int key){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		List<ForumGeralResposta> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public ForumGeralResposta criarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public ForumGeralResposta atualizarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public ForumGeralResposta deletarForumQuestao(ForumGeralResposta p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ForumGeralRespostaDAO dao = DAOFactory.getForumGeralRespostaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
