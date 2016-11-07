package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.OficinaProfessor;


@Path("OficinaProfessor")
public class OficinaProfessorResource {

	private Logger logger = Logger.getLogger(OficinaProfessorResource.class.getName());
	/**
	 * Deletar, alterar e criar oficina professor
	 * @param action
	 * @param id
	 * @param idOficina
	 * @param idProfessor
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idOficina") int idOficina,
			@FormParam("idProfessor") int idProfessor){
		
		OficinaProfessor resultado = new OficinaProfessor();
		
		if(action.equals("delete")){
			resultado = new OficinaProfessorService().deletarOficinaProfessor(new OficinaProfessorService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			OficinaProfessor oficinaProfessor = new OficinaProfessor();
			
			oficinaProfessor.setOficina(new OficinaService().listarkey(idOficina).get(0));
			oficinaProfessor.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new OficinaProfessorService().criarOficinaProfessor(oficinaProfessor);
			
		}else if(action.equals("update")){
			OficinaProfessor oficinaProfessor = new OficinaProfessorService().listarkey(id).get(0);
			
			oficinaProfessor.setOficina(new OficinaService().listarkey(idOficina).get(0));
			oficinaProfessor.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new OficinaProfessorService().atualizarOficinaProfessor(oficinaProfessor);
		}
		
		return Integer.toString(resultado.getIdoficina_professor());
	}
	
	/**
	 * Listar todas as oficinas professor
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<OficinaProfessor> getOficinaProfessor() {
		logger.debug("Listar OficinaProfessor ...");
		List<OficinaProfessor> resultado;
		 resultado = new OficinaProfessorService().listarTodos();
		 logger.debug("QTD OficinaProfessor : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Listar oficinas de professor por id de professor
	 * @param idProfessor
	 * @return list
	 */
	@Path("listarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<OficinaProfessor> getlistarProfessor(@PathParam("idProfessor") int idProfessor){
		logger.debug("Listar OficinaProfessor ...");
		List<OficinaProfessor> resultado;
		 resultado = new OficinaProfessorService().listarProfessor(idProfessor);
		 logger.debug("QTD OficinaProfessor: " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Listar por id
	 * @param idOficina
	 * @return
	 */
	@Path("listarId/{idOficina}")
	@GET
	@Produces("application/json")
	public List<OficinaProfessor> getListaPorId(@PathParam("idOficina") int idOficina){
		return new OficinaProfessorService().listarkey(idOficina);
	}
	
	/**
	 * Listar por oficina
	 */
	@Path("listarPorOficina/{id}")
	@GET
	@Produces("application/json")
	public List<OficinaProfessor> getlistarPorOficina(@PathParam("id") int id){
		return new OficinaProfessorService().listarPorOficina(id);
	}
	
}
