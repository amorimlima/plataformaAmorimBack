package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ForumGeralQuestaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ForumGeralQuestao;

public class ForumGeralQuestaoDAOImpl extends AbstractHibernateDAO implements ForumGeralQuestaoDAO {
	
	public ForumGeralQuestaoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralQuestaoDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ForumGeralQuestao> listAll() {
		
		Criteria criteria = getSession().createCriteria(ForumGeralQuestao.class);
		List<ForumGeralQuestao> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralQuestaoDAO#criar(br.com.muranodesign.model.ForumGeralQuestao)
	 */
	public void criar(ForumGeralQuestao c) {
		synchronized (ForumGeralQuestaoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralQuestaoDAO#deletar(br.com.muranodesign.model.ForumGeralQuestao)
	 */
	public void deletar(ForumGeralQuestao c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralQuestaoDAO#atualizar(br.com.muranodesign.model.ForumGeralQuestao)
	 */
	public void atualizar(ForumGeralQuestao p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralQuestaoDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ForumGeralQuestao> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ForumGeralQuestao.class);
		criteria.add(Restrictions.eq("idForumQuestao", key));
		List<ForumGeralQuestao> result = criteria.list();
		return result;
	}


}
