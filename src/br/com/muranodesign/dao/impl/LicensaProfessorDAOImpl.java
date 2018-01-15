package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.LicensaProfessorDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.LicensaProfessor;

public class LicensaProfessorDAOImpl extends AbstractHibernateDAO implements LicensaProfessorDAO {

	public LicensaProfessorDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}

	@Override
	public void criar(LicensaProfessor result) {
		synchronized (LicensaProfessor.class) {
			getSession().persist(result);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LicensaProfessor> listarkey(int id) {
		Criteria criteria = getSession().createCriteria(LicensaProfessor.class);
		criteria.add(Restrictions.eq("idLicensaProfessor", id));
		List<LicensaProfessor> result = criteria.list();
		return result;
	}

	@Override
	public void deletar(LicensaProfessor result) {
		synchronized (LicensaProfessor.class) {
			getSession().delete(result);
			getSession().flush();
		}
	}

	@Override
	public void update(LicensaProfessor result) {
		synchronized (LicensaProfessor.class) {
			getSession().merge(result);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LicensaProfessor> listarProfessor(int idProfessor) {
		Criteria criteria = getSession().createCriteria(LicensaProfessor.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		List<LicensaProfessor> result = criteria.list();
		return result;
	}

}
