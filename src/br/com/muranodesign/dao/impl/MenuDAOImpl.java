package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MenuDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Menu;

public class MenuDAOImpl extends AbstractHibernateDAO implements MenuDAO {

	public MenuDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> listAll() {
			
			Criteria criteria = getSession().createCriteria(Menu.class);
			List<Menu> result = criteria.list();
			
			
			return result;
		} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuDAO#criar(br.com.muranodesign.model.Menu)
	 */
	public void criar(Menu c) {
		synchronized (MenuDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();
	
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuDAO#deletar(br.com.muranodesign.model.Menu)
	 */
	public void deletar(Menu c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuDAO#atualizar(br.com.muranodesign.model.Menu)
	 */
	public void atualizar(Menu p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Menu.class);
		criteria.add(Restrictions.eq("Idmenu", key));
		List<Menu> result = criteria.list();
		return result;
	}

}
