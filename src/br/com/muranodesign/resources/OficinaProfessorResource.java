package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgendamentoSalaService;
import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.model.AgendamentoSala;
import br.com.muranodesign.model.OficinaProfessor;
import br.com.muranodesign.model.Rotina;

/**
 * 
 * @author Kevyn
 *
 */
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
	
	@Path("ListarOficineiros")
	@GET
	@Produces("application/json")
	public List<ProfessorFuncionario> getOficineiros(){
		return new OficinaProfessorService().listarOficineiros();
	}
	
	@Path("ListarOficinaProfessorRotina/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<Object> getOficinaProfessorRotina(@PathParam("idProfessor") int idProfessor)
	{
			List<Object> resultado = new ArrayList<Object>();
			
			List<OficinaProfessor> oficinasProfessor = new OficinaProfessorService().listarProfessor(idProfessor);
			for (OficinaProfessor oficinaProfessor : oficinasProfessor) {
				int idOficina = oficinaProfessor.getOficina().getIdoficina();
				List<Rotina> rotinaOficina = new RotinaService().listarPorOficina(idOficina);
				if (!rotinaOficina.isEmpty())
				{
					Rotina rotinaOficinaProfessor = rotinaOficina.get(0);
					List<ProfessorFuncionario> professores = new OficinaProfessorService().listarProfessoresPorOficina(idOficina);
					List<AgendamentoSala> agendamento = new AgendamentoSalaService().listarRotina(rotinaOficinaProfessor.getIdrotina());
					
					Hashtable <String, Object> rotinaObj = new Hashtable<String, Object>();

					if (!agendamento.isEmpty())
					{
						rotinaObj.put("sala", agendamento.get(0));
						rotinaObj.put("rotina", rotinaOficinaProfessor);
					}
					else
					{
						rotinaObj.put("sala", "");
						rotinaObj.put("rotina", rotinaOficinaProfessor);
					}
					rotinaObj.put("professores", professores);
					
					resultado.add(rotinaObj);
				}
				
			}
			
			return resultado;
	}
	
}
