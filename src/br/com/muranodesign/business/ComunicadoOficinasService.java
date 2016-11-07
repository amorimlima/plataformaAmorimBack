package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.ComunicadoOficinasDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.ComunicadoOficinas;

public class ComunicadoOficinasService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<ComunicadoOficinas> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ComunicadoOficinasDAO dao = DAOFactory.getComunicadoOficinasDAO(pc);
		List<ComunicadoOficinas> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<ComunicadoOficinas> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ComunicadoOficinasDAO dao = DAOFactory.getComunicadoOficinasDAO(pc);
		List<ComunicadoOficinas> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public ComunicadoOficinas criarComunicadoOficinas(ComunicadoOficinas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ComunicadoOficinasDAO dao = DAOFactory.getComunicadoOficinasDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public ComunicadoOficinas deletarComunicadoOficinas(ComunicadoOficinas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ComunicadoOficinasDAO dao = DAOFactory.getComunicadoOficinasDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public ComunicadoOficinas atualizarComunicadoOficinas(ComunicadoOficinas p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		ComunicadoOficinasDAO dao = DAOFactory.getComunicadoOficinasDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	

}
