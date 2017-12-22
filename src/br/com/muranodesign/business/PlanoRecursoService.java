package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PlanoRecursoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PlanoRecurso;
import br.com.muranodesign.model.ProfessorFuncionario;

/** Classe responsável por conter os métodos de negócio de PlanoRecurso
 * @author Pedro Henrique dos Santos
 * @see PlanoRecurso
 * @see PlanoRecursoDAO
 */
public class PlanoRecursoService {
	
	/** Método responsável por chamar o método de listagem de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @return lstPlanoRecurso Lista com os planos de recurso
	 */
	public List<PlanoRecurso> listarTodos(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> lstPlanoRecurso = dao.listAll();
		pc.commitAndClose();
		return lstPlanoRecurso;
	}
	
	/** Método responsável por chamar o método de listagem por id de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param key chave da tabela fornecida pelo usuário
	 * @return lstPlanoRecurso Objeto listado de acordo com o id 
	 */
	public List<PlanoRecurso> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> lstPlanoRecurso = dao.listarKey(key);
		pc.commitAndClose();
		return lstPlanoRecurso;
	}
	
	/** Método responsável por chamar o método de inserção de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser persistido
	 * @return Objeto recém criado
	 */
	public PlanoRecurso criarPlanoRecurso(PlanoRecurso p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/** Método responsável por chamar o método de update de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser atualizado
	 * @return Objeto recém atualizado
	 */
	public PlanoRecurso atualizarPlanoRecurso(PlanoRecurso p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/** Método responsável por chamar o método de deleção de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser deletado
	 * @return Objeto recém deletado
	 */
	public PlanoRecurso deletarPlanoRecurso(PlanoRecurso p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

	public List<PlanoRecurso> recursoPlanoTotal(int idPlano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> result = dao.listarRecursoPlanoTotal(idPlano);
		pc.commitAndClose();
		return result;
	}

	public List<PlanoRecurso> recursoPlano(int idPlano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> result = dao.listarRecursoPlano(idPlano);
		pc.commitAndClose();
		return result;
	}

	public List<PlanoRecurso> buscarRecurso(String nome, int tipo,
			ProfessorFuncionario professorFuncionario) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> result = dao.buscarRecurso(nome, tipo, professorFuncionario);
		pc.commitAndClose();
		return result;
	}
}
