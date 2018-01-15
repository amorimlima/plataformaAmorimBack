package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PlanoRecursoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PlanoRecurso;
import br.com.muranodesign.model.ProfessorFuncionario;

/** Classe respons�vel por conter os m�todos de neg�cio de PlanoRecurso
 * @author Pedro Henrique dos Santos
 * @see PlanoRecurso
 * @see PlanoRecursoDAO
 */
public class PlanoRecursoService {
	
	/** M�todo respons�vel por chamar o m�todo de listagem de plano recurso
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
	
	/** M�todo respons�vel por chamar o m�todo de listagem por id de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param key chave da tabela fornecida pelo usu�rio
	 * @return lstPlanoRecurso Objeto listado de acordo com o id 
	 */
	public List<PlanoRecurso> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		List<PlanoRecurso> lstPlanoRecurso = dao.listarKey(key);
		pc.commitAndClose();
		return lstPlanoRecurso;
	}
	
	/** M�todo respons�vel por chamar o m�todo de inser��o de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser persistido
	 * @return Objeto rec�m criado
	 */
	public PlanoRecurso criarPlanoRecurso(PlanoRecurso p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/** M�todo respons�vel por chamar o m�todo de update de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser atualizado
	 * @return Objeto rec�m atualizado
	 */
	public PlanoRecurso atualizarPlanoRecurso(PlanoRecurso p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoRecursoDAO dao = DAOFactory.getPlanoRecursoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/** M�todo respons�vel por chamar o m�todo de dele��o de plano recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto a ser deletado
	 * @return Objeto rec�m deletado
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
