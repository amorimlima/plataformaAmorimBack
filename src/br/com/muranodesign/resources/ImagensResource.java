package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.HistoricoEventosService;
import br.com.muranodesign.business.ImagensService;
import br.com.muranodesign.model.Imagens;
import br.com.muranodesign.model.historicoEventos;

/**
 * 
 * @author Kevyn
 *
 */
@Path("Imagens")
public class ImagensResource {
	
	private Logger logger = Logger.getLogger(PlanoEstudoResource.class.getName());
	
	/**
	 * Listar todos
	 */
	@GET
	@Produces("application/json")
	public List<Imagens> getTemplate() {
		logger.info("Listar Imagens ...");
		List<Imagens> resultado;
		resultado = new ImagensService().listarTodos();
		logger.info("QTD Imagens : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Inserir ou alterar
	 * @param action
	 * @param idimagens
	 * @param imagens
	 * @param idhistoricoEventos
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("idimagens") int idimagens,
			@FormParam("imagens") int imagens,
			@FormParam("idhistoricoEventos") int idhistoricoEventos){
		
		Imagens resultado = new Imagens();
		if(action.equals("delete")){
			resultado = new ImagensService().deletarImagens(new ImagensService().listarkey(imagens).get(0));
		}
		else if(action.equals("create")){
			
			
		}else if(action.equals("update")){
			historicoEventos evento = new HistoricoEventosService().listarkey(idhistoricoEventos).get(0);
			
			Imagens img = new ImagensService().listarkey(imagens).get(0);
			img.setIdimagens(idimagens);
			img.setHistorico(evento);
			
			resultado = new ImagensService().atualizarImagens(img);
		}
		
		return Integer.toString(resultado.getIdimagens());
	}

}
