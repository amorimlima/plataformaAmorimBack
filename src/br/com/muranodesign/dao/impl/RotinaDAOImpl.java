package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.RotinaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.Rotina;

public class RotinaDAOImpl extends AbstractHibernateDAO implements RotinaDAO{
	
	public RotinaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Rotina> listAll() {
		
		Criteria criteria = getSession().createCriteria(Rotina.class);
		List<Rotina> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#criar(br.com.muranodesign.model.Rotina)
	 */
	public void criar(Rotina c) {
		synchronized (RotinaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#deletar(br.com.muranodesign.model.Rotina)
	 */
	public void deletar(Rotina c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#atualizar(br.com.muranodesign.model.Rotina)
	 */
	public void atualizar(Rotina p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Rotina> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.add(Restrictions.eq("Idrotina", key));
		List<Rotina> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#listarInconsistencia(long, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Rotina> listarInconsistencia(long hora, int idSemana/*, int idSala*/){
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.add(Restrictions.eq("hora", hora));
		criteria.createAlias("dia", "dia");
		criteria.add(Restrictions.eq("dia.Idsemana", idSemana));
		List<Rotina> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#listarPorOficina(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Rotina> listarPorOficina(int idOficina){
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		List<Rotina> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.RotinaDAO#listarPorAgrupamento(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Oficina> listarPorAgrupamento(int idAgrupamento){
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", idAgrupamento));
		criteria.setProjection(Projections.distinct(Projections.property("oficina")));
		List<Oficina> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Rotina> listarRotinaAlunoDia(int idagrupamento, int iddiaSemana) {
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", idagrupamento));
		criteria.createAlias("dia", "dia");
		criteria.add(Restrictions.eq("dia.Idsemana", iddiaSemana));
		List<Rotina> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Rotina> listarRotinaOficinaDia(int idoficina, int idDiaSemana) {
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idoficina));
		criteria.createAlias("dia", "dia");
		criteria.add(Restrictions.eq("dia.Idsemana", idDiaSemana));
		List<Rotina> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Rotina> listarRotinaTutoriaDia(Integer idtutoria, int idDiaSemana) {
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("tutoria", "tutoria");
		criteria.add(Restrictions.eq("tutoria.idtutoria", idtutoria));
		criteria.createAlias("dia", "dia");
		criteria.add(Restrictions.eq("dia.Idsemana", idDiaSemana));
		List<Rotina> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Agrupamento> listarAgrupamentoPorOficina(int idOficina) {
		Criteria criteria = getSession().createCriteria(Rotina.class);
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		criteria.setProjection(Projections.distinct(Projections.property("agrupamento")));
		List<Agrupamento> result = criteria.list();
		return result;
	}

}
