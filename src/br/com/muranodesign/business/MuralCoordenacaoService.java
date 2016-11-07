package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.MuralCoordenacaoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.MuralCoordenacao;

public class MuralCoordenacaoService {

	/**
	 * Listar todos
	 * @return
	 */
	public List<MuralCoordenacao> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralCoordenacaoDAO dao = DAOFactory.getMuralCoordenacaoDAO(pc);
		List<MuralCoordenacao> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por id
	 * @param key
	 * @return
	 */
	public List<MuralCoordenacao> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralCoordenacaoDAO dao = DAOFactory.getMuralCoordenacaoDAO(pc);
		List<MuralCoordenacao> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public MuralCoordenacao criarMuralCoordenacao(MuralCoordenacao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralCoordenacaoDAO dao = DAOFactory.getMuralCoordenacaoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public MuralCoordenacao atualizarMuralCoordenacao(MuralCoordenacao p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralCoordenacaoDAO dao = DAOFactory.getMuralCoordenacaoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public MuralCoordenacao deletarMuralCoordenacao(MuralCoordenacao p){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		MuralCoordenacaoDAO dao = DAOFactory.getMuralCoordenacaoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}

}
