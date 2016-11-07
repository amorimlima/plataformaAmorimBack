package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.FichaInscricaoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.FichaInscricao;

public class FichaInscricaoService {
	
	/**
	 * Listar todos
	 * @return
	 */
	public List<FichaInscricao> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		FichaInscricaoDAO dao = DAOFactory.getFichaInscricao(pc);
		List<FichaInscricao> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<FichaInscricao> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		FichaInscricaoDAO dao = DAOFactory.getFichaInscricao(pc);
		List<FichaInscricao> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public FichaInscricao criarFichaInscricao(FichaInscricao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		FichaInscricaoDAO dao = DAOFactory.getFichaInscricao(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public FichaInscricao atualizarFichaInscricao(FichaInscricao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		FichaInscricaoDAO dao = DAOFactory.getFichaInscricao(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public FichaInscricao deletarFichaInscricao(FichaInscricao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		FichaInscricaoDAO dao = DAOFactory.getFichaInscricao(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	

}
