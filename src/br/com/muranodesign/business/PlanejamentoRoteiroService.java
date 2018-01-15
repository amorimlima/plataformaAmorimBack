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
import br.com.muranodesign.dao.PlanejamentoRoteiroDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.PlanejamentoRoteiro;



// TODO: Auto-generated Javadoc
/**
 * The Class PlanejamentoRoteiroService.
 */
public class PlanejamentoRoteiroService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<PlanejamentoRoteiro> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<PlanejamentoRoteiro> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listar por id Aluno
	 *
	 * @param idAluno id do aluno
	 * @return the list
	 */
	public List<PlanejamentoRoteiro> listarIdAluno(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarIdAluno(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar aluno total de objetivos
	 * @param id
	 * @return
	 */
	public /*List<PlanejamentoRoteiro>*/long listarAlunoTotal(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		/*List<PlanejamentoRoteiro>*/long result = dao.listarAlunoTotal(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar alunos objetivos completos
	 * @param id
	 * @return
	 */
	public /*List<PlanejamentoRoteiro>*/ long listarAlunoCompletos(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		/*List<PlanejamentoRoteiro>*/ long result = dao.listarAlunoCompletos(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar Status
	 * @param status
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarStatus(int status){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarStatus(status);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar objetivo pendente
	 * @param obj
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarObjetivoPendente(int obj){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarObjetivoPendente(obj);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar objetivo completo
	 * @param obj
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarObjetivoCompleto(int obj){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarObjetivoCompleto(obj);
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listar planos pendentes por id aluno e id grupo
	 * @param aluno
	 * @param objetivo
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarPendente(int aluno,int objetivo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarPendente(aluno, objetivo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar alunos objetivos corrigidos
	 * @param id
	 * @return
	 */
	public long listarAlunoCorrigidos(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		long result = dao.listarAlunoCorrigidos(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarPlanoEstudo(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarPlanoEstudo(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarObjetivoTotal(int obj){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarObjetivoTotal(obj);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<PlanejamentoRoteiro> listarPlanoEstudoTotal(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarPlanoEstudoTotal(id);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar plano estudo.
	 *
	 * @param p the p
	 * @return the plano estudo
	 */
	public PlanejamentoRoteiro criarPlanejamentoRoteiro(PlanejamentoRoteiro p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar plano estudo.
	 *
	 * @param p the p
	 * @return the plano estudo
	 */
	public PlanejamentoRoteiro deletarPlanejamentoRoteiro(PlanejamentoRoteiro p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar plano estudo.
	 *
	 * @param p the p
	 * @return the plano estudo
	 */
	public PlanejamentoRoteiro atualizarPlanejamentoRoteiro(PlanejamentoRoteiro p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}

	public List<PlanejamentoRoteiro> listarAlunoCompletosLista(int idAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarAlunoCompletosLista(idAluno);
		pc.commitAndClose();
		return result;
	}

	public List<PlanejamentoRoteiro> listarAlunoCorrigidosLista(int idAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarAlunoCorrigidosLista(idAluno);
		pc.commitAndClose();
		return result;
	}

	public List<PlanejamentoRoteiro> listarAlunoAno(int idAluno, int ano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.listarAlunoAno(idAluno, ano);
		pc.commitAndClose();
		return result;
	}

	public List<PlanejamentoRoteiro> countRoteiroCompletos(Integer idroteiro, int idAluno, int ano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.countRoteiroCompletos(idroteiro, idAluno, ano);
		pc.commitAndClose();
		return result;
	}
	
	public List<PlanejamentoRoteiro> countRoteiroCorrigidos(Integer idroteiro, int idAluno, int ano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		List<PlanejamentoRoteiro> result = dao.countRoteiroCorrigidos(idroteiro, idAluno, ano);
		pc.commitAndClose();
		return result;
	}

	public float countCompletosTutoria(int idTutoria) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		float result = dao.countCompletosTutoria(idTutoria);
		pc.commitAndClose();
		return result;
	}

	public float countCorrigidosTutoria(int idTutoria) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanejamentoRoteiroDAO dao = DAOFactory.getPlanejamentoRoteiroDAO(pc);
		float result = dao.countCorrigidosTutoria(idTutoria);
		pc.commitAndClose();
		return result;
	}
	
}
