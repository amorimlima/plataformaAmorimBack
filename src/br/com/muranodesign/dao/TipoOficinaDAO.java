package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.TipoOficina;

public interface TipoOficinaDAO {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<TipoOficina> listAll();

	public TipoOficina listarKey(int key);
	
}
