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

import java.util.Date;
import java.util.List;

import br.com.muranodesign.model.RegistroDiario;


// TODO: Auto-generated Javadoc
/**
 * The Interface RegistroDiarioDAO.
 */
public interface RegistroDiarioDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<RegistroDiario> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(RegistroDiario p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(RegistroDiario p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(RegistroDiario p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<RegistroDiario> listarKey(int key);
		
		/**
		 * Listar por id plano estudo
		 * @param id
		 * @return
		 */
		public List<RegistroDiario> listarPlanoEstudo(int id);
		
		/**
		 * Trazer o ultimo roteiro diario
		 * @return
		 */
		public List<RegistroDiario> listarUltimo();
		
		/**
		 * Listar por mes
		 * @param ini
		 * @param fim
		 * @return
		 */
		public List<RegistroDiario> listarMes(Date ini, Date fim);
		
		
		/**
		 * Listar por plano de estudo e data
		 * @return
		 */
		public List<RegistroDiario> listaPlanoEstudoDara(int id, Date data);
	

}
