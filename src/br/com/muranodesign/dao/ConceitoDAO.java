package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.Conceito;

public interface ConceitoDAO {

	List<Conceito> listAll();

	List<Conceito> listarKey(int id);

}
