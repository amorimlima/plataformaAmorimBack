/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.business;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.muranodesign.dao.CalendarioDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Calendario;



// TODO: Auto-generated Javadoc
/**
 * The Class CalendarioService.
 */
public class CalendarioService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<Calendario> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<Calendario> listarFiltroData(Date dataInicio, Date dataFim) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listFiltroData(dataInicio, dataFim);
				
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<Calendario> listaFeriado(int ano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listFeriados(ano);
				
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Calendario> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por eventos
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public List<Calendario> listarEvento(int id) throws ParseException{
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listarEvento(id);
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Criar calendario.
	 *
	 * @param p the p
	 * @return the calendario
	 */
	public Calendario criarCalendario(Calendario p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar calendario.
	 *
	 * @param p the p
	 * @return the calendario
	 */
	public Calendario deletarCalendario(Calendario p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar calendario.
	 *
	 * @param p the p
	 * @return the calendario
	 */
	public Calendario atualizarCalendario(Calendario p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}

	/**
	 * Listar todos que forem visiveis
	 * @return list
	 */
	public List<Calendario> listVisivel(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listVisivel();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar range
	 * @return
	 */
	public List<Calendario> listarRange(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CalendarioDAO dao = DAOFactory.getCalendarioDAO(pc);
		List<Calendario> result = dao.listarRange();
				
		pc.commitAndClose();
		return result;
	}
	
}
