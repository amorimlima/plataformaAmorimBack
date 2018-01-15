/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package br.com.muranodesign.dao.impl;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.dao.AlunoVariavelDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.Periodo;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class AlunoVariavelDAOImpl extends AbstractHibernateDAO implements AlunoVariavelDAO {

	/**
	 * Instantiates a new aluno variavel dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public AlunoVariavelDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listAll() {
		
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.addOrder(Order.asc("aluno"));
		List<AlunoVariavel> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listAll(int status) {
		
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		
		criteria.add(Restrictions.eq("ativo", status));
		criteria.addOrder(Order.asc("aluno"));
		
		List<AlunoVariavel> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#criar(br.com.muranodesign.model.AlunoVariavel)
	 */
	public void criar(AlunoVariavel c) {
		synchronized (AlunoVariavelDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#deletar(br.com.muranodesign.model.AlunoVariavel)
	 */
	public void deletar(AlunoVariavel c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#atualizar(br.com.muranodesign.model.AlunoVariavel)
	 */
	public void atualizar(AlunoVariavel p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listarKey(int key){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.add(Restrictions.eq("idalunoVariavel", key));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaAluno(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaAluno(int idAluno){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.add(Restrictions.eq("ativo", 1));
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaAlunoInativo(int idAluno){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaGrupo(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaGrupo(int idGrupo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("grupo", "grupo");
		criteria.add(Restrictions.eq("grupo.idgrupo", idGrupo));
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.addOrder(Order.asc("aluno"));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaAnoEstudo(br.com.muranodesign.model.AnoEstudo)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaAnoEstudo(AnoEstudo ano){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String anoLetivo = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivoObj = new AnoLetivoService().listarAnoLetivo(anoLetivo).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivoObj));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.add(Restrictions.eq("anoEstudo", ano));
		criteria.addOrder(Order.asc("aluno"));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaAnoEstudoPeriodo(br.com.muranodesign.model.AnoEstudo, br.com.muranodesign.model.Periodo)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaAnoEstudoPeriodo(AnoEstudo ano, Periodo periodo, int anoLetivo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.add(Restrictions.eq("anoEstudo", ano));
		criteria.add(Restrictions.eq("periodo", periodo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.idanoLetivo", anoLetivo));
		//criteria.add(Restrictions.isNull("grupo"));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaAnoEstudoPeriodoComgrupo(br.com.muranodesign.model.AnoEstudo, br.com.muranodesign.model.Periodo)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaAnoEstudoPeriodoComgrupo(AnoEstudo ano, Periodo periodo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.add(Restrictions.eq("anoEstudo", ano));
		criteria.add(Restrictions.eq("periodo", periodo));
		String anoLetivo = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivoObj = new AnoLetivoService().listarAnoLetivo(anoLetivo).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivoObj));
		criteria.add(Restrictions.eq("ativo", 1));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listaPeriodo(br.com.muranodesign.model.Periodo)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listaPeriodo(Periodo periodo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.add(Restrictions.eq("periodo", periodo));
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#getAluno(int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AlunoVariavel getAluno(int aluno) {
		
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);

		
	    criteria.add(Restrictions.eq("idalunoVariavel", aluno));
		List<AlunoVariavel> result = criteria.list();
		
		if (result.isEmpty()) {
			return null;
		}else {
			return result.get(0);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#listarNamed()
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listarNamed(){
		
		Query q =  getSession().getNamedQuery("AlunoVariavel.findAll");
	    List<AlunoVariavel> list = q.list();
	    return list;
	}
	

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#update(int, int)
	 */
	public void update(int id,  int grupo){
		Query query = getSession().getNamedQuery("AlunoVariavel.UPDATE");

		query.setParameter("id", grupo);
		query.setParameter("idalunoVariavel", id);
		
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#ListarCicloAno(java.util.List, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarCicloAno(List<Integer> i, int primeiro, int ultimo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("anoEstudo", "anoEstudo");
		criteria.add(Restrictions.in("anoEstudo.idanoEstudo", i) );
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#ListarCicloAnoPeriodo(java.util.List, int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarCicloAnoPeriodo(List<Integer> i,int periodo,int primeiro,int ultimo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("anoEstudo", "anoEstudo");
		criteria.add(Restrictions.in("anoEstudo.idanoEstudo", i) );
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", periodo) );
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
		
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.AlunoVariavelDAO#ListarRangePeriodo(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarRangePeriodo(int id, int primeiro,int ultimo){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", id) );
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarCicloAnoPeriodoSemGrupo(
			List<Integer> anos, int idPeriodo, int primeiro, int ultimo) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("anoEstudo", "anoEstudo");
		criteria.add(Restrictions.in("anoEstudo.idanoEstudo", anos) );
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", idPeriodo) );
		criteria.createAlias("grupo", "grupo", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull("grupo"), Restrictions.eq("grupo.status", "1")));
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarCicloAnoSemGrupo(List<Integer> anos,
			int primeiro, int ultimo) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("anoEstudo", "anoEstudo");
		criteria.add(Restrictions.in("anoEstudo.idanoEstudo", anos) );
		criteria.createAlias("grupo", "grupo", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull("grupo"), Restrictions.eq("grupo.status", "1")));
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarRangePeriodoSemGrupo(int idPeriodo,
			int primeiro, int ultimo) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("periodo", "periodo");
		criteria.add(Restrictions.eq("periodo.idperiodo", idPeriodo) );
		criteria.createAlias("grupo", "grupo", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull("grupo"), Restrictions.eq("grupo.status", "1")));
		criteria.createAlias("anoEstudo", "anoEstudo");
		criteria.addOrder(Order.asc("anoEstudo.ano"));
		criteria.createAlias("aluno", "aluno");
		criteria.addOrder(Order.asc("aluno.nome"));
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		List<AlunoVariavel> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> ListarNomeSemGrupo(String nome, int idPeriodo, List<Integer> anos){
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		
		criteria.createAlias("aluno", "aluno");
		if (idPeriodo != 0)
		{
			criteria.createAlias("periodo", "periodo");
			criteria.add(Restrictions.eq("periodo.idperiodo", idPeriodo));
		}
		if (!anos.isEmpty())
		{
			criteria.createAlias("anoEstudo", "anoEstudo");
			criteria.add(Restrictions.in("anoEstudo.idanoEstudo", anos));
		}
		criteria.add(Restrictions.like("aluno.nome", nome, MatchMode.ANYWHERE));
		criteria.createAlias("grupo", "grupo", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull("grupo"), Restrictions.eq("grupo.status", "1")));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AlunoVariavel> listarAlunoAno(int idAluno, int ano) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", Integer.toString(ano)));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		
		List<AlunoVariavel> result = criteria.list();
		
		return result;
	}

	@Override
	public List<AlunoVariavel> listaAlunoTodos(int idAluno) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@Override
	public List<AlunoVariavel> listarNomeAluno(String like) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.like("aluno.nome", like, MatchMode.ANYWHERE));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@Override
	public AlunoVariavel listarAtivosAno(int ano, int i) {
		Query query = getSession().getNamedQuery("listarAnoNovo");
		return (AlunoVariavel)query.list().get(i);
//		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
//		criteria.createAlias("anoLetivo", "anoLetivo");
//		criteria.add(Restrictions.eq("anoLetivo.idanoLetivo", 61));
//		criteria.add(Restrictions.eq("ativo", 1));
//		criteria.addOrder(Order.asc("aluno"));
//		AlunoVariavel result = (AlunoVariavel)criteria.list().get(i);
//		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlunoVariavel> listarRelatorioSecretaria(int tutoria, int ano,
			int periodo, String neces, String prog, String status) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("grupo", "grupo");
		criteria.createAlias("grupo.tutoria", "tutoria");
		criteria.createAlias("aluno", "aluno");
		if (tutoria != 0)
			criteria.add(Restrictions.eq("tutoria.idtutoria", tutoria));
		if (ano != 0){
			criteria.createAlias("anoEstudo", "anoEstudo");
			criteria.add(Restrictions.eq("anoEstudo.idanoEstudo", ano));
		}
		if (periodo != 0){
			criteria.createAlias("periodo", "periodo");
			criteria.add(Restrictions.eq("periodo.idperiodo", periodo));
		}
		if (neces != null && !neces.equals(""))
			criteria.add(Restrictions.eq("aluno.E_NECESSIDADE_ESPECIAL", neces));
		if(prog != null && !prog.equals(""))
			criteria.add(Restrictions.eq("programa_social", prog));
		if(status != null && !status.equals(""))
			criteria.add(Restrictions.eq("aluno.ativo", status));
		
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlunoVariavel> listaTutoria(int id) {
		Criteria criteria = getSession().createCriteria(AlunoVariavel.class);
		criteria.createAlias("grupo", "grupo");
		criteria.createAlias("grupo.tutoria", "tutoria");
		criteria.add(Restrictions.eq("tutoria.idtutoria", id));
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		criteria.add(Restrictions.eq("anoLetivo", anoLetivo));
		criteria.add(Restrictions.eq("ativo", 1));
		criteria.addOrder(Order.asc("grupo"));
		List<AlunoVariavel> result = criteria.list();
		return result;
	}

}