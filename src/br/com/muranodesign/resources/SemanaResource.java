package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.SemanaService;
import br.com.muranodesign.model.Semana;

@Path("Semana")
public class SemanaResource {
	
	private Logger logger = Logger.getLogger(SemanaResource.class.getName());
	
	/**
	 * Deletar, alterar e criar Semana
	 * @param action
	 * @param id
	 * @param dia
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("dia") String dia){
		
		Semana resultado = new Semana();
		
		if(action.equals("delete")){
			resultado = new SemanaService().deletarSemana(new SemanaService().listarkey(id).get(0));
			
		}
		else if(action.equals("create")){
			Semana semana = new Semana();
			
			semana.setDia(dia);
			resultado = new SemanaService().criarSemana(semana);
			
		}else if(action.equals("update")){
			Semana semana = new SemanaService().listarkey(id).get(0);
			
			semana.setDia(dia);
			resultado = new SemanaService().atualizarSemana(semana);
		}
		
		return Integer.toString(resultado.getIdsemana());
	}
	
	/**
	 * Listar todos os dias da semana
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Semana> getSemana() {
		logger.debug("Listar Semana ...");
		List<Semana> resultado;
		 resultado = new SemanaService().listarTodos();
		 logger.debug("QTD Semana : " +  resultado.size());
		return resultado;
	}

}
