package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MuralDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Mural;
import br.com.muranodesign.util.StringUtil;

public class MuralDAOImpl extends AbstractHibernateDAO implements MuralDAO{
	
	public MuralDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Mural> listAll() {
		
		Criteria criteria = getSession().createCriteria(Mural.class);
		List<Mural> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#criar(br.com.muranodesign.model.Mural)
	 */
	public void criar(Mural c) {
		synchronized (MuralDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#deletar(br.com.muranodesign.model.Mural)
	 */
	public void deletar(Mural c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#atualizar(br.com.muranodesign.model.Mural)
	 */
	public void atualizar(Mural p) {
		getSession().merge(p);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mural> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Mural.class);
		criteria.add(Restrictions.eq("Idmural", key));
		List<Mural> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MuralDAO#Range(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Mural>Range(String data2, String data, int idProfessor){
		Criteria criteria = getSession().createCriteria(Mural.class);

		StringUtil stringUtil = new StringUtil();
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		
		criteria.add(Restrictions.ge("data", stringUtil.converteStringData(data2)));
		criteria.add(Restrictions.lt("data", stringUtil.converteStringData(data)));
		List<Mural> result = criteria.list();
		
		
		return result;
	}

}
