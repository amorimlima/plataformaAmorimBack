package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.PlanoRecurso;
import br.com.muranodesign.model.ProfessorFuncionario;

/** Interface responsável por conter a declaração de métodos de crud para a tabela plano_recurso
 * @author Pedro Henrique dos Santos
 * @see PlanoRecurso
 */
public interface PlanoRecursoDAO {
	
	public List<PlanoRecurso> listAll();
	
	public void criar(PlanoRecurso p);
	
	public void deletar(PlanoRecurso p);

	public void atualizar(PlanoRecurso p);
	
	public List<PlanoRecurso> listarKey(int key);

	public List<PlanoRecurso> listarRecursoPlanoTotal(int idPlano);

	public List<PlanoRecurso> listarRecursoPlano(int idPlano);

	public List<PlanoRecurso> buscarRecurso(String nome, int tipo,
			ProfessorFuncionario professorFuncionario);
}
