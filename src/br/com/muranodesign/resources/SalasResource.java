package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.SalasService;
import br.com.muranodesign.model.Salas;


@Path("Salas")
public class SalasResource {
	
	private Logger logger = Logger.getLogger(SemanaResource.class.getName());
	
	/**
	 * Deletar, Alterar e Criar salas
	 * @param action
	 * @param id
	 * @param sala
	 * @return id 
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("sala") String sala){
		
	
		Salas resultado = new Salas();
		
		if(action.equals("delete")){
			resultado = new SalasService().deletarSalas(new SalasService().listarkey(id).get(0));
			
		}
		else if(action.equals("create")){
			Salas salas = new Salas();
			
			salas.setSala(sala);
			resultado = new SalasService().criarSalas(salas);
			
		}else if(action.equals("update")){
			Salas salas = new SalasService().listarkey(id).get(0);
			
			salas.setSala(sala);
			resultado = new SalasService().atualizarSalas(salas);
		}
		
		return Integer.toString(resultado.getIdsalas());
	}
	
	/**
	 * Lista todas as salas
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Salas> getSalas() {
		logger.debug("Listar Salas ...");
		List<Salas> resultado;
		 resultado = new SalasService().listarTodos();
		 logger.debug("QTD OficinaProfessor : " +  resultado.size());
		return resultado;
	}

}
