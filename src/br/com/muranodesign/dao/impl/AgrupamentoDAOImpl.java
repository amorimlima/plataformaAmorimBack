package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AgrupamentoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Agrupamento;


public class AgrupamentoDAOImpl extends AbstractHibernateDAO implements AgrupamentoDAO{
	
	public AgrupamentoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Agrupamento> listAll() {
		
		Criteria criteria = getSession().createCriteria(Agrupamento.class);
		List<Agrupamento> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#criar(br.com.muranodesign.model.Agrupamento)
	 */
	public void criar(Agrupamento c) {
		synchronized (AgrupamentoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#deletar(br.com.muranodesign.model.Agrupamento)
	 */
	public void deletar(Agrupamento c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#atualizar(br.com.muranodesign.model.Agrupamento)
	 */
	public void atualizar(Agrupamento p) {
		getSession().merge(p);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Agrupamento> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Agrupamento.class);
		criteria.add(Restrictions.eq("Idagrupamento", key));
		List<Agrupamento> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#listaAnoLetivo(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Agrupamento> listaAnoLetivo(int idAno){
		Criteria criteria = getSession().createCriteria(Agrupamento.class);
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.idanoLetivo", idAno));
		List<Agrupamento> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AgrupamentoDAO#ListarPorCiclo(int)
	 */
	
	//Alteração que pode mudar
	@SuppressWarnings("unchecked")
	public List<Agrupamento> ListarPorCiclo(int id){
		Criteria criteria = getSession().createCriteria(Agrupamento.class);
		criteria.createAlias("ciclo", "ciclo");
		criteria.add(Restrictions.eq("ciclo.Idciclos", id));
		List<Agrupamento> result = criteria.list();
		return result;
	}
	//Alteração que pode mudar

}
