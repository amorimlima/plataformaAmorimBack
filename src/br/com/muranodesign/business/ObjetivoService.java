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

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.ObjetivoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Objetivo;



// TODO: Auto-generated Javadoc
/**
 * The Class ObjetivoService.
 */
public class ObjetivoService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<Objetivo> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		List<Objetivo> result = dao.listAllTeste();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar todos os testes
	 * @return
	 */
	public List<Objetivo> listAllTeste(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		List<Objetivo> result = dao.listAllTeste();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Objetivo> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		List<Objetivo> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Objetivo> listarRoteiro(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		List<Objetivo> result = dao.listarRoteiro(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public long listarRoteiroTotal(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		long result = dao.listarRoteiroTotal(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public long listarGrafico(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		//List<Objetivo> result = dao.listarGrafico(id);
		long result = dao.listarGrafico(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Objetivo> listarEntregues(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		List<Objetivo> result = dao.listarGraficoEntregues(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar objetivo.
	 *
	 * @param p the p
	 * @return the objetivo
	 */
	public Objetivo criarObjetivo(Objetivo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar objetivo.
	 *
	 * @param p the p
	 * @return the objetivo
	 */
	public Objetivo deletarObjetivo(Objetivo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar objetivo.
	 *
	 * @param p the p
	 * @return the objetivo
	 */
	public Objetivo atualizarObjetivo(Objetivo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}	
}
