package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Aprovacao;

public interface AprovacaoDAO {

	List<Aprovacao> listAll();

	List<Aprovacao> listarKey(int id);

}
