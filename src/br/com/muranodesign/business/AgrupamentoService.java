package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AgrupamentoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Agrupamento;

public class AgrupamentoService {
	
	/**
	 * Lista todos
	 * @return list
	 */
	public List<Agrupamento> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		List<Agrupamento> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista por id
	 * @param key
	 * @return list
	 */
	public List<Agrupamento> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		List<Agrupamento> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Cria
	 * @param p
	 * @return p
	 */
	public Agrupamento criarAgrupamento(Agrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualiza
	 * @param p
	 * @return p
	 */
	public Agrupamento atualizarAgrupamento(Agrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deleta
	 * @param p
	 * @return p
	 */
	public Agrupamento deletarAgrupamento(Agrupamento p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar ano letivo
	 * @param idAno
	 * @return
	 */
	public List<Agrupamento> listaAnoLetivo(int idAno){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		List<Agrupamento> result = dao.listaAnoLetivo(idAno);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por ciclo
	 * @param id
	 * @return
	 */
	//Alteração que pode mudar
	public List<Agrupamento> ListarPorCiclo(int id){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgrupamentoDAO dao = DAOFactory.getAgrupamentoDAO(pc);
		List<Agrupamento> result = dao.ListarPorCiclo(id);
		pc.commitAndClose();
		return result;
	}
	//Alteração que pode mudar

}
