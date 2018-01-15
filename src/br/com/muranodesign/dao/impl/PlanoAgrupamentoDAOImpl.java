package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanoAgrupamentoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.PlanejamentoAula;
import br.com.muranodesign.model.PlanoAgrupamento;

public class PlanoAgrupamentoDAOImpl extends AbstractHibernateDAO implements PlanoAgrupamentoDAO {

	public PlanoAgrupamentoDAOImpl(HibernatePersistenceContext pc) {
		super(pc);
	}

	@SuppressWarnings("unchecked")
	public List<PlanoAgrupamento> listAll() {
		Criteria criteria = getSession().createCriteria(PlanoAgrupamento.class);
		List<PlanoAgrupamento> result = criteria.list();
		return result;
	}

	public PlanoAgrupamento listarkey(int id) {
		Criteria criteria = getSession().createCriteria(PlanoAgrupamento.class);
		criteria.add(Restrictions.eq("idPlano_agrupamento", id));
		PlanoAgrupamento result = (PlanoAgrupamento)criteria.list().get(0);
		return result;
	}

	@Override
	public void deletar(PlanoAgrupamento planoAgrupamento) {
		getSession().delete(planoAgrupamento);
		getSession().flush();
	}

	@Override
	public void atualizar(PlanoAgrupamento resultado) {
		getSession().delete(resultado);
		getSession().flush();
	}

	@Override
	public void criar(PlanoAgrupamento p) {
		synchronized (PlanoAgrupamentoDAOImpl.class){
			getSession().persist(p);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanejamentoAula> listarAgrupamento(int idAgrupamento) {
		Criteria criteria = getSession().createCriteria(PlanoAgrupamento.class);
		criteria.createAlias("agrupamento", "agrupamento");
		criteria.add(Restrictions.eq("agrupamento.Idagrupamento", idAgrupamento));
		criteria.setProjection(Projections.property("planejamentoAula"));
		List<PlanejamentoAula> result = (List<PlanejamentoAula>)criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Agrupamento> listarPlano(int idPlano) {
		Criteria criteria = getSession().createCriteria(PlanoAgrupamento.class);
		criteria.createAlias("planejamentoAula", "planejamentoAula");
		criteria.add(Restrictions.eq("planejamentoAula.Idplanejamento_aula", idPlano));
		criteria.setProjection(Projections.property("agrupamento"));
		List<Agrupamento> result = (List<Agrupamento>)criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<PlanoAgrupamento> listarAgrupamentoOficinaProfessor(
			int idProfessor, int idOficina, String[] arrayAgrupamentos) {
		Criteria criteria = getSession().createCriteria(PlanoAgrupamento.class);
		criteria.add(Restrictions.in("agrupamento", arrayAgrupamentos));
		criteria.createAlias("planejamentoAula", "planejamentoAula");
		criteria.add(Restrictions.eq("planejamentoAula.oficina", idOficina));
		criteria.add(Restrictions.eq("planejamentoAula.professor", idProfessor));
		criteria.setProjection(Projections.property("planejamentoAula"));
		List<PlanoAgrupamento> result = criteria.list();
		return result;
	}
	
}
