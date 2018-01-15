/**
 * 
 */
package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.EmprestimoLivro;

/**
 * @author murano
 *
 */
public interface EmprestimoLivroDAO {

	void update(EmprestimoLivro resultado);

	void criar(EmprestimoLivro resultado);

	List<EmprestimoLivro> listarkey(int id);

	void deletar(EmprestimoLivro resultado);

	List<EmprestimoLivro> listarTodos();

}
