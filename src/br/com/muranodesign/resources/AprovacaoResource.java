package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AprovacaoService;
import br.com.muranodesign.model.Aprovacao;

public class AprovacaoResource {

	private Logger logger = Logger.getLogger(AprovacaoResource.class.getName());
	
	@GET
	@Produces("application/json")
	public List<Aprovacao> getAprovacao(){
		logger.debug("Listar Aprovacao ...");
		List<Aprovacao> resultado;
		resultado = new AprovacaoService().listarTodos();
		logger.debug("QTD Aprovacao : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<Aprovacao> getAprovacao(@PathParam("id") int id){
		logger.debug("Listar Aprovacao id: "+id+"...");
		List<Aprovacao> resultado;
		resultado = new AprovacaoService().listarKey(id);
		return resultado;
	}
	
}
