package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MenuPerfilDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.MenuPerfil;

public class MenuPerfilDAOImpl extends AbstractHibernateDAO implements MenuPerfilDAO{

	public MenuPerfilDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#listAll()
	 */
	public List<MenuPerfil> listAll() {
		
		Criteria criteria = getSession().createCriteria(MenuPerfil.class);
		List<MenuPerfil> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#criar(br.com.muranodesign.model.MenuPerfil)
	 */
	public void criar(MenuPerfil c) {
		synchronized (MenuPerfilDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#deletar(br.com.muranodesign.model.MenuPerfil)
	 */
	public void deletar(MenuPerfil c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#atualizar(br.com.muranodesign.model.MenuPerfil)
	 */
	public void atualizar(MenuPerfil p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#listarKey(int)
	 */
	public List<MenuPerfil> listarKey(int key){
		Criteria criteria = getSession().createCriteria(MenuPerfil.class);
		criteria.add(Restrictions.eq("Idmenu_perfil", key));
		List<MenuPerfil> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MenuPerfilDAO#listarUser(int)
	 */
	@SuppressWarnings("unchecked")
	public List<MenuPerfil> listarUser(int id){
		Criteria criteria = getSession().createCriteria(MenuPerfil.class);
		criteria.createAlias("perfil", "perfil");
		criteria.add(Restrictions.eq("perfil.idperfil", id));
		criteria.addOrder(Order.asc("ordem"));
		List<MenuPerfil> result = criteria.list();
		return result;
	}
	
}
