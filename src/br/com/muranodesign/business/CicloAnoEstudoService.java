package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.CicloAnoEstudoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.CicloAnoEstudo;

public class CicloAnoEstudoService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<CicloAnoEstudo> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		List<CicloAnoEstudo> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar chave
	 * @param key
	 * @return
	 */
	public List<CicloAnoEstudo> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		List<CicloAnoEstudo> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar ciclo
	 * @param p
	 * @return
	 */
	public CicloAnoEstudo criarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar
	 * @param p
	 * @return
	 */
	public CicloAnoEstudo atualizarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar
	 * @param p
	 * @return
	 */
	public CicloAnoEstudo deletarCicloAnoEstudo(CicloAnoEstudo p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar ciclo
	 * @param idCiclo
	 * @return
	 */
	public List<CicloAnoEstudo> listCiclo(int idCiclo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CicloAnoEstudoDAO dao = DAOFactory.getCicloAnoEstudoDAO(pc);
		List<CicloAnoEstudo> result = dao.listCiclo(idCiclo);
		pc.commitAndClose();
		return result;
	}
}
