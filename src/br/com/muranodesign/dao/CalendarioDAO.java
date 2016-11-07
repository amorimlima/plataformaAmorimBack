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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.muranodesign.model.Calendario;


// TODO: Auto-generated Javadoc
/**
 * The Interface CalendarioDAO.
 */
public interface CalendarioDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<Calendario> listAll();
		
		/**
		 * Listar todos que forem visiveis
		 * @return list
		 */
		public List<Calendario> listVisivel();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(Calendario p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(Calendario p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(Calendario p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<Calendario> listarKey(int key);
		
		/**
		 * Listar por filtro data
		 * @param dataInicio
		 * @param dataFim
		 * @return
		 */
		public List<Calendario> listFiltroData(Date dataInicio, Date dataFim) ;
		
		/**
		 * Listar os feriados por ano 
		 * @param ano
		 * @return
		 */
		public List<Calendario> listFeriados(int ano);
		
		/**
		 * Listar por eventos
		 * @param id
		 * @return
		 * @throws ParseException
		 */
		public List<Calendario> listarEvento(int id) throws ParseException;
		
		/**
		 * Listar por range
		 * @return
		 */
		public List<Calendario> listarRange();

}
