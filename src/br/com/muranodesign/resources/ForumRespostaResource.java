/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ForumQuestaoService;
import br.com.muranodesign.business.ForumRespostaService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.ForumQuestao;
import br.com.muranodesign.model.ForumResposta;
import br.com.muranodesign.model.Usuario;


/**
 * Classe tem como objetivo disponibilizar os serviços criação respota do forum
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("ForumResposta")
public class ForumRespostaResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ForumRespostaResource.class.getName());

	/**
	 * Gets the forum resposta.
	 *
	 * @return the forum resposta
	 */
	@GET
	@Produces("application/json")
	public List<ForumResposta> getForumResposta() {
		logger.info("Listar ForumResposta ...");
		List<ForumResposta> resultado;
		resultado = new ForumRespostaService().listarTodos();
		logger.info("QTD ForumResposta : " + resultado.size());
		return resultado;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("DeletarForumRespQuest/{id}")
	@GET
	@Produces("text/plain")
	public String DeletarForum(@PathParam("id") int id){
		List<ForumResposta> resp = new ForumRespostaService().ListarTotal(id);
		
		
		for (ForumResposta forumResposta : resp) {
			new ForumRespostaService().deletarForumResposta(forumResposta);
		}
		
		 new ForumQuestaoService().deletarForumQuestao(new ForumQuestaoService().listarkey(id).get(0));
		
		return "ok";
	}
	
	
	@Path("DeletarForumResp/{id}")
	@GET
	@Produces("text/plain")
	public String DeletarForumResp(@PathParam("id") int id){
		new ForumRespostaService().deletarForumResposta(new ForumRespostaService().listarkey(id).get(0));
		
		return "ok";
	}
	

	/**
	 * Gets the evento.
	 *
	 * @param id the id
	 * @return the evento
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public ForumResposta getEvento(@PathParam("id") int id) {
		logger.info("Lista ForumResposta  por id " + id);
		List<ForumResposta> resultado;
		resultado = new ForumRespostaService().listarkey(id);
		ForumResposta evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Retorna o total de forum resposta e a quantidade por professor
	 * @param id
	 * @return Hashtable<String, Long>
	 */
	@Path("TotalParcial/{id}")
	@GET
	@Produces("application/json")
	public  Hashtable<String, Long> totalParcial(@PathParam("id") int id){
		Hashtable<String, Long> retorno = new Hashtable<String, Long>();
		
		List<ForumResposta> reposta = new ArrayList<ForumResposta>();
		
		reposta = new ForumRespostaService().ListarTotal(id);
		long count = 0;
		
		Usuario user = new Usuario();
		for (ForumResposta forumResposta : reposta) {
			 user = new UsuarioService().listarkey(forumResposta.getUsuario().getIdusuario()).get(0);
			 if(user.getPerfil().getIdperfil() == 24){
				 count++;
			 }
		}
		
		retorno.put("Total", new ForumRespostaService().Total(id));
		retorno.put("Professor",count );
		return retorno;
	}
	
	
	@Path("ListarPorQuestao/{id}")
	@GET
	@Produces("application/json")
	public List<ForumResposta> qtd(@PathParam("id") int id){
		return new ForumRespostaService().ListarTotal(id);
	}
	
	/**
	 * Removes the forum resposta.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeForumResposta(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("ForumResposta  " + action);
		if ( action.equals("delete")) {
			List<ForumResposta> resultado;
			resultado = new ForumRespostaService().listarkey(id);
			ForumResposta res = resultado.get(0);
			new ForumRespostaService().deletarForumResposta(res);
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Criar e alterar forum questao
	 * @param action
	 * @param strid
	 * @param resposta
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @param usuario
	 * @param forumQuestao
	 * @return  id
	 */
	@POST
	//@Path("upload")
	public String eventoAction(

			@FormParam("action") String action,
			@FormParam("id") String strid,

			@FormParam("resposta") String resposta,
			@FormParam("usuario") String usuario,
			@FormParam("forumQuestao") String forumQuestao

			) {		
		ForumResposta objForumResposta = new ForumResposta();
		logger.info("eventoAction ...");
		ForumResposta  resultado;
	

		// get tipo usuario
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(Integer.parseInt(usuario));
		Usuario objUsuario= rsUsuario.get(0);
		//TODO: Validar valores.

		// get tipo Roteiro
		List<ForumQuestao> rsForumQuestao;
		rsForumQuestao = new ForumQuestaoService().listarkey(Integer.parseInt(forumQuestao));
		ForumQuestao objForumQuestao= rsForumQuestao.get(0);
		//TODO: Validar valores.

		if (action.equals("create")) {
			logger.info("Criando no  Forum Questao");

			objForumResposta.setResposta(resposta);
			objForumResposta.setData(new Date());
			objForumResposta.setUsuario(objUsuario);
			objForumResposta.setForumQuestao(objForumQuestao);
			objForumResposta.setVisto(0);
		

			resultado = new ForumRespostaService().criarForumResposta(objForumResposta);


		} else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<ForumResposta> rsForumResposta;
			rsForumResposta= new ForumRespostaService().listarkey(id);
			objForumResposta = rsForumResposta.get(0);
			objForumResposta.setResposta(resposta);
			objForumResposta.setData(new Date());
			objForumResposta.setUsuario(objUsuario);
			objForumResposta.setForumQuestao(objForumQuestao);
			objForumResposta.setVisto(0);

			resultado = new ForumRespostaService().atualizarForumResposta(objForumResposta);
			

		}else {
			logger.info("Erro na URI  " + action);
			return "0";
		}


		return Integer.toString(resultado.getIdforumResposta());

	}

}
