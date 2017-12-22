package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AlunoConceitoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AlunoConceito;

public class AlunoConceitoDAOImpl extends AbstractHibernateDAO implements AlunoConceitoDAO {

	public AlunoConceitoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}

	@Override
	public void criar(AlunoConceito resultado) {
		synchronized (AlunoConceitoDAOImpl.class) {
			getSession().persist(resultado);
			getSession().flush();

		}
	}

	@Override
	public void update(AlunoConceito resultado) {
		synchronized (AlunoConceitoDAOImpl.class) {
			getSession().merge(resultado);
			getSession().flush();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlunoConceito> listarKey(int id) {
		Criteria criteria = getSession().createCriteria(AlunoConceito.class);
		criteria.add(Restrictions.eq("idAlunoConceito", id));
		List <AlunoConceito> result = criteria.list();
		return result;
	}

	@Override
	public void deletar(AlunoConceito resultado) {
		synchronized (AlunoConceitoDAOImpl.class) {
			getSession().delete(resultado);
			getSession().flush();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlunoConceito> listarTodos() {
		Criteria criteria = getSession().createCriteria(AlunoConceito.class);
		List <AlunoConceito> result = criteria.list();
		return result;
	}
	
	
	
}
