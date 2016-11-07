package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Template;

public interface TemplateDAO {
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Template> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(Template p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(Template p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(Template p);
	
	/**
	 * Listar key.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<Template> listarKey(int key);
}
