package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.OficinaProfessorDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.OficinaProfessor;

public class OficinaProfessorDAOImpl extends AbstractHibernateDAO implements OficinaProfessorDAO{
	
	public OficinaProfessorDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<OficinaProfessor> listAll() {
		
		Criteria criteria = getSession().createCriteria(OficinaProfessor.class);
		List<OficinaProfessor> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#criar(br.com.muranodesign.model.OficinaProfessor)
	 */
	public void criar(OficinaProfessor c) {
		synchronized (OficinaProfessorDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#deletar(br.com.muranodesign.model.OficinaProfessor)
	 */
	public void deletar(OficinaProfessor c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#atualizar(br.com.muranodesign.model.OficinaProfessor)
	 */
	public void atualizar(OficinaProfessor p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<OficinaProfessor> listarKey(int key){
		Criteria criteria = getSession().createCriteria(OficinaProfessor.class);
		criteria.add(Restrictions.eq("Idoficina_professor", key));
		List<OficinaProfessor> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#listarProfessor(int)
	 */
	@SuppressWarnings("unchecked")
	public List<OficinaProfessor> listarProfessor(int id){
		Criteria criteria = getSession().createCriteria(OficinaProfessor.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", id));
		List<OficinaProfessor> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#listarOficina(int)
	 */
	@SuppressWarnings("unchecked")
	public List<OficinaProfessor> listarOficina(int idOficina, int idProfessor){
		Criteria criteria = getSession().createCriteria(OficinaProfessor.class);
		
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.ne("professor.idprofessorFuncionario", idProfessor));
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		List<OficinaProfessor> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaProfessorDAO#listarPorOficina(int)
	 */
	@SuppressWarnings("unchecked")
	public List<OficinaProfessor> listarPorOficina(int idOficina){
		Criteria criteria = getSession().createCriteria(OficinaProfessor.class);
		
		criteria.createAlias("oficina", "oficina");
		criteria.add(Restrictions.eq("oficina.Idoficina", idOficina));
		List<OficinaProfessor> result = criteria.list();
		return result;
	}
}
