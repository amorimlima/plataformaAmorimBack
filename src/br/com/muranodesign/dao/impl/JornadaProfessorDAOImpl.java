package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.JornadaProfessorDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.JornadaProfessor;
import br.com.muranodesign.model.PlanejamentoRoteiro;

//Alteração que pode mudar
public class JornadaProfessorDAOImpl extends AbstractHibernateDAO implements JornadaProfessorDAO{
	
	public JornadaProfessorDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<JornadaProfessor> listAll() {
		Criteria criteria = getSession().createCriteria(JornadaProfessor.class);
		List<JornadaProfessor> result = criteria.list();
		
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#criar(br.com.muranodesign.model.JornadaProfessor)
	 */
	public void criar(JornadaProfessor p) {
		synchronized (JornadaProfessorDAOImpl.class) {
			getSession().persist(p);
			getSession().flush();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#deletar(br.com.muranodesign.model.JornadaProfessor)
	 */
	public void deletar(JornadaProfessor p) {
		getSession().delete(p);
		getSession().flush();
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#atualizar(br.com.muranodesign.model.JornadaProfessor)
	 */
	public void atualizar(JornadaProfessor p) {
		getSession().merge(p);
		getSession().flush();
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<JornadaProfessor> listarKey(int key) {
		Criteria criteria = getSession().createCriteria(JornadaProfessor.class);
		criteria.add(Restrictions.eq("Idjornada_professor", key));
		List<JornadaProfessor> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#ListarTodos(int)
	 */
	@SuppressWarnings("unchecked")
	public List<JornadaProfessor> ListarTodos(int professor){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("ocupado", 0));
		criteria.add(Restrictions.eq("professor", professor));
		criteria.add(Restrictions.isNotNull("oficina_professor"));
		List<JornadaProfessor> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#Total()
	 */
	public long Total(int professor){
		Criteria criteria = getSession().createCriteria(JornadaProfessor.class);
		criteria.add(Restrictions.eq("professor", professor));
		criteria.setProjection(Projections.count("Idjornada_professor"));
	
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#Disponivel(int)
	 */
	public long Disponivel(int professor){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("ocupado", 0));
		criteria.add(Restrictions.eq("professor", professor));
		criteria.add(Restrictions.isNotNull("oficina_professor"));
		criteria.setProjection(Projections.count("Idjornada_professor"));
	
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.JornadaProfessorDAO#extra(int)
	 */
	public long extra(int professor){
		Criteria criteria = getSession().createCriteria(PlanejamentoRoteiro.class);
		criteria.add(Restrictions.eq("ocupado", 0));
		criteria.add(Restrictions.eq("professor", professor));
		criteria.add(Restrictions.isNotNull("extra"));
		criteria.setProjection(Projections.count("Idjornada_professor"));
	
		long result = (Long) criteria.list().get(0);
		return result;
	}

}
