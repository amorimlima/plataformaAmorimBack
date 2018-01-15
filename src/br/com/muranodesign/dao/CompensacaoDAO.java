package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Compensacao;

public interface CompensacaoDAO {

	void criar(Compensacao resultado);

	List<Compensacao> listarKey(int id);

	void update(Compensacao resultado);

	void deletar(Compensacao resultado);

	List<Compensacao> listAll();

}
