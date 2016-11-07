package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.JornadaProfessorDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.JornadaProfessor;

//Alteração que pode mudar
public class JornadaProfessorService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<JornadaProfessor> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		List<JornadaProfessor> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar key
	 * @param key
	 * @return
	 */
	public List<JornadaProfessor> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		List<JornadaProfessor> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public JornadaProfessor criarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar Jornada Professor
	 * @param p
	 * @return
	 */
	public JornadaProfessor atualizarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar Jornada Professor
	 * @param p
	 * @return
	 */
	public JornadaProfessor deletarJornadaProfessor(JornadaProfessor p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar todos
	 * @param professor
	 * @return
	 */
	public List<JornadaProfessor> ListarTodos(int professor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		List<JornadaProfessor> result = dao.ListarTodos(professor);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Total
	 * @param professor
	 * @return
	 */
	public long Total(int professor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.Total(professor);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Disponivel
	 * @param professor
	 * @return
	 */
	public long Disponivel(int professor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.Disponivel(professor);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Extra
	 * @param professor
	 * @return
	 */
	public long extra(int professor){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		JornadaProfessorDAO dao = DAOFactory.getJornadaProfessorDAO(pc);
		long result = dao.extra(professor);
		pc.commitAndClose();
		return result;
	}
}
