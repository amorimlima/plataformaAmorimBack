package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.PlanejamentoAula;
import br.com.muranodesign.model.PlanoAgrupamento;

public interface PlanoAgrupamentoDAO {

	List <PlanoAgrupamento> listAll();

	PlanoAgrupamento listarkey(int id);

	void deletar(PlanoAgrupamento planoAgrupamento);

	void atualizar(PlanoAgrupamento resultado);

	void criar(PlanoAgrupamento p);

	List<PlanejamentoAula> listarAgrupamento(int idAgrupamento);

	List<Agrupamento> listarPlano(int idPlano);

	List<PlanoAgrupamento> listarAgrupamentoOficinaProfessor(int idProfessor,
			int idOficina, String[] arrayAgrupamentos);

}
