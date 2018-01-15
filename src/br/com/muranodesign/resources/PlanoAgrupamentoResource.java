package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.PlanejamentoAulaService;
import br.com.muranodesign.business.PlanoAgrupamentoService;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.PlanejamentoAula;
import br.com.muranodesign.model.PlanoAgrupamento;

@Path("PlanoAgrupamento")
public class PlanoAgrupamentoResource {

	private Logger logger = Logger.getLogger(PlanoAgrupamentoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idPlanejamento") int idPlanejamento,
			@FormParam("idAgrupamento") int idAgrupamento){
		
		PlanoAgrupamento resultado = new PlanoAgrupamento();
		
		if(action.equals("create")){
			resultado.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamento).get(0));
			resultado.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
			resultado = new PlanoAgrupamentoService().criar(resultado);
		}
		else if(action.equals("delete")){
			resultado = new PlanoAgrupamentoService().deletar(new PlanoAgrupamentoService().listarkey(id));
		}
		else if(action.equals("update")){
			resultado = new PlanoAgrupamentoService().listarkey(id);
			resultado.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamento).get(0));
			resultado.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
			resultado = new PlanoAgrupamentoService().atualizar(resultado);
		}
		return Integer.toString(resultado.getIdPlano_agrupamento());
	}
	
	@GET
	@Produces("application/json")
	public List<PlanoAgrupamento> getPlanoAgrupamento(){
		logger.debug("Listar Plano Agrupamento...");
		return new PlanoAgrupamentoService().listarAll();
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public PlanoAgrupamento getById(@PathParam("id") int id){
		return new PlanoAgrupamentoService().listarkey(id);
	}
	
	@Path("ListarAgrupamento/{idAgrupamento}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getAgrupamento(@PathParam("idAgrupamento") int idAgrupamento){
		logger.debug("Listar Plano Aula por Agrupamento...");
		List<PlanejamentoAula> resultado = new PlanoAgrupamentoService().listarAgrupamento(idAgrupamento);
		logger.debug("Qt Planos Agrupamento "+idAgrupamento+": " + resultado.size());
		return resultado;
	}
	
	@Path("ListarPlano/{idPlano}")
	@GET
	@Produces("application/json")
	public List<Agrupamento> getPlano(@PathParam("idPlano") int idPlano){
		logger.debug("Listar Agrupamento por Plano Aula...");
		List<Agrupamento> resultado = new PlanoAgrupamentoService().listarPlano(idPlano);
		logger.debug("Qt Agrupamento Plano Aula "+idPlano+": " + resultado.size());
		return resultado;
	}
	
	@Path("ListarAgrupamentoOficinaProfessor/{idProfessor}/{idOficina}/{idAgrupamentos}")
	@GET
	@Produces("application/json")
	public List<PlanoAgrupamento> getAgrupamentoOficinaProfessor(
			@PathParam("idProfessor") int idProfessor,
			@PathParam("idOficina") int idOficina,
			@PathParam("idAgrupamentos") String agrupamentos){
		logger.debug("Listar Agrupamento por Plano Aula...");
		String [] arrayAgrupamentos = agrupamentos.split(";");
		List<PlanoAgrupamento> resultado = new PlanoAgrupamentoService().listarAgrupamentoOficinaProfessor(idProfessor, idOficina, arrayAgrupamentos);
		return resultado;
	}
	
	@Path("NovoPlanejamento/")
	@POST
	@Produces("text/plain")
	public String planejamentoAgrupmentos(@FormParam("idPlanejamento") int idPlanejamento, @FormParam("idAgrupamentos") String idAgrupamentos){
		
		String r = "";
		
		String [] arrayAgrupamentos = idAgrupamentos.split(";");
		for (String agrupamento : arrayAgrupamentos) {
			PlanoAgrupamento resultado = new PlanoAgrupamento();
			resultado.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamento).get(0));
			resultado.setAgrupamento(new AgrupamentoService().listarkey(Integer.parseInt(agrupamento)).get(0));
			resultado = new PlanoAgrupamentoService().criar(resultado);
			r += resultado.getIdPlano_agrupamento() + "; ";
		}
		
		return r;
	}
}
