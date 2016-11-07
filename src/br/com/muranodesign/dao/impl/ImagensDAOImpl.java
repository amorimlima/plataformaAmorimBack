package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ImagensDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Imagens;

public class ImagensDAOImpl extends AbstractHibernateDAO implements ImagensDAO {
	
	public ImagensDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ImagensDAO#listAll()
	 */
	public List<Imagens> listAll() {
		
		Criteria criteria = getSession().createCriteria(Imagens.class);
		List<Imagens> result = criteria.list();
		
		
		return result;
	} 
	
/*
 * (non-Javadoc)
 * @see br.com.muranodesign.dao.ImagensDAO#criar(br.com.muranodesign.model.Imagens)
 */
	public void criar(Imagens c) {
		synchronized (ImagensDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ImagensDAO#deletar(br.com.muranodesign.model.Imagens)
	 */
	public void deletar(Imagens c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ImagensDAO#atualizar(br.com.muranodesign.model.Imagens)
	 */
	public void atualizar(Imagens p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ImagensDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Imagens> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Imagens.class);
		criteria.add(Restrictions.eq("Idimagens", key));
		List<Imagens> result = criteria.list();
		return result;
	}
	

}
