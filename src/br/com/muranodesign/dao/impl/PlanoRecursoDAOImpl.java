package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanoRecursoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanoRecurso;
import br.com.muranodesign.model.ProfessorFuncionario;

/** Classe responsável por implementar os métodos para operações com o banco de dados
 * @author Pedro Henrique dos Santos
 * @see PlanoRecurso
 */
public class PlanoRecursoDAOImpl extends AbstractHibernateDAO implements PlanoRecursoDAO {

	public PlanoRecursoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}
	
	/** Método responsável por listar os registros da tabela plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @return lstPlanoRecurso Lista com todos os registros da tabela de Plano Recurso
	 * @see PlanoRecurso
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlanoRecurso> listAll() {
		Criteria criteria = getSession().createCriteria(PlanoRecurso.class);
		List<PlanoRecurso> lstPlanoRecurso = criteria.list();
		return lstPlanoRecurso;
	}
	
	/** Método responsável por inserir dados na tabela plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto do tipo PlanoRecurso
	 * @see PlanoRecurso
	 */
	@Override
	public void criar(PlanoRecurso p){
		synchronized (PlanoRecursoDAOImpl.class){
			getSession().persist(p);
			getSession().flush();
		}		
	}
	
	/** Método responsável por deletar registros da tabela plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto do tipo PlanoRecurso
	 */
	@Override
	public void deletar(PlanoRecurso p) {
		getSession().delete(p);
		getSession().flush();
		
	}

	/** Método responsável por atualizar registros da tabela plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @param p Objeto do tipo PlanoRecurso
	 */
	@Override
	public void atualizar(PlanoRecurso p) {
		getSession().merge(p);
		getSession().flush();
		
	}

	/** Método responsável por trazer registro de plano_recurso de acordo com o id informado
	 * @param key Representa o id da tabela
	 * @return Lista com objeto correspondente ao id informado
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlanoRecurso> listarKey(int key) {
		Criteria criteria = getSession().createCriteria(PlanoRecurso.class);
		criteria.add(Restrictions.eq("idPlanoRecurso", key));
		List<PlanoRecurso> lstPlanoRecurso = criteria.list();
		return lstPlanoRecurso;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanoRecurso> listarRecursoPlanoTotal(int idPlano) {
		Criteria criteria = getSession().createCriteria(PlanoRecurso.class);
		criteria.createAlias("planejamentoAula", "planejamentoAula");
		criteria.add(Restrictions.eq("planejamentoAula.Idplanejamento_aula", idPlano));
		List<PlanoRecurso> lstPlanoRecurso = criteria.list();
		return lstPlanoRecurso;
	}

	@SuppressWarnings("unchecked")
	public List<PlanoRecurso> listarRecursoPlano(int idPlano) {
		Criteria criteria = getSession().createCriteria(PlanoRecurso.class);
		criteria.createAlias("planejamentoAula", "planejamentoAula");
		criteria.add(Restrictions.eq("planejamentoAula.Idplanejamento_aula", idPlano));
		criteria.add(Restrictions.eq("visibilidade", 1));
		List<PlanoRecurso> lstPlanoRecurso = criteria.list();
		return lstPlanoRecurso;
	}

	@Override
	public List<PlanoRecurso> buscarRecurso(String nome, int tipo, ProfessorFuncionario idProfessor) {
		Criteria criteria = getSession().createCriteria(PlanoRecurso.class);
		if (nome != null && !nome.equals(""))
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		if(tipo != 0){
			criteria.createAlias("tipoRecurso", "tipoRecurso");
			criteria.add(Restrictions.eq("tipoRecurso.idtipoRecursoAprendizagem", tipo));
		}
		criteria.createAlias("planejamentoAula", "planejamentoAula");
		criteria.add(Restrictions.eq("planejamentoAula.professor", idProfessor));
		List<PlanoRecurso> lstPlanoRecurso = criteria.list();
		return lstPlanoRecurso;
	}

}
