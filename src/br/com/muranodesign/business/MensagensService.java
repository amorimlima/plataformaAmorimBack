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
import br.com.muranodesign.dao.MensagensDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Mensagens;
import br.com.muranodesign.model.Usuario;



// TODO: Auto-generated Javadoc
/**
 * The Class MensagensService.
 */
public class MensagensService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<Mensagens> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por intervalo de ids
	 * @param primeiro
	 * @param ultimo
	 * @return
	 */
	public List<Mensagens> listIntervalo(int primeiro, int ultimo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listIntervalo(primeiro, ultimo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param idProprietario
	 * @param idMensagem
	 * @return
	 */
	public List<Mensagens> listarMensagemByProprietario(int idProprietario, int idMensagem){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarMensagemByProprietario(idProprietario, idMensagem);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Mensagens> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar proprietario
	 * @param proprietario
	 * @return
	 */
	public List<Mensagens> listarProprietario(Usuario proprietario) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarProprietario(proprietario);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar proprietario 
	 * @param proprietario
	 * @param caixa
	 * @param primeiro
	 * @param ultimo
	 * @return
	 */
	public List<Mensagens> listarProprietario(Usuario proprietario,String caixa,int primeiro, int ultimo) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarProprietario(proprietario, caixa, primeiro, ultimo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar proprietario
	 * @param proprietario
	 * @param caixa
	 * @return
	 */
	public List<Mensagens> listarProprietarioUnica(Usuario proprietario,String caixa){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarProprietarioUnica(proprietario, caixa);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar mensagens.
	 *
	 * @param p the p
	 * @return the mensagens
	 */
	public Mensagens criarMensagens(Mensagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar mensagens.
	 *
	 * @param p the p
	 * @return the mensagens
	 */
	public Mensagens deletarMensagens(Mensagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar mensagens.
	 *
	 * @param p the p
	 * @return the mensagens
	 */
	public Mensagens atualizarMensagens(Mensagens p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar Remetente
	 * @param id
	 * @return
	 */
	public List<Mensagens> listarRemetente(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarRemetente(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar proprietario
	 * @param id
	 * @return
	 */
	public List<Mensagens> listarProprietario(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarProprietario(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar proprietario
	 * @param id
	 * @return
	 */
	public List<Mensagens> listarProprietarioCount(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MensagensDAO dao = DAOFactory.getMensagensDAO(pc);
		List<Mensagens> result = dao.listarProprietarioCount(id);
		pc.commitAndClose();
		return result;
	}

	
}
