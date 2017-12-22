package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.JeiffPea;

public interface JeiffPeaDAO {

	List<JeiffPea> listarTodos();

	List<JeiffPea> listarkey(int id);

	void criar(JeiffPea jp);

	void atualizar(JeiffPea jp);

	void deletar(JeiffPea jp);

	List<JeiffPea> listarPeriodo(int idperiodo);

	List<Object> listarAnexos();

}
