package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.BlogDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Blog;

public class BlogService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<Blog> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por chave
	 * @param key
	 * @return
	 */
	public List<Blog> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Blog criarBlog(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public Blog atualizarBloga(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar Blog
	 * @param p
	 * @return
	 */
	public Blog deletarBlog(Blog p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 	Listar por oficina
	 * @param id
	 * @return
	 */
	public List<Blog> listarOficina(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarOficina(id);
		pc.commitAndClose();
		return result;
	}

	public List<Blog> listarAgrupamento(int idAgrupamento) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarAgrupamento(idAgrupamento);
		pc.commitAndClose();
		return result;
	}

	public List<Blog> listarTutoria(int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarTutoria(idProfessor);
		pc.commitAndClose();
		return result;
	}

	public List<Blog> listarOficinaProfessor(int idOficina, int idProfessor) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarOficinaProfessor(idOficina, idProfessor);
		pc.commitAndClose();
		return result;
	}

	public List<Blog> listarAutor(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		BlogDAO dao = DAOFactory.getBlogDAO(pc);
		List<Blog> result = dao.listarAutor(id);
		pc.commitAndClose();
		return result;
	}
}
