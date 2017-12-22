package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.Rotina;

public interface RotinaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Rotina> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Rotina p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Rotina p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Rotina p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Rotina> listarKey(int key);
	
	/**
	 * 
	 */
	public List<Rotina> listarInconsistencia(long hora, int idSemana/*,int idSala*/);
	
	/**
	 * Listar por oficina
	 * @param idOficina
	 * @return
	 */
	public List<Rotina> listarPorOficina(int idOficina);
	
	/**
	 * Listar por id de agrupamento
	 * @param idAgrupamento
	 * @return
	 */
	public List<Oficina> listarPorAgrupamento(int idAgrupamento);

	public List<Rotina> listarRotinaAlunoDia(int idagrupamento, int iddiaSemana);

	public List<Rotina> listarRotinaOficinaDia(int idoficina, int idDiaSemana);

	public List<Rotina> listarRotinaTutoriaDia(Integer idtutoria,
			int idDiaSemana);

	public List<Agrupamento> listarAgrupamentoPorOficina(int idOficina);
}
