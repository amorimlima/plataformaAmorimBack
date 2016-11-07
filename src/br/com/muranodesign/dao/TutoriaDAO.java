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

import br.com.muranodesign.model.Tutoria;


// TODO: Auto-generated Javadoc
/**
 * The Interface TutoriaDAO.
 */
public interface TutoriaDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<Tutoria> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(Tutoria p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(Tutoria p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(Tutoria p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<Tutoria> listarKey(int key);
		
		/**
		 * Listar professor nome
		 * @param tutoria
		 * @return
		 */
		public List<Tutoria> listarProfessor(String tutoria);
		
		/**
		 * Listar por professor
		 * @param tutor
		 * @return
		 */
		public List<Tutoria> listarProfessorId(int tutor, String ano);
		
		/**
		 * Listar por professor
		 * @param tutor
		 * @return
		 */
		public List<Tutoria> listarProfessorId(int tutor);
		/**
		 * Listar por ano letivo
		 * @return
		 */
		public List<Tutoria> listarAno(String ano);
	
		/**
		 * Listar por id ano letivo
		 * @return
		 */
		public List<Tutoria> listarAnoid(int id);
		
		/**
		 * Listar por id de periodo
		 * @param id
		 * @return
		 */
		public List<Tutoria> listarPeriodo(int id);
		
		/**
		 * Listar apenas dados pertinentes
		 * @return
		 */
		public List<Tutoria> listarDadosPertinentes();

}
