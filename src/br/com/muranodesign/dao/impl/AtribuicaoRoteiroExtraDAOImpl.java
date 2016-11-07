package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Roteiro;

public class AtribuicaoRoteiroExtraDAOImpl extends AbstractHibernateDAO  implements AtribuicaoRoteiroExtraDAO {
	
	public AtribuicaoRoteiroExtraDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#listAll()
		 */
		@SuppressWarnings("unchecked")
		public List<AtribuicaoRoteiroExtra> listAll(){
			Criteria criteria = getSession().createCriteria(AtribuicaoRoteiroExtra.class);
			List<AtribuicaoRoteiroExtra> result = criteria.list();
			return result;
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#criar(br.com.muranodesign.model.AtribuicaoRoteiroExtra)
		 */
		public void criar(AtribuicaoRoteiroExtra p){
			synchronized (AtribuicaoRoteiroExtraDAOImpl.class){
				getSession().persist(p);
				getSession().flush();	
			}
			
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#deletar(br.com.muranodesign.model.AtribuicaoRoteiroExtra)
		 */
		public void deletar(AtribuicaoRoteiroExtra p) {
			getSession().delete(p);
			getSession().flush();
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#atualizar(br.com.muranodesign.model.AtribuicaoRoteiroExtra)
		 */
		public void atualizar(AtribuicaoRoteiroExtra p) {
			getSession().merge(p);
			getSession().flush();
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#listarKey(br.com.muranodesign.model.Aluno, br.com.muranodesign.model.Roteiro)
		 */
		@SuppressWarnings("unchecked")
		public List<AtribuicaoRoteiroExtra> listarKey(Aluno aluno, Roteiro roteiro){
			Criteria criteria = getSession().createCriteria(AtribuicaoRoteiroExtra.class);
			criteria.add(Restrictions.eq("aluno", aluno));
			criteria.add(Restrictions.eq("roteiro", roteiro));
			List<AtribuicaoRoteiroExtra> result = criteria.list();
			return result;
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#listarid(int)
		 */
		@SuppressWarnings("unchecked")
		public List<AtribuicaoRoteiroExtra> listarid(int id){
			Criteria criteria = getSession().createCriteria(AtribuicaoRoteiroExtra.class);
			criteria.add(Restrictions.eq("idatribuicaoRoteiroExtra", id));
			List<AtribuicaoRoteiroExtra> result = criteria.list();
			return result;
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#listarRoteiro(br.com.muranodesign.model.Aluno, br.com.muranodesign.model.AnoLetivo)
		 */
		@SuppressWarnings("unchecked")
		public List<AtribuicaoRoteiroExtra> listarRoteiro(Aluno aluno, AnoLetivo ano){
			Criteria criteria = getSession().createCriteria(AtribuicaoRoteiroExtra.class);
			criteria.add(Restrictions.eq("aluno", aluno));
			criteria.add(Restrictions.eq("anoLetivo", ano));
			List<AtribuicaoRoteiroExtra> result = criteria.list();
			return result;
			
		}
		
		/*
		 * (non-Javadoc)
		 * @see br.com.muranodesign.dao.AtribuicaoRoteiroExtraDAO#listarAluno(br.com.muranodesign.model.Aluno)
		 */
		@SuppressWarnings("unchecked")
		public List<AtribuicaoRoteiroExtra> listarAluno(Aluno aluno){
			Criteria criteria = getSession().createCriteria(AtribuicaoRoteiroExtra.class);
			criteria.add(Restrictions.eq("aluno", aluno));
			List<AtribuicaoRoteiroExtra> result = criteria.list();
			return result;
		}
}
