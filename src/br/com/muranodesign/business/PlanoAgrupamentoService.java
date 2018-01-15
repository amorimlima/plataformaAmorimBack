package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.dao.PlanoAgrupamentoDAO;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.PlanejamentoAula;
import br.com.muranodesign.model.PlanoAgrupamento;

public class PlanoAgrupamentoService {

	public PlanoAgrupamento criar(PlanoAgrupamento resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		dao.criar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public PlanoAgrupamento listarkey(int id) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		PlanoAgrupamento result = dao.listarkey(id);
		pc.commitAndClose();
		return result;
	}

	public PlanoAgrupamento deletar(PlanoAgrupamento planoAgrupamento) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		dao.deletar(planoAgrupamento);
		pc.commitAndClose();
		return planoAgrupamento;
	}

	public PlanoAgrupamento atualizar(PlanoAgrupamento resultado) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		dao.atualizar(resultado);
		pc.commitAndClose();
		return resultado;
	}

	public List<PlanoAgrupamento> listarAll() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		List<PlanoAgrupamento> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}

	public List<PlanejamentoAula> listarAgrupamento(int idAgrupamento) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		List<PlanejamentoAula> result = dao.listarAgrupamento(idAgrupamento);
		pc.commitAndClose();
		return result;
	}

	public List<Agrupamento> listarPlano(int idPlano) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		List<Agrupamento> result = dao.listarPlano(idPlano);
		pc.commitAndClose();
		return result;
	}

	public List<PlanoAgrupamento> listarAgrupamentoOficinaProfessor(
			int idProfessor, int idOficina, String[] arrayAgrupamentos) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		PlanoAgrupamentoDAO dao = DAOFactory.getPlanoAgrupamentoDAO(pc);
		List<PlanoAgrupamento> result = dao.listarAgrupamentoOficinaProfessor(idProfessor, idOficina, arrayAgrupamentos);
		pc.commitAndClose();
		return result;
	}

}
