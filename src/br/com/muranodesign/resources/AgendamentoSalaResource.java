package br.com.muranodesign.resources;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgendamentoSalaService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.business.SalasService;
import br.com.muranodesign.business.SemanaService;
import br.com.muranodesign.model.AgendamentoSala;
import br.com.muranodesign.model.Rotina;

/**
 * TESTE
 * @author kevyn
 *
 */
@Path("AgendamentoSala")
public class AgendamentoSalaResource {
	
	private Logger logger = Logger.getLogger(AgendamentoSalaResource.class.getName());
	
	/**
	 * Deletar, alterar e criar Agendamento Sala
	 * @param action
	 * @param id
	 * @param outra
	 * @param hora
	 * @param data
	 * @param idsala
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("outra") String outra,
			@FormParam("Hora") long Hora,
			@FormParam("dia") int iddia,
			@FormParam("idrotina") int idrotina, 
			@FormParam("idsala") int idsala){
		
		
		GregorianCalendar gc = new GregorianCalendar(); 
		long hora =  gc.get(Calendar.HOUR_OF_DAY);
		
		//hora = hora + gc.get(Calendar.MINUTE);
	
		AgendamentoSala resultado = new AgendamentoSala();
		
		if(action.equals("delete")){
			resultado = new AgendamentoSalaService().deletarAgendamentoSala(new AgendamentoSalaService().listarkey(id).get(0));
			
		}
		else if(action.equals("create")){
			AgendamentoSala agendamento = new AgendamentoSala();
			
			if(idrotina != 0){
				
				Rotina rotina = new RotinaService().listarkey(idrotina).get(0);
				
				List<AgendamentoSala> validacao = new AgendamentoSalaService().listarValidacao(rotina.getDia().getIdsemana(), idsala, rotina.getHora());
				
				if(!validacao.isEmpty()){
					return "ERRO: Horario indisponivel";
				}
				
				
				agendamento.setRotina(new RotinaService().listarkey(idrotina).get(0));
				agendamento.setSala(new SalasService().listarkey(idsala).get(0));
				
				resultado = new AgendamentoSalaService().criarAgendamentoSala(agendamento);
			}else{
			
				List<AgendamentoSala> validacao = new AgendamentoSalaService().listarValidacao(iddia, idsala, hora);
				
				if(!validacao.isEmpty()){
					return "ERRO: Horario indisponivel";
				}
				
				agendamento.setDia(new SemanaService().listarkey(iddia).get(0));
				agendamento.setHora(hora);
				agendamento.setOutra(outra);
				
				agendamento.setSala(new SalasService().listarkey(idsala).get(0));
				
				resultado = new AgendamentoSalaService().criarAgendamentoSala(agendamento);
			}
			
		}else if(action.equals("update")){
			AgendamentoSala agendamento = new AgendamentoSalaService().listarkey(id).get(0);
			
			if(idrotina != 0){
				
				Rotina rotina = new RotinaService().listarkey(idrotina).get(0);
				
				List<AgendamentoSala> validacao = new AgendamentoSalaService().listarValidacao(rotina.getDia().getIdsemana(), idsala, rotina.getHora());
				
				if(!validacao.isEmpty()){
					return "ERRO: Horario indisponivel";
				}
				
				
				agendamento.setRotina(new RotinaService().listarkey(idrotina).get(0));
				agendamento.setSala(new SalasService().listarkey(idsala).get(0));
				
				resultado = new AgendamentoSalaService().atualizarAgendamentoSala(agendamento);
			}else{
			
				List<AgendamentoSala> validacao = new AgendamentoSalaService().listarValidacao(iddia, idsala, Hora);
				
				if(!validacao.isEmpty()){
					return "ERRO: Horario indisponivel";
				}
				
				agendamento.setDia(new SemanaService().listarkey(iddia).get(0));
				agendamento.setHora(Hora);
				agendamento.setOutra(outra);
				
				agendamento.setSala(new SalasService().listarkey(idsala).get(0));
				
				resultado = new AgendamentoSalaService().atualizarAgendamentoSala(agendamento);
			}
		}
		
		return Integer.toString(resultado.getIdagendamento_sala());
	}
	
	/**
	 * Lista agendamentos de sala por dia, sala e hora
	 * @param dia
	 * @param sala
	 * @param Hora
	 * @return list
	 */
	@Path("ListarValidacao/{dia}/{sala}/{Hora}")
	@GET
	@Produces("application/json")
	public List<AgendamentoSala> getValidacao(@PathParam("dia") int dia, @PathParam("sala") int sala, @PathParam("Hora") long Hora){
		logger.debug("Listar AgendamentoSala ...");
		List<AgendamentoSala> resultado;
		 resultado = new AgendamentoSalaService().listarValidacao(dia, sala, Hora);
		 logger.debug("QTD AgendamentoSala : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Lista todos os agendamentos de salas
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<AgendamentoSala> getAgendamentoSala() {
		logger.debug("Listar AgendamentoSala ...");
		List<AgendamentoSala> resultado;
		 resultado = new AgendamentoSalaService().listarTodos();
		 logger.debug("QTD AgendamentoSala : " +  resultado.size());
		return resultado;
	}

}
