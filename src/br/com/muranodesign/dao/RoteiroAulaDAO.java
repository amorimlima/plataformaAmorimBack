package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.RoteiroAula;

public interface RoteiroAulaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<RoteiroAula> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(RoteiroAula p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(RoteiroAula p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(RoteiroAula p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<RoteiroAula> listarKey(int key);
	
	/**
	 * Listar like por nome de roteiro
	 * @param letra
	 * @return
	 */
	public List<RoteiroAula> listarLikeRoteiro(String letra);
	
	/**
	 * Listar por id oficina professor
	 * @param id
	 * @return
	 */
	public List<RoteiroAula> listarOficinaProfessor(int id);
	
	/**
	 * Listar por id oficina professor e like roteiro aula
	 * @param id
	 * @param letra
	 * @return
	 */
	public List<RoteiroAula> listarOficinaProfessorLike(int id, String letra);
	
	/**
	 * Listar quando o id for diferente
	 * @param id
	 * @return
	 */
	public List<RoteiroAula> listarNaoOficinaProfessor(int id);
	
	/**
	 * istar quando o id for diferente e like roteiro aula
	 * @param id
	 * @return
	 */
	public List<RoteiroAula> listarNaoOficinaProfessorLike(int id, String letra);
	
	/**
	 * Listar roteiros like
	 * @param idOficinaProfessor
	 * @param letras
	 * @return
	 */
	public List<RoteiroAula> listarLike(int idOficinaProfessor, String letras);
	
}
