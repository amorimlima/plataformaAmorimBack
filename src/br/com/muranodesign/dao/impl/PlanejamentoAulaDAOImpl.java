package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanejamentoAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanejamentoAula;

public class PlanejamentoAulaDAOImpl extends AbstractHibernateDAO implements PlanejamentoAulaDAO{

	public PlanejamentoAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		List<PlanejamentoAula> result = criteria.list();
		
		
		return result;
	} 

	/*
	 * 0(non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#criar(br.com.muranodesign.model.PlanejamentoAula)
	 */
	public void criar(PlanejamentoAula c) {
		synchronized (PlanejamentoAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#deletar(br.com.muranodesign.model.PlanejamentoAula)
	 */
	public void deletar(PlanejamentoAula c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#atualizar(br.com.muranodesign.model.PlanejamentoAula)
	 */
	public void atualizar(PlanejamentoAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.add(Restrictions.eq("Idplanejamento_aula", key));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#listarProfessor(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarProfessor(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", id));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#listarPlanoAula(int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarPlanoAula(int id){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("planoAula", "planoAula");
		criteria.add(Restrictions.eq("planoAula.Idplano_aula", id));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.PlanejamentoAulaDAO#listarProfessorObjetivoAula(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarProfessorObjetivoAula(int idProfessor, int idObjetivoAula, int idplanoAula){
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("objetivoAula", "objetivoAula");
		criteria.add(Restrictions.eq("objetivoAula.Idobjetivo_aula", idObjetivoAula));
		criteria.createAlias("planoAula", "planoAula");
		criteria.add(Restrictions.eq("planoAula.Idplano_aula", idplanoAula));
		
		
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
}
