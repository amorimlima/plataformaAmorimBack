package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.TipoOficinaService;
import br.com.muranodesign.model.TipoOficina;


@Path("TipoOficina")
public class TipoOficinaResource {

	private Logger logger = Logger.getLogger(TipoOficinaResource.class.getName());
	
	/**
	 * Listar todas as oficinas
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<TipoOficina> getOficina() {
		logger.debug("Listar TipoOficina ...");
		List<TipoOficina> resultado;
		 resultado = new TipoOficinaService().listarTodos();
		 logger.debug("QTD TipoOficina : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public TipoOficina getOficina(@PathParam("id") int id){
		return new TipoOficinaService().listarkey(id);
	}
	
}
