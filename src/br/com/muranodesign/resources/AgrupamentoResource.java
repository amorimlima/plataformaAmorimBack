package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.CiclosService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.Rotina;


@Path("Agrupamento")
public class AgrupamentoResource {
	
	private Logger logger = Logger.getLogger(AgrupamentoResource.class.getName());
	
	
	/**
	 * Deletar, alterar e criar agrupamentos 
	 * @param action
	 * @param id
	 * @param nome
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("anoLetivo") int anoLetivo,
			//Alteração que pode mudar
			@FormParam("ciclo") int ciclo,
			//Alteração que pode mudar
			@FormParam("nome") String nome){
		
		Agrupamento resultado = new Agrupamento();
		
		if(action.equals("delete")){
			resultado = new AgrupamentoService().deletarAgrupamento(new AgrupamentoService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			Agrupamento agrupamento = new Agrupamento();
			
			agrupamento.setNome(nome);
			agrupamento.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			
			//Alteração que pode mudar
			agrupamento.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			//Alteração que pode mudar
			
			resultado = new AgrupamentoService().criarAgrupamento(agrupamento);
			
		}else if(action.equals("update")){
			Agrupamento agrupamento = new AgrupamentoService().listarkey(id).get(0);
			
			agrupamento.setNome(nome);
			agrupamento.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			
			//Alteração que pode mudar
			agrupamento.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			//Alteração que pode mudar
			
			resultado = new AgrupamentoService().atualizarAgrupamento(agrupamento);
		}
		
		return Integer.toString(resultado.getIdagrupamento());
	}
	
	
	//Alteração que pode mudar
	@Path("ListarPorCiclo/{id}")
	@GET
	@Produces("application/json")
	public List<Agrupamento> getListarPorCiclo(@PathParam("id") int id){
		logger.info("Lista Agrupamento  por id de ciclo" + id);
		List<Agrupamento> resultado;
		resultado = new AgrupamentoService().ListarPorCiclo(id);
		
		return resultado;
	}
	//Alteração que pode mudar
	
	
	/**
	 * Lista agrupamento por id
	 * @param id
	 * @return obj agrupamento
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Agrupamento getAgrupamento(@PathParam("id") int id) {
		logger.info("Lista Agrupamento  por id " + id);
		List<Agrupamento> resultado;
		resultado = new AgrupamentoService().listarkey(id);
		Agrupamento evento = resultado.get(0);

		return evento;
	}
	
	
	@Path("AnoLetivo/{id}")
	@GET
	@Produces("application/json")
	public List<Agrupamento> getAnoLetivo(@PathParam("id") int id){
		return new AgrupamentoService().listaAnoLetivo(id);
	}
	
	/**
	 * Lista todos os agrupamentos
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Agrupamento> getAgrupamento() {
		logger.debug("Listar Agrupamento ...");
		List<Agrupamento> resultado;
		 resultado = new AgrupamentoService().listarTodos();
		 logger.debug("QTD Agrupamento : " +  resultado.size());
		return resultado;
	}
	
	@Path("ListarPorOficina/{idOficina}")
	@GET
	@Produces("application/json")
	public List<Agrupamento> getByOficina(@PathParam("idOficina") int idOficina)
	{
		logger.debug("Listar Agrupamento by Oficina...");
		List<Agrupamento> resultado = new ArrayList<Agrupamento>();
		List<Rotina> rotinas;
		rotinas = new RotinaService().listarPorOficina(idOficina);
		for (Rotina rotina : rotinas) {
			resultado.add(rotina.getAgrupamento());
		}
		logger.debug("QTD Agrupamento : " +  resultado.size());
		return resultado;
	}
}
