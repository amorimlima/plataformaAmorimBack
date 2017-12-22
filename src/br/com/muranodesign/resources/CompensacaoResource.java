package br.com.muranodesign.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.CompensacaoService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.Compensacao;
import br.com.muranodesign.util.StringUtil;

@Path("Compensacao")
public class CompensacaoResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(CompensacaoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("id") int id,
			@FormParam("descricao") String descricao,
			@FormParam("data") String data,
			@FormParam("tutoria") int tutoria,
			@FormParam("action") String action
			){
		
		Compensacao resultado = new Compensacao();
		
		StringUtil util = new StringUtil();
		Date d = util.converteStringData(data);
		
		if (action.equals("create")){
			resultado.setDescricao(descricao);
			resultado.setData(d);
			resultado.setTutoria(new TutoriaService().listarkey(tutoria).get(0));
			resultado = new CompensacaoService().criar(resultado);
		}
		
		else if (action.equals("update")){
			resultado = new CompensacaoService().listarkey(id).get(0);
			resultado.setDescricao(descricao);
			resultado.setData(d);
			resultado.setTutoria(new TutoriaService().listarkey(tutoria).get(0));
			resultado = new CompensacaoService().update(resultado);
		}
		else if (action.equals("delete")){
			resultado = new CompensacaoService().deletar(new CompensacaoService().listarkey(id).get(0));
		}
		
		return Integer.toString(resultado.getIdCompensacao());
		
	}
	
	@GET
	@Produces("application/json")
	public List<Compensacao> getCompensacao(){
		logger.debug("Listar Compensacao ...");
		List<Compensacao> resultado;
		resultado = new CompensacaoService().listarTodos();
		logger.debug("QTD Compensacao : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<Compensacao> getCompensacao(@PathParam("id") int id){
		logger.debug("Listar Compensacao id: "+id+"...");
		List<Compensacao> resultado;
		resultado = new CompensacaoService().listarkey(id);
		return resultado;
	}
	
	
}
