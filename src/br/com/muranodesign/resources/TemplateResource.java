package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.TemplateService;
import br.com.muranodesign.model.Template;

/**
 * 
 * @author Kevyn
 *
 */
@Path("Template")
public class TemplateResource {
	
	private Logger logger = Logger.getLogger(TemplateResource.class.getName());
	
	/**
	 * Deletar, alterar e criar Template
	 * @param action
	 * @param idTemplate
	 * @param template
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(

		@FormParam("action") String action,
		@FormParam("idTemplate") int idTemplate,
		@FormParam("template") String template		

	) {
		
		Template resultado = new Template();
		
		if(action.equals("delete")){
			Template temp = new TemplateService().listarkey(idTemplate).get(0);
			resultado = new TemplateService().deletarTemplate(temp);
		}
		else if(action.equals("create")){
			Template temp = new Template();
			temp.setTemplate(template);
			
			resultado = new TemplateService().criarTemplate(temp);
			
		}else if(action.equals("update")){
			Template temp = new TemplateService().listarkey(idTemplate).get(0);
			temp.setTemplate(template);
			
			resultado = new TemplateService().atualizarTemplate(temp);
			
		}
		
		return Integer.toString(resultado.getIdtemplate());
	}
	
	/**
	 * Lista todos os templates
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Template> getTemplate() {
		logger.info("Listar Template ...");
		List<Template> resultado;
		resultado = new TemplateService().listarTodos();
		logger.info("QTD Template : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Lista template por id
	 * @param id
	 * @return obj template
	 */
	@Path("TemplateId/{id}")
	@GET
	@Produces("application/json")	
	public Template getTemplatebyId(@PathParam("id") int id){
		logger.info("Listar Template por id..."+id);
		Template resultado;
		resultado = new TemplateService().listarkey(id).get(0);
		logger.info("QTD Template : " + 1);
		return resultado;
	}
	
}
