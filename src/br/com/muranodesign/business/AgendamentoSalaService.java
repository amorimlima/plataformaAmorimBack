package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AgendamentoSalaDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.AgendamentoSala;

public class AgendamentoSalaService {
	
	/**
	 * Lista todos
	 * @return list
	 */
	public List<AgendamentoSala> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		List<AgendamentoSala> result = dao.listAll();
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista por id
	 * @param key
	 * @return list
	 */
	public List<AgendamentoSala> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		List<AgendamentoSala> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Criar agendamento sala
	 * @param p
	 * @return p
	 */
	public AgendamentoSala criarAgendamentoSala(AgendamentoSala p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Atualizar agendamento sala
	 * @param p
	 * @return p
	 */
	public AgendamentoSala atualizarAgendamentoSala(AgendamentoSala p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Deletar agendamento sala
	 * @param p
	 * @return p
	 */
	public AgendamentoSala deletarAgendamentoSala(AgendamentoSala p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	/**
	 * Listar Agendamento Sala
	 * @param dia
	 * @param sala
	 * @param hora
	 * @return
	 */
	public List<AgendamentoSala> listarValidacao(int dia, int sala, long hora){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		List<AgendamentoSala> result = dao.listarValidacao(dia, sala, hora);
		pc.commitAndClose();
		return result;
	}

	public List<AgendamentoSala> listarRotina(int idrotina) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		List<AgendamentoSala> result = dao.listarRotina(idrotina);
		pc.commitAndClose();
		return result;
	}

	public List<AgendamentoSala> listarDiaHora(int idDiaSemana, long hora) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AgendamentoSalaDAO dao = DAOFactory.getAgendamentoSalaDAO(pc);
		List<AgendamentoSala> result = dao.listarDiaHora(idDiaSemana, hora);
		pc.commitAndClose();
		return result;
	}

}
