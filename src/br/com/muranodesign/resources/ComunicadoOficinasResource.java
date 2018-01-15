package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ComunicadoOficinasService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.ComunicadoOficinas;


/**
 * 
 * @author Kevyn
 *
 */
@Path("ComunicadoOficinas")
public class ComunicadoOficinasResource {
	
	private Logger logger = Logger.getLogger(ComunicadoOficinasResource.class.getName());
	
	/**
	 * Gets the materia.
	 *
	 * @return the materia
	 */
	@GET
	@Produces("application/json")
	public List<ComunicadoOficinas> getComunicados() {
		logger.info("Listar Comunicados ...");
		List<ComunicadoOficinas> resultado;
		resultado = new ComunicadoOficinasService().listarTodos();
		logger.info("QTD Comunicados : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Gets the evento.
	 *
	 * @param id the id
	 * @return the evento
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public ComunicadoOficinas getComunicado(@PathParam("id") int id) {
		logger.info("Lista Comunicados  por id " + id);
		List<ComunicadoOficinas> resultado;
		resultado = new ComunicadoOficinasService().listarkey(id);
		ComunicadoOficinas evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Deletar, alterar e criar comunicado oficinas
	 * @param action
	 * @param idComunicado
	 * @param idProfessor
	 * @param comunicado
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("idComunicado") int idComunicado,
			@FormParam("idProfessor") int idProfessor,
			@FormParam("comunicado") String comunicado){
		
		ComunicadoOficinas resultado = new ComunicadoOficinas();
		
		if(action.equals("delete")){
			resultado = new ComunicadoOficinasService().deletarComunicadoOficinas(new ComunicadoOficinasService().listarkey(idComunicado).get(0));
		}
		else if(action.equals("create")){
			ComunicadoOficinas comunicadoOficina = new ComunicadoOficinas();
			
			comunicadoOficina.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			comunicadoOficina.setComunicado(comunicado);
			
			resultado = new ComunicadoOficinasService().criarComunicadoOficinas(comunicadoOficina);
			
		}else if(action.equals("update")){
			ComunicadoOficinas comunicadoOficina = new ComunicadoOficinasService().listarkey(idComunicado).get(0);
			
			comunicadoOficina.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			comunicadoOficina.setComunicado(comunicado);
			
			resultado = new ComunicadoOficinasService().atualizarComunicadoOficinas(comunicadoOficina);
		}
		
		return Integer.toString(resultado.getIdComunicado());
	}

}
