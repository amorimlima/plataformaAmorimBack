package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ForumGeralRespostaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ForumGeralResposta;

public class ForumGeralRespostaDAOImpl   extends AbstractHibernateDAO implements ForumGeralRespostaDAO{
	
	public ForumGeralRespostaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralRespostaDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ForumGeralResposta> listAll() {
		
		Criteria criteria = getSession().createCriteria(ForumGeralResposta.class);
		List<ForumGeralResposta> result = criteria.list();
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralRespostaDAO#criar(br.com.muranodesign.model.ForumGeralResposta)
	 */
	public void criar(ForumGeralResposta c) {
		synchronized (ForumGeralRespostaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralRespostaDAO#deletar(br.com.muranodesign.model.ForumGeralResposta)
	 */
	public void deletar(ForumGeralResposta c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralRespostaDAO#atualizar(br.com.muranodesign.model.ForumGeralResposta)
	 */
	public void atualizar(ForumGeralResposta p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumGeralRespostaDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ForumGeralResposta> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ForumGeralResposta.class);
		criteria.add(Restrictions.eq("idForumResposta", key));
		List<ForumGeralResposta> result = criteria.list();
		return result;
	}
	
}
