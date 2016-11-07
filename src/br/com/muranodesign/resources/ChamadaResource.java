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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.ChamadaService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.Chamada;
import br.com.muranodesign.util.StringUtil;


/**
 * Classe tem como objetivo disponibilizar os serviços chamada
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

@Path("Chamada")
public class ChamadaResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ChamadaResource.class.getName());

	/**
	 * Gets the chamada.
	 *
	 * @return the chamada
	 */
	@GET
	@Produces("application/json")
	public List<Chamada> getChamada() {
		logger.info("Listar Chamada ...");
		List<Chamada> resultado;
		resultado = new ChamadaService().listarTodos();
		logger.info("QTD Chamada : " + resultado.size());
		return resultado;
	}

	/**
	 * Gets Chamada.
	 *
	 * @param id do aluno
	 * @return the Chamada
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Chamada getChamada(@PathParam("id") int id) {
		logger.info("Lista Chamada  por id " + id);
		List<Chamada> resultado;
		resultado = new ChamadaService().listarkey(id);
		Chamada evento = resultado.get(0);

		return evento;

	}
	
	
	/**
	 * Gets Chamada.
	 *
	 * @param id do aluno
	 * @param precenca
	 * @return the Chamada
	 */
	@Path("{idAluno}/{precenca}")
	@GET
	@Produces("application/json")
	public List<Chamada> getPrecenca(@PathParam("idAluno") int idAluno , @PathParam("precenca") int precenca ) {
		logger.info("Buscar precenca por id do Aluno " + idAluno);
		
		List<Aluno> rsAluno;
		rsAluno = new AlunoService().listarkey(idAluno);
		Aluno objAluno = null;
		
		List<Chamada> resultado = null;
		if (! rsAluno.isEmpty()){
			objAluno = rsAluno.get(0);
			
			logger.info("Buscar precenca por id do Aluno " + idAluno + "precenca = " + precenca  );
			resultado = new ChamadaService().listaPrecenca(objAluno, precenca);
		} 
		
		return resultado;

	}

	
	/**
	 * Listar faltas por id de aluno
	 * @param id
	 * @return numero de faltas
	 */
	@Path("faltas/{id}")
	@GET
	@Produces("application/json")
	public long countFaltas(@PathParam("id") int id){
		
		return new ChamadaService().countFaltas(id);
	}
	
	/**
	 * Listar  data da chamada 
	 * @param id
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	@Path("dataChamada/{id}/{data}")
	@GET
	@Produces("application/json")
	public List<Chamada> dataPrecenca(@PathParam("id") int id, @PathParam("data") String data) throws ParseException{
		String texto = data.replaceAll("-", "/");
		
		DateFormat formatter = new SimpleDateFormat("yy/MM/dd");  
		Date date = (Date)formatter.parse(texto); 
		
		return new ChamadaService().dataPresenca(id, date);
	}
	
	@Path("dataChamadaAtual/{id}")
	@GET
	@Produces("application/json")
	public List<Hashtable<String, String>> getdataChamadaAtual(@PathParam("id") int id/*, @PathParam("data") String data*/) throws ParseException{
		String data = "";
		
		List<AlunoVariavel> alunoVariavel = new AlunoVariavelService().listaGrupo(id);
	
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH);
		mes++;
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		
		if(dia < 10 && mes < 10){
			data = Integer.toString(ano) + "-0" + Integer.toString(mes) + "-0" +  Integer.toString(dia);
			
		}else if(dia < 10 && mes >= 10){
			data = Integer.toString(ano) + "-" + Integer.toString(mes) + "-0" +  Integer.toString(dia);
			
		}else if(dia >= 10 && mes < 10){
			data = Integer.toString(ano) + "-0" + Integer.toString(mes) + "-" +  Integer.toString(dia);
			
		}else if(dia >= 10 && mes >= 10){
			data = Integer.toString(ano) + "-" + Integer.toString(mes) + "-" +  Integer.toString(dia);
		}
		
		
		String texto = data.replaceAll("-", "/");
		
		DateFormat formatter = new SimpleDateFormat("yy/MM/dd");  
		Date date = (Date)formatter.parse(texto);
		
	
		List<Hashtable<String, String>> retorno = new ArrayList<Hashtable<String,String>>();
		
		for (AlunoVariavel alunoVariavel2 : alunoVariavel) {
			List<Chamada> chamadas = new ChamadaService().dataPresenca(alunoVariavel2.getAluno().getIdAluno(), date);//stringUtil.converteStringData(data));
			
			Hashtable<String, String> aux = new Hashtable<String, String>();
			
			if(!chamadas.isEmpty()){
				aux.put("idAluno", Integer.toString(alunoVariavel2.getAluno().getIdAluno()));
				aux.put("nome", alunoVariavel2.getAluno().getNome());
				aux.put("idAlunoVariavel", Integer.toString(alunoVariavel2.getIdalunoVariavel()));
				aux.put("status", Integer.toString(chamadas.get(0).getPresenca()));
				aux.put("idChamada", Integer.toString(chamadas.get(0).getIdchamada()));
				aux.put("existe", "0");
				
				retorno.add(aux);
				
			}else{
				aux.put("idAluno", Integer.toString(alunoVariavel2.getAluno().getIdAluno()));
				aux.put("nome", alunoVariavel2.getAluno().getNome());
				aux.put("idAlunoVariavel", Integer.toString(alunoVariavel2.getIdalunoVariavel()));
				aux.put("status", "não tem");
				aux.put("idChamada", "nao tem");
				aux.put("existe", "1");
				
				retorno.add(aux);
			}
		}

		return retorno;		
	}
	
	
	/**
	 * Gets Chamada.
	 *
	 * @param id do aluno
	 * @param precenca
	 * @return Total de precenca
	 */
	@Path("total/{idAluno}/{precenca}")
	@GET
	@Produces("application/json")
	public int getPrecencaTotal(@PathParam("idAluno") int idAluno , @PathParam("precenca") int precenca ) {
		logger.info("Buscar precenca por id do Aluno " + idAluno);
		
		List<Aluno> rsAluno;
		rsAluno = new AlunoService().listarkey(idAluno);
		Aluno objAluno = null;
	
		int total = 0;
		if (! rsAluno.isEmpty()){
			objAluno = rsAluno.get(0);
			List<Chamada> resultado;
			
			logger.info("Buscar precenca por id do Aluno " + idAluno + "precenca = " + precenca  );
			resultado = new ChamadaService().listaPrecenca(objAluno, precenca);
			total = resultado.size();
		} 
		
		return total;

	}
	
	
	
	/**
	 * Removes the chamada.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeChamada(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("Chamada  " + action);
		if ( action.equals("delete")) {
			List<Chamada> resultado;
			resultado = new ChamadaService().listarkey(id);
			Chamada evento = resultado.get(0);
			new ChamadaService().deletarChamada(evento);
			return "true";
		} else {
			return "false";
		}

	}
	
	
	/**
	 * Criar e alterar chamada
	 * @param action
	 * @param strid
	 * @param presenca
	 * @param data
	 * @param aluno
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			@FormParam("presenca") String presenca,
			@FormParam("data") String data,
			@FormParam("aluno") String aluno
			
			
			) {
		Chamada objChamada = new Chamada();
		logger.info("eventoAction ...");
		Chamada resultado;

		List<Aluno> rsAluno;
		rsAluno = new AlunoService().listarkey(Integer.parseInt(aluno));
		Aluno objAluno= rsAluno.get(0);
		StringUtil stringUtil = new StringUtil();
		
		
		if (action.equals("create")) {
			
			objChamada.setAluno(objAluno);
			objChamada.setData(stringUtil.converteStringData(data));
			objChamada.setPresenca(Short.parseShort(presenca));
			
			resultado = new ChamadaService().criarChamada(objChamada);
			
		}  else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<Chamada> rsChamada;
			rsChamada= new ChamadaService().listarkey(id);
			objChamada= rsChamada.get(0);
		
			objChamada.setAluno(objAluno);
			objChamada.setData(stringUtil.converteStringData(data));
			objChamada.setPresenca(Short.parseShort(presenca));
			
			resultado =  new ChamadaService().atualizarChamada(objChamada);
			
		} else {
			return "0";
		}
	    return Integer.toString(resultado.getIdchamada());
	
		}
}
