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

import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.Periodo;


// TODO: Auto-generated Javadoc
/**
 * The Interface AlunoVariavelDAO.
 */
public interface AlunoVariavelDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<AlunoVariavel> listAll();
		
		
		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<AlunoVariavel> listAll(int status);
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(AlunoVariavel p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(AlunoVariavel p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(AlunoVariavel p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<AlunoVariavel> listarKey(int key);
		
		
		/**
		 * Gets the aluno.
		 *
		 * @param aluno the aluno
		 * @return the aluno
		 */
		public AlunoVariavel getAluno(int aluno);
		
		/**
		 * Listar alunos
		 * @param idAluno
		 * @return
		 */
		public List<AlunoVariavel> listaAluno(int idAluno);
		
		/**
		 * Listar grupo
		 * @param idGrupo
		 * @return
		 */
		public List<AlunoVariavel> listaGrupo(int idGrupo);
		
		/**
		 * Listar Ano Estudo
		 * @param ano
		 * @return
		 */
		public List<AlunoVariavel> listaAnoEstudo(AnoEstudo ano);
		
		
		/**
		 * Listar por ano e periodo
		 * @param ano
		 * @param periodo
		 * @return
		 */
		public List<AlunoVariavel> listaAnoEstudoPeriodo(AnoEstudo ano, Periodo periodo);
		
		/**
		 * Listar por ano e periodo e que tenha grupo
		 * @param ano
		 * @param periodo
		 * @return
		 */
		public List<AlunoVariavel> listaAnoEstudoPeriodoComgrupo(AnoEstudo ano, Periodo periodo);
		
		/**
		 * Listar por periodo
		 * @param periodo
		 * @return
		 */
		public List<AlunoVariavel> listaPeriodo(Periodo periodo);
		
		/**
		 * Lista por named query
		 * @return
		 */
		public List<AlunoVariavel> listarNamed();
		
		/**
		 * 
		 * @param id
		 * @param grupo
		 */
		public void update(int id, int grupo);
		
		/**
		 * 
		 * @param i
		 * @param primeiro
		 * @param ultimo
		 * @return
		 */
		public List<AlunoVariavel> ListarCicloAno(List<Integer> i,int primeiro,int ultimo);
		
		/**
		 * 
		 * @param i
		 * @param periodo
		 * @param primeiro
		 * @param ultimo
		 * @return
		 */
		public List<AlunoVariavel> ListarCicloAnoPeriodo(List<Integer> i,int periodo,int primeiro,int ultimo);
		
		/**
		 * 
		 * @param id
		 * @param primeiro
		 * @param ultimo
		 * @return
		 */
		public List<AlunoVariavel> ListarRangePeriodo(int id, int primeiro,int ultimo);
		
		
}
