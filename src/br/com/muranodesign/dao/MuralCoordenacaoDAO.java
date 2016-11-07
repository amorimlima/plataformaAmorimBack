package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.MuralCoordenacao;

public interface MuralCoordenacaoDAO {

	List<MuralCoordenacao> listAll();

	List<MuralCoordenacao> listarKey(int key);

	void criar(MuralCoordenacao p);

	void atualizar(MuralCoordenacao p);

	void deletar(MuralCoordenacao p);

}
