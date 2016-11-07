package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.HistoricoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.SO;



public class HistoricoService {
	
	/**
	 * Criar
	 * @param p
	 * @return
	 */
	public Historico_conexao criarHistorico(Historico_conexao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar quantidade de acesso na escola
	 * @param data
	 * @return
	 */
	public List<Historico_conexao> listarQtdAcessoEscola(String data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarQtdAcessoEscola(data);
		return result;
	}
	
	/**
	 *  Listar quantidade de acesso fora da escola
	 * @param data
	 * @return
	 */
	public List<Historico_conexao> listarQtdAcessoExterno(String data){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarQtdAcessoExterno(data);
		return result;
	}
	
	/**
	 * Listar SO
	 * @param so
	 * @return
	 */
	public List<Historico_conexao> listarSo(SO so){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		HistoricoDAO dao = DAOFactory.getNHistoricoDAO(pc);
		List<Historico_conexao> result = dao.listarSo(so);
		return result;
	}

}
