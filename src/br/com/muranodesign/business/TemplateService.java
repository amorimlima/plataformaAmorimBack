package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.TemplateDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Template;

public class TemplateService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<Template> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TemplateDAO dao = DAOFactory.getTemplateDAO(pc);
		List<Template> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar by id
	 * @param key
	 * @return
	 */
	public List<Template> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TemplateDAO dao = DAOFactory.getTemplateDAO(pc);
		List<Template> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar 
	 * @param p
	 * @return
	 */
	public Template criarTemplate(Template p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TemplateDAO dao = DAOFactory.getTemplateDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Template atualizarTemplate(Template p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TemplateDAO dao = DAOFactory.getTemplateDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public Template deletarTemplate(Template p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		TemplateDAO dao = DAOFactory.getTemplateDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
