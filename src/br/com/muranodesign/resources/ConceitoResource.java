package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ConceitoService;
import br.com.muranodesign.model.Conceito;


@Path("Conceito")
public class ConceitoResource {
	
	private Logger logger = Logger.getLogger(ConceitoResource.class.getName());
	
	@GET
	@Produces("application/json")
	public List<Conceito> getConceito(){
		logger.debug("Listar Conceito ...");
		List<Conceito> resultado;
		resultado = new ConceitoService().listarTodos();
		logger.debug("QTD Conceito : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<Conceito> getConceito(@PathParam("id") int id){
		logger.debug("Listar Conceito id: "+id+"...");
		List<Conceito> resultado;
		resultado = new ConceitoService().listarKey(id);
		return resultado;
	}

}
