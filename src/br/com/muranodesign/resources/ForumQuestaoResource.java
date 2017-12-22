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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.ForumQuestao;
import br.com.muranodesign.model.ForumResposta;
import br.com.muranodesign.model.Roteiro;
import br.com.muranodesign.model.Usuario;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos criação de questões no forum
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("ForumQuestao")
public class ForumQuestaoResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ForumQuestaoResource.class.getName());

	/**
	 * Gets the forum questao.
	 *
	 * @return the forum questao
	 */
	@GET
	@Produces("application/json")
	public List<ForumQuestao> getForumQuestao() {
		logger.info("Listar ForumQuestao ...");
		List<ForumQuestao> resultado;
		resultado = new ForumQuestaoService().listarTodos();
		logger.info("QTD ForumQuestao : " + resultado.size());
		return resultado;
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
	public ForumQuestao getEvento(@PathParam("id") int id) {
		logger.info("Lista ForumQuestao  por id " + id);
		List<ForumQuestao> resultado;
		resultado = new ForumQuestaoService().listarkey(id);
		ForumQuestao evento = resultado.get(0);

		return evento;

	}

	
	/**
	 * Lista por rotina
	 * @param id
	 * @return
	 */
	@Path("ListaPorAnoEstudo/{id}")
	@GET
	@Produces("application/json")
	public List<ForumQuestao> ForumListaAno(@PathParam("id") int id){
		List<Roteiro> roteiro = new RoteiroService().listarAno(id);
		List<ForumQuestao> forum = new ArrayList<ForumQuestao>();
		
		if(!roteiro.isEmpty()){
			for (Roteiro roteiro2 : roteiro) {
				forum.addAll(new ForumQuestaoService().listaRoteiro(roteiro2.getIdroteiro()));
			}
		}
		
		return forum;
		
	}
	
	/**
	 * Listar por roteiro
	 * @param id
	 * @return
	 */
	@Path("ListaPorRoteiro/{id}")
	@GET
	@Produces("application/json")
	public List<ForumQuestao> ListaPorRoteiro(@PathParam("id") int id){
		
		
		return new ForumQuestaoService().listaRoteiro(id);
		
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	@Path("RespostasNVistas/{idUsuario}")
	@GET
	@Produces("application/json")
	public long RespostasNVistas(@PathParam("idUsuario") int idUsuario){
		List<ForumQuestao> questao = new ForumQuestaoService().listaUser(idUsuario);
		List<ForumResposta> resp = new ArrayList<ForumResposta>();
		
		for (ForumQuestao forumQuestao : questao) {
			resp.addAll(new ForumRespostaService().ListaNVisto(forumQuestao.getIdforumQuestao()));	
		}
		
		return resp.size();
	}

	/**
	 * Alterar a flag para vista 
	 * @param idQuestao
	 * @return
	 */
	@Path("RespostaVista/{idQuestao}")
	@GET
	@Produces("application/json")
	public String RespostaVista(@PathParam("idQuestao") int idQuestao){
		List<ForumResposta> resp = new ForumRespostaService().ListaNVisto(idQuestao);
		
		for (ForumResposta forumResposta : resp) {
			forumResposta.setVisto(1);
			new ForumRespostaService().atualizarForumResposta(forumResposta);
		}
		
		return "alterada";
	}
	
	
	//Pendente
	@Path("RangeData/")
	@GET
	@Produces("application/json")
	public List<ForumQuestao> RangeData(){
		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
		
		Calendar c = Calendar.getInstance();
		int dia = c.get(Calendar.DAY_OF_MONTH);
		String data2;
		if(dia > 7){
			dia = dia - 7;
			String quebra[] = data.split("-");
			
			if(dia > 10){
			
				data2 = quebra[0]+"-"+quebra[1]+"-"+Integer.toString(dia);
			}else{
				data2 = quebra[0]+"-"+quebra[1]+"-"+"0"+Integer.toString(dia);
			}
			
		}else{
			int aux = 30;
			dia = dia - 7;
			aux = aux + dia;
			
			String quebra[] = data.split("-");
			int mes = Integer.parseInt(quebra[1]);
			mes = mes - 1;
			
			
			data2 = quebra[0]+"-"+Integer.toString(mes)+"-"+Integer.toString(aux);
			
		}

		return new ForumQuestaoService().Range(data2,data);
	}
	

		@Path("RangeDataCount/")
		@GET
		@Produces("application/json")
		public int RangeDataCount(){
			
			
			Date dataHoje = new Date();
			SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
			String data = formataData.format(dataHoje);
			
			Calendar c = Calendar.getInstance();
			int dia = c.get(Calendar.DAY_OF_MONTH);
			String data2;
			if(dia > 7){
				dia = dia - 7;
				String quebra[] = data.split("-");
				
				if(dia > 10){
					;
					data2 = quebra[0]+"-"+quebra[1]+"-"+Integer.toString(dia);
				}else{
					data2 = quebra[0]+"-"+quebra[1]+"-"+"0"+Integer.toString(dia);
				}
				
			}else{
				int aux = 30;
				dia = dia - 7;
				aux = aux + dia;
				
				String quebra[] = data.split("-");
				int mes = Integer.parseInt(quebra[1]);
				mes = mes - 1;
				
				
				data2 = quebra[0]+"-"+Integer.toString(mes)+"-"+Integer.toString(aux);
				
			}

			
			return new ForumQuestaoService().Range(data2,data).size();
		}
	
	
	
	/**
	 * Listar por like de nome de roteiro
	 * @param letra
	 * @return
	 */
	@Path("ListaLikeRoteiro/{letra}") 
	@GET
	@Produces("application/json")
	public List<ForumQuestao> ListaLikeRoteiro(@PathParam("letra") String letra){
		return new ForumQuestaoService().ListaLikeRoteiro(letra);
	}
	
	/**
	 * Lista ordenado por data
	 * @return
	 */
	@Path("ListaOrdenado/") 
	@GET
	@Produces("application/json")
	public List<ForumQuestao> listAllOrder(){
		return new ForumQuestaoService().listAllOrder();
	}
	
	/**
	 * Removes the forum questao.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeForumQuestao(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("ForumQuestao  " + action);
		if ( action.equals("delete")) {
			List<ForumQuestao> resultado;
			resultado = new ForumQuestaoService().listarkey(id);
			ForumQuestao res = resultado.get(0);
			new ForumQuestaoService().deletarForumQuestao(res);
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Listar forum questao por top de qualquer valor
	 * @param qtd
	 * @return list
	 */
	@Path("topN/{qtd}")
	@GET
	@Produces("application/json")
	public List<ForumQuestao> getTopN(@PathParam("qtd") int qtd){
		
    	return new ForumQuestaoService().topN(qtd);
		
	}

	/**
	 * Criar e alterar forum questao
	 * @param action
	 * @param strid
	 * @param questao
	 * @param usuario
	 * @param roteiro
	 * @return  id
	 */
	@POST
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") String strid,
			@FormParam("questao") String questao,
			@FormParam("usuario") String usuario,
			@FormParam("roteiro") String roteiro

			) {
				
 
		
		ForumQuestao objForumQuestao = new ForumQuestao();
		logger.info("eventoAction ...");
		ForumQuestao  resultado;
		

		// get tipo usuario
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(Integer.parseInt(usuario));
		Usuario objUsuario= rsUsuario.get(0);
		//TODO: Validar valores.

		// get tipo Roteiro
		List<Roteiro> rsRoteiro;
		rsRoteiro = new RoteiroService().listarkey(Integer.parseInt(roteiro));
		Roteiro objRoteiro= rsRoteiro.get(0);
		//TODO: Validar valores.

		if (action.equals("create")) {
			logger.info("Criando no  Forum Questao");

			objForumQuestao.setQuestao(questao);
			objForumQuestao.setData(new Date());
			objForumQuestao.setUsuario(objUsuario);
			objForumQuestao.setRoteiro(objRoteiro);
		

			resultado = new ForumQuestaoService().criarForumQuestao(objForumQuestao);


		} else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<ForumQuestao> rsForumQuestao;
			rsForumQuestao = new ForumQuestaoService().listarkey(id);
			objForumQuestao = rsForumQuestao.get(0);
			objForumQuestao.setQuestao(questao);
			//objForumQuestao.setQuestao(questao);
			objForumQuestao.setData(new Date());
			objForumQuestao.setUsuario(objUsuario);
			objForumQuestao.setRoteiro(objRoteiro);

			resultado = new ForumQuestaoService().atualizarForumQuestao(objForumQuestao);


		}else {
			logger.info("Erro na URI  " + action);
			return "0";
		}

		return Integer.toString(resultado.getIdforumQuestao());

	}

}
