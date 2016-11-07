package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ComunicadoOficinasDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ComunicadoOficinas;

public class ComunicadoOficinasDAOImpl extends AbstractHibernateDAO implements ComunicadoOficinasDAO {

	public ComunicadoOficinasDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ComunicadoOficinasDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ComunicadoOficinas> listAll() {
		
		Criteria criteria = getSession().createCriteria(ComunicadoOficinas.class);
		List<ComunicadoOficinas> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ComunicadoOficinasDAO#criar(br.com.muranodesign.model.ComunicadoOficinas)
	 */
	public void criar(ComunicadoOficinas c) {
		synchronized (ComunicadoOficinasDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ComunicadoOficinasDAO#deletar(br.com.muranodesign.model.ComunicadoOficinas)
	 */
	public void deletar(ComunicadoOficinas c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ComunicadoOficinasDAO#atualizar(br.com.muranodesign.model.ComunicadoOficinas)
	 */
	public void atualizar(ComunicadoOficinas p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ComunicadoOficinasDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ComunicadoOficinas> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ComunicadoOficinas.class);
		criteria.add(Restrictions.eq("idComunicado", key));
		List<ComunicadoOficinas> result = criteria.list();
		return result;
	}
	
}
