package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.SO;
public interface HistoricoDAO {
	
public List<Historico_conexao> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Historico_conexao p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Historico_conexao p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Historico_conexao p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Historico_conexao> listarKey(int key);
	
	
	/**
	 * Listar quantidade de acessos escolas
	 * @param data
	 * @return
	 */
	public List<Historico_conexao> listarQtdAcessoEscola(String data);

	
	/**
	 * Listar quantidade de acessos externos
	 * @param data
	 * @return
	 */
	public List<Historico_conexao> listarQtdAcessoExterno(String data);
	
	/**
	 * Listar historico por so
	 * @param so
	 * @return list
	 */
	public List<Historico_conexao> listarSo(SO so);

}
