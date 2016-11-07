package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Roteiro;

public interface  AtribuicaoRoteiroExtraDAO {
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<AtribuicaoRoteiroExtra> listAll();
	
	/**
	 * Criar.
	 *
	 * @param p the p
	 */
	public void criar(AtribuicaoRoteiroExtra p);
	
	/**
	 * Deletar.
	 *
	 * @param p the p
	 */
	public void deletar(AtribuicaoRoteiroExtra p);
	
	/**
	 * Atualizar.
	 *
	 * @param p the p
	 */
	public void atualizar(AtribuicaoRoteiroExtra p);
	
	/**
	 * Listar aluno por roteiro.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<AtribuicaoRoteiroExtra> listarKey(Aluno aluno, Roteiro roteiro);
	
	
	/**
	 * Listar id
	 * @param id
	 * @return
	 */
	public List<AtribuicaoRoteiroExtra> listarid(int id);
	
	
	/**
	 * Listar os roteiros referentes ao aluno e ano letivo 
	 * @param aluno
	 * @param ano
	 * @return
	 */
	public List<AtribuicaoRoteiroExtra> listarRoteiro(Aluno aluno, AnoLetivo ano);
	
	/**
	 * Listar as atribuicoes de aluno
	 * @param aluno
	 * @return
	 */
	public List<AtribuicaoRoteiroExtra> listarAluno(Aluno aluno);

}
