package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.EmprestimoLivroDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.EmprestimoLivro;

public class EmprestimoLivroDAOImpl extends AbstractHibernateDAO implements EmprestimoLivroDAO {

	public EmprestimoLivroDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}
	
	@Override
	public void update(EmprestimoLivro resultado) {
		synchronized (EmprestimoLivroDAOImpl.class) {
			getSession().merge(resultado);
			getSession().flush();

		}
	}

	@Override
	public void criar(EmprestimoLivro resultado) {
		synchronized (EmprestimoLivroDAOImpl.class) {
			getSession().persist(resultado);
			getSession().flush();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmprestimoLivro> listarkey(int id) {
		Criteria criteria = getSession().createCriteria(EmprestimoLivro.class);
		criteria.add(Restrictions.eq("idEmprestimoLivro", id));
		List <EmprestimoLivro> result = criteria.list();
		return result;
	}

	@Override
	public void deletar(EmprestimoLivro resultado) {
		synchronized (EmprestimoLivroDAOImpl.class) {
			getSession().delete(resultado);
			getSession().flush();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmprestimoLivro> listarTodos() {
		Criteria criteria = getSession().createCriteria(EmprestimoLivro.class);
		List <EmprestimoLivro> result = criteria.list();
		return result;
	}

}
