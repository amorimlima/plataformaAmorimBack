package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.CiclosService;
import br.com.muranodesign.model.Ciclos;


@Path("Ciclo")
public class CicloResource {

	private Logger logger = Logger.getLogger(CicloResource.class.getName());	
	
	@GET
	@Produces("application/json")
	public List<Ciclos> getOficina() {
		logger.debug("Listar Ciclo ...");
		List<Ciclos> resultado;
		 resultado = new CiclosService().listarTodos();
		 logger.debug("QTD Ciclo : " +  resultado.size());
		return resultado;
	}
	
}
