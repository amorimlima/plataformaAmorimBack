package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.JornadaProfessor;

//Alteração que pode mudar
public interface JornadaProfessorDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<JornadaProfessor> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(JornadaProfessor p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(JornadaProfessor p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(JornadaProfessor p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<JornadaProfessor> listarKey(int key);

	/**
	 * Listar os horarios professor
	 * @param professor
	 * @return
	 */
	public List<JornadaProfessor> ListarTodos(int professor);
	
	/**
	 * Total
	 * @return
	 */
	public long Total(int professor);
	
	/**
	 * Disponivel
	 * @return
	 */
	public long Disponivel(int professor);
	
	/**
	 * extra
	 * @return
	 */
	public long extra(int professor);
}
