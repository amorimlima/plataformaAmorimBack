package br.com.muranodesign.dao.impl;

import java.util.Date;
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

	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarProfessorOficina(int idProfessor, int idOficina) {
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}

	@Override
	public List<PlanejamentoAula> listarIntervalo(Date inicio, Date fim,
			int idProfessor) {
		Criteria criteria = getSession().createCriteria(PlanejamentoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.add(Restrictions.or((Restrictions.or(
				Restrictions.between("data_inicio", inicio, fim),
				Restrictions.between("data_fim", inicio, fim))),
			Restrictions.and(Restrictions.lt("data_inicio", inicio),
							 Restrictions.gt("data_fim", fim)))
);
		List<PlanejamentoAula> result = criteria.list();
		return result;
	}
	
}
