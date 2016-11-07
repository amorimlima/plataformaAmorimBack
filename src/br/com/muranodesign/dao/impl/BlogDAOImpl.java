package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.BlogDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Blog;

public class BlogDAOImpl extends AbstractHibernateDAO implements BlogDAO{

	public BlogDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Blog> listAll() {
			
			Criteria criteria = getSession().createCriteria(Blog.class);
			List<Blog> result = criteria.list();
			
			
			return result;
		}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#criar(br.com.muranodesign.model.Blog)
	 */
	public void criar(Blog c) {
		synchronized (BlogDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#deletar(br.com.muranodesign.model.Blog)
	 */
	public void deletar(Blog c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#atualizar(br.com.muranodesign.model.Blog)
	 */
	public void atualizar(Blog p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Blog> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Blog.class);
		criteria.add(Restrictions.eq("Idblog", key));
		List<Blog> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.BlogDAO#listarOficina(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Blog> listarOficina(int id){
		Criteria criteria = getSession().createCriteria(Blog.class);
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", id));
		List<Blog> result = criteria.list();
		return result;
	}
	
}
