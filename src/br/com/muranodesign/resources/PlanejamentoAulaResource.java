package br.com.muranodesign.resources;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.PlanejamentoAulaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.PlanejamentoAula;
import br.com.muranodesign.util.StringUtil;

/**
 * 
 * @author Kevyn
 *
 */
@Path("PlanejamentoAula")
public class PlanejamentoAulaResource {
	private Logger logger = Logger.getLogger(PlanejamentoAulaResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("oficina") int oficina,
			@FormParam("data_inicio") String data_inicio,
			@FormParam("data_fim") String data_fim,
			@FormParam("objetivo") String objetivo,
			@FormParam("metodologia") String metodologia,
			@FormParam("conteudo") String conteudo,
			@FormParam("atividades") String atividades,
			@FormParam("idProfessor") int idProfessor) throws ParseException{
		
		StringUtil stringUtil = new StringUtil();
		
		PlanejamentoAula resultado = new PlanejamentoAula();
		
		if(action.equals("delete")){
			resultado = new PlanejamentoAulaService().deletarPlanejamentoAula(new PlanejamentoAulaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			
			resultado.setOficina(new OficinaService().listarkey(oficina).get(0));
			resultado.setData_inicio(stringUtil.converteStringData(data_inicio));
			resultado.setData_fim(stringUtil.converteStringData(data_fim));
			resultado.setObjetivo(objetivo);
			resultado.setMetodologia(metodologia);
			resultado.setConteudo(conteudo);
			resultado.setAtividades(atividades);
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new PlanejamentoAulaService().criarPlanejamentoAula(resultado);
			
		}else if(action.equals("update")){
			
			resultado = new  PlanejamentoAulaService().listarkey(id).get(0);
			
			resultado.setOficina(new OficinaService().listarkey(oficina).get(0));
			resultado.setData_inicio(stringUtil.converteStringData(data_inicio));
			resultado.setData_fim(stringUtil.converteStringData(data_fim));
			resultado.setObjetivo(objetivo);
			resultado.setMetodologia(metodologia);
			resultado.setConteudo(conteudo);
			resultado.setAtividades(atividades);
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new PlanejamentoAulaService().atualizarPlanejamentoAula(resultado);
		}
		
		return Integer.toString(resultado.getIdplanejamento_aula());
	}
	
	@Path("listarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getlistarProfessor(@PathParam("idProfessor") int idProfessor){
		logger.debug("Listar PlanejamentoAula Professor: "+idProfessor+" ...");
		List<PlanejamentoAula> resultado;
		 resultado = new PlanejamentoAulaService().listarProfessor(idProfessor);
		 logger.debug("QTD PlanejamentoAula: " +  resultado.size());
		return resultado;
	}
	
	@Path("listarProfessorOficina/{idProfessor}/{idOficina}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getlistarProfessorOficina(@PathParam("idProfessor") int idProfessor, @PathParam("idOficina") int idOficina){
		logger.debug("Listar PlanejamentoAula Professor: "+idProfessor+" Oficina: "+idOficina+" ...");
		List<PlanejamentoAula> resultado;
		resultado = new PlanejamentoAulaService().listarProfessorOficina(idProfessor, idOficina);
		logger.debug("QTD PlanejamentoAula: " +  resultado.size());
		return resultado;
	}
	
	@Path("ListarData/{dataInicio}/{dataFim}/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getDataIntervalo(@PathParam("dataInicio") String dataInicio, @PathParam("dataFim") String dataFim, @PathParam("idProfessor") int idProfessor) throws ParseException{
		logger.debug("Listar PlanejamentoAula inicio: "+dataInicio+" fim: "+dataFim);
		List<PlanejamentoAula> resultado;
		StringUtil stringUtil = new StringUtil();
		resultado = new PlanejamentoAulaService().listarIntervalo(stringUtil.converteStringData(dataInicio), stringUtil.converteStringData(dataFim), idProfessor);
		logger.debug("QTD PlanejamentoAula: " +  resultado.size());
		return resultado;
	}
	
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getPlanejamentoAula() {
		logger.debug("Listar PlanejamentoAula ...");
		List<PlanejamentoAula> resultado;
		 resultado = new PlanejamentoAulaService().listarTodos();
		 logger.debug("QTD OficinaProfessor : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getPlanejamentoAula(@PathParam("id") int id) {
		logger.debug("Listar PlanejamentoAula ...");
		List<PlanejamentoAula> resultado;
		 resultado = new PlanejamentoAulaService().listarkey(id);
		 logger.debug("QTD OficinaProfessor : " +  resultado.size());
		return resultado;
	}
	
	
}
