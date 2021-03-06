/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Mensagens;
import br.com.muranodesign.model.Usuario;


// TODO: Auto-generated Javadoc
/**
 * The Interface MensagensDAO.
 */
public interface MensagensDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<Mensagens> listAll();
		
		/**
		 * Lista por intervalo de ids
		 * @param primeiro
		 * @param ultimo
		 * @return
		 */
		public List<Mensagens> listIntervalo(int primeiro, int ultimo);
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(Mensagens p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(Mensagens p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(Mensagens p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<Mensagens> listarKey(int key);
		
		
		
		/**
		 * Listar proprietario.
		 *
		 * @param proprietario the proprietario
		 * @return the list
		 */
		public List<Mensagens> listarProprietario(Usuario proprietario);
		
		/**
		 * Listar proprietario.
		 *
		 * @param proprietario the proprietario
		 * @return the list
		 */
		public List<Mensagens> listarProprietario(Usuario proprietario,String caixa,int primeiro, int ultimo);
		
		/**
		 * Listar proprietario
		 * @param proprietario
		 * @param caixa
		 * @return
		 */
		public List<Mensagens> listarProprietarioUnica(Usuario proprietario,String caixa);
	
		/**
		 * 
		 * @param id
		 * @return
		 */
		public List<Mensagens> listarRemetente(int id);
		
		/**
		 * 
		 * @param id
		 * @return
		 */
		public List<Mensagens> listarProprietario(int id);
		
		/**
		 * Count de mensagens por proprietario
		 * @param id
		 * @return
		 */
		public List<Mensagens> listarProprietarioCount(int id);
		
		/**
		 * 
		 * @param idProprietario
		 * @param idMensagem
		 * @return
		 */
		public List<Mensagens> listarMensagemByProprietario(int idProprietario, int idMensagem);
}
