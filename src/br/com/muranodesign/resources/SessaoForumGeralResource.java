package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.SessaoForumGeralService;
import br.com.muranodesign.model.SessaoForumGeral;

/**
 *  Classe tem como objetivo disponibilizar os serviços relacionandos a SessaoForumGeral
 * @author Kevyn
 *
 */
@Path("SessaoForumGeral")
public class SessaoForumGeralResource {
	
	/**
	 * logger
	 */
	private Logger logger = Logger.getLogger(SessaoForumGeralResource.class.getName());
	
	/**
	 * Listar todas as sessoes forum geral
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<SessaoForumGeral> getSessao() {
		logger.debug("Listar Alunos ...");
		List<SessaoForumGeral> resultado;
		resultado = new SessaoForumGeralService().listarTodos();
		logger.debug("QTD Sessao : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Lista sessao por id
	 * @param id
	 * @return obj SessaoForumGeral
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public SessaoForumGeral getSessaoForumGeral(@PathParam("id") int id) {

		List<SessaoForumGeral> resultado;
		resultado = new SessaoForumGeralService().listarkey(id);
		SessaoForumGeral sessao = null;

		if (!resultado.isEmpty()) {
			sessao = resultado.get(0);
		}
		return sessao;

	}
	
	/**
	 * Deleta sessao
	 * @param action
	 * @param id
	 * @return String
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeSessao(@PathParam("action") String action, 	@PathParam("id") int id) {
		logger.info("SessaoGeralForum  " + action);
		if ( action.equals("delete")) {
			List<SessaoForumGeral> resultado;
			resultado = new SessaoForumGeralService().listarkey(id);
			SessaoForumGeral res = resultado.get(0);
			new SessaoForumGeralService().deletarSessao(res);
			return "true";
		}else{
			return "false";
		}
	}
	
	
	/**
	 * Criar e alterar sessao
	 * @param action
	 * @param id
	 * @param sessao
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("sessao") String sessao
			){
		
		SessaoForumGeral sessaoForum = new SessaoForumGeral();
		logger.info("eventoAction ...");
		SessaoForumGeral resultado;
		
		if(action.equals("create")){
			sessaoForum.setSessao(sessao);

			resultado = new SessaoForumGeralService().criarSessao(sessaoForum);
			
		}else if(action.equals("update")){
			sessaoForum.setIdSessaoForum(id);
			sessaoForum.setSessao(sessao);
			
			resultado = new SessaoForumGeralService().atualizarSessao(sessaoForum);
		}else {
			return "0";
		}
		
		return Integer.toString(resultado.getIdSessaoForum());
	}
			
}
