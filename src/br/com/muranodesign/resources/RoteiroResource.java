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

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AnoEstudoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.AtribuicaoRoteiroExtraService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Objetivo;
import br.com.muranodesign.model.Roteiro;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos ao roteiro resource
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

@Path("Roteiro")
public class RoteiroResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(RoteiroResource.class.getName());

	/**
	 * Gets the roteiro.
	 *
	 * @return the roteiro
	 */
	@GET
	@Produces("application/json")
	public List<Roteiro> getRoteiro() {
		logger.info("Listar Roteiro ...");
		List<Roteiro> resultado;
		
		resultado = new RoteiroService().listarTodos();
		
		
		logger.info("QTD Roteiro : " + resultado.size());
		return resultado;
	}
	
	
	
	/**
	 * Listar os roteiros refentes ao ano de estudo solicitado
	 * @param ano
	 * @return Lista de roteiros.
	 */
	
	@Path("/RoteiroAno/{ano}")
	@GET
	@Produces("application/json")
	public List<Roteiro> getRoreiroAno(@PathParam("ano") int ano){
		logger.info("Lista Roteiro  por ano " + ano);
		List<Roteiro> resultado;
		resultado = new RoteiroService().listarAno(ano);
		return resultado;
	}
	
	/**
	 * Lista Like Roteiro
	 * @param letra
	 * @return
	 */
	@Path("ListaLikeRoteiro/{letra}") 
	@GET
	@Produces("application/json")
	public List<Roteiro> ListaLikeRoteiro(@PathParam("letra") String letra){
		List<Roteiro> like = new RoteiroService().ListaLikeRoteiro(letra);
		if(!like.isEmpty()){
			return like;
		}else{
			return new RoteiroService().listarTodos();
		}
	
	}
	
	/**
	 * Listar os roteiros ferentes ao aluno
	 * @return list
	 */
	@Path("/RoteiroAluno/{idAluno}/{ano}")
	@GET
	@Produces("application/json")
	public List<AtribuicaoRoteiroExtra> getRoteiroAluno(@PathParam("idAluno") int idAluno, @PathParam("ano") String ano){
		logger.info("Lista Roteiro  por ano e aluno" + ano + idAluno);
		List<AtribuicaoRoteiroExtra> resultado;
		
		
		resultado = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(idAluno).get(0), new AnoLetivoService().listarAnoLetivo(ano).get(0));

		
		return resultado;
	}
	
	/**
	 * Roteiro range
	 * @param primeiro
	 * @param ultimo
	 * @return
	 */
	@Path("RoteiroRange/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<Roteiro> getRoteiroRange(@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		return  new RoteiroService().listRoteiroRange(primeiro, ultimo);
	}
	
	/**
	 * Listar nome
	 * @param anoEstudo
	 * @return
	 */
	@Path("ListarNomeIdAnoEstudo/{idAnoEstudo}")
	@GET
	@Produces("application/json")
	public List<Roteiro> listarLazyNome (@PathParam("idAnoEstudo") int anoEstudo){
		logger.info("Listar Por Ano Estudo id " + anoEstudo);
		List<Roteiro> resultado;
		resultado = new RoteiroService().listarAnoEstudoLazy(anoEstudo);
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
	public Roteiro getEvento(@PathParam("id") int id) {
		logger.info("Lista Roteiro  por id " + id);
		List<Roteiro> resultado;
		resultado = new RoteiroService().listarkey(id);
		Roteiro evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Inativar Roteiro
	 * @param id
	 * @return
	 */
	@Path("InativarRoteiro/{id}")
	@GET
	@Produces("text/plain")
	public String deletarRoteiro(@PathParam("id") int id){
		List<Objetivo> objs = new ObjetivoService().listarRoteiro(id);
		Roteiro roteiro = new RoteiroService().listarkey(id).get(0);
		
		String retorno = "";
		
		if(!objs.isEmpty()){
			roteiro.setAtivo(0);
			new RoteiroService().atualizarRoteiro(roteiro);
			
			retorno = "inativado";
		}else{
			new RoteiroService().deletarRoteiro(roteiro);
			
			retorno = "deletado";
		}
		
		
		return retorno;
	}
	
	/**
	 * Removes the roteiro.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeRoteiro(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("Roteiro  " + action);
		if ( action.equals("delete")) {
			List<Roteiro> resultado;
			resultado = new RoteiroService().listarkey(id);
			Roteiro roteiro = resultado.get(0);
			new RoteiroService().deletarRoteiro(roteiro);
			return "true";
		} else {
			return "false";
		}

	}
	
	
	/**
	 * Evento action.
	 *
	 * @param action the action
	 * @param strid the strid
	 * @param nome the nome
	 * @param descricao the descricao
	 * @param anoEstudo the ano estudo
	 * @return the string
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			
			@FormParam("nome") String nome,
			@FormParam("descricao") String descricao,
			@FormParam("anoEstudo") String anoEstudo,
			@FormParam("ativo") String ativo
			
			) {
		Roteiro objRoteiro= new Roteiro();
		logger.info("eventoAction ...");
		Roteiro resultado;
		
		List<AnoEstudo> rsAnoEstudo;
		rsAnoEstudo = new AnoEstudoService().listarkey(Integer.parseInt(anoEstudo));
		AnoEstudo objAnoEstudo = rsAnoEstudo.get(0);
		
		if (action.equals("create")) {
			
			objRoteiro.setNome(nome);
			objRoteiro.setDescricao(descricao);
			objRoteiro.setAnoEstudo(objAnoEstudo);
			objRoteiro.setAtivo(Integer.parseInt(ativo));
			resultado= new RoteiroService().criarRoteiro(objRoteiro);
			
		} else if (action.equals("update")) {
			int id=Integer.parseInt(strid);
			List<Roteiro> rsRoteiro;
			rsRoteiro= new RoteiroService().listarkey(id);
			objRoteiro= rsRoteiro.get(0);
			objRoteiro.setIdroteiro(id);
			objRoteiro.setNome(nome);
			objRoteiro.setDescricao(descricao);
			objRoteiro.setAnoEstudo(objAnoEstudo);
			objRoteiro.setAtivo(Integer.parseInt(ativo));
			resultado= new RoteiroService().atualizarRoteiro(objRoteiro);
			
		} else {
			return "0";
		}
		
		return Integer.toString(resultado.getIdroteiro());
	}
	
	
	
	
	

}