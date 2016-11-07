package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.CoresService;
import br.com.muranodesign.model.Cores;

@Path("Cores")
public class CoresResource {
	
private Logger logger = Logger.getLogger(CoresResource.class.getName());	
	
	/**
	 * Listar cores
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<Cores> getOficina() {
		logger.debug("Listar Cores ...");
		List<Cores> resultado;
		 resultado = new CoresService().listarTodos();
		 logger.debug("QTD Oficina : " +  resultado.size());
		return resultado;
	}

}
