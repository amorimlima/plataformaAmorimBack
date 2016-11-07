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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MensagensDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Mensagens;
import br.com.muranodesign.model.Usuario;


/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class MensagensDAOImpl extends AbstractHibernateDAO implements MensagensDAO {

	/**
	 * Instantiates a new mensagens dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public MensagensDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listAll() {
		
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		List<Mensagens> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listIntervalo(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listIntervalo(int primeiro, int ultimo){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		
		List<Mensagens> result = criteria.list();
		
		
		return result;
	}
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#criar(br.com.muranodesign.model.Mensagens)
	 */
	public void criar(Mensagens c) {
		synchronized (MensagensDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#deletar(br.com.muranodesign.model.Mensagens)
	 */
	public void deletar(Mensagens c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#atualizar(br.com.muranodesign.model.Mensagens)
	 */
	public void atualizar(Mensagens p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.add(Restrictions.eq("idmensagens", key));
		List<Mensagens> result = criteria.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarProprietario(br.com.muranodesign.model.Usuario)
	 */
	public List<Mensagens> listarProprietario(Usuario proprietario) {
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		
		criteria.addOrder(Order.desc("data"));
		
		
		criteria.add(Restrictions.eq("proprietario", proprietario));
		
		
		List<Mensagens> result = criteria.list();
		return result;
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarProprietario(br.com.muranodesign.model.Usuario, java.lang.String, int, int)
	 */
	public List<Mensagens> listarProprietario(Usuario proprietario,String caixa,int primeiro, int ultimo) {
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		

		if (caixa.equals("entrada")){ 
			criteria.add(Restrictions.eq("cxEntrada", "S"));
			
		} else if  (caixa.equals("enviado")){
			criteria.add(Restrictions.eq("cxEnviada", "S"));
			
		}
		
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(ultimo);
		
		criteria.add(Restrictions.eq("proprietario", proprietario));
		
		
		criteria.addOrder(Order.desc("data"));
		List<Mensagens> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarProprietarioUnica(br.com.muranodesign.model.Usuario, java.lang.String)
	 */
	public List<Mensagens> listarProprietarioUnica(Usuario proprietario,String caixa){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		
		if (caixa.equals("entrada")){ 
			criteria.add(Restrictions.eq("cxEntrada", "S"));
			
		} else if  (caixa.equals("enviado")){
			criteria.add(Restrictions.eq("cxEnviada", "S"));
			
		}
		
		criteria.add(Restrictions.eq("proprietario", proprietario));
		
		
		criteria.addOrder(Order.desc("data"));
		List<Mensagens> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarMensagemByProprietario(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listarMensagemByProprietario(int idProprietario, int idMensagem){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.createAlias("proprietario", "proprietario");
		criteria.add(Restrictions.eq("proprietario.idusuario", idProprietario));
		criteria.add(Restrictions.eq("idmensagens", idMensagem));
		
		
		List<Mensagens> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarRemetente(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listarRemetente(int id){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.createAlias("remetente", "remetente");
		criteria.add(Restrictions.eq("remetente.idusuario", id));
		
		List<Mensagens> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarProprietario(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listarProprietario(int id){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.createAlias("proprietario", "proprietario");
		criteria.add(Restrictions.eq("proprietario.idusuario", id));
		
		List<Mensagens> result = criteria.list();
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.MensagensDAO#listarProprietarioCount(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Mensagens> listarProprietarioCount(int id){
		Criteria criteria = getSession().createCriteria(Mensagens.class);
		criteria.createAlias("proprietario", "proprietario");
		criteria.add(Restrictions.eq("proprietario.idusuario", id));
		
		criteria.add(Restrictions.eq("lida", "N"));
		criteria.add(Restrictions.eq("cxEntrada", "S"));
		criteria.add(Restrictions.eq("cxEnviada", "N"));

		List<Mensagens> result = criteria.list();
		return result;
	}

}