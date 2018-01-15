package br.com.muranodesign.dao;

import java.util.Date;
import java.util.List;

import br.com.muranodesign.model.PlanejamentoAula;

public interface PlanejamentoAulaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<PlanejamentoAula> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(PlanejamentoAula p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(PlanejamentoAula p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(PlanejamentoAula p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<PlanejamentoAula> listarKey(int key);
	
	/**
	 * Listar por id de professor
	 * @param id
	 * @return
	 */
	public List<PlanejamentoAula> listarProfessor(int id);

	/**
	 * Listar por id de professor e oficina
	 * @param idProfessor
	 * @param idOficina
	 * @return
	 */
	public List<PlanejamentoAula> listarProfessorOficina(int idProfessor,
			int idOficina);

	public List<PlanejamentoAula> listarIntervalo(Date inicio, Date fim,
			int idProfessor);
	
}
