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

import br.com.muranodesign.model.ForumQuestao;


// TODO: Auto-generated Javadoc
/**
 * The Interface ForumQuestaoDAO.
 */
public interface ForumQuestaoDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<ForumQuestao> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(ForumQuestao p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(ForumQuestao p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(ForumQuestao p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<ForumQuestao> listarKey(int key);
		
		/**
		 * List de forum com top n
		 * @param qtd
		 * @return
		 */
		public List<ForumQuestao> topN(int qtd);
		
		/**
		 * Lista por roteiro
		 * @return
		 */
		public List<ForumQuestao> listaRoteiro(int roteiro);	
		
		/**
		 * Lista por like de nome de roteiro
		 * @param letra
		 * @return
		 */
		public List<ForumQuestao> ListaLikeRoteiro(String letra);
		
		/**
		 * Lista ordenado por data
		 * @return
		 */
		public List<ForumQuestao> listAllOrder();
	
		/**
		 * Lista os foruns dos ultimos 7 dias
		 * @return
		 */
		public List<ForumQuestao> Range(String data2, String data);
		
		/**
		 * Lista por id de usuario
		 * @param idUser
		 * @return
		 */
		public List<ForumQuestao> listaUser(int idUser);

}
