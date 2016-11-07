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
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.PlanoEstudoService;
import br.com.muranodesign.business.RegistroDiarioService;
import br.com.muranodesign.model.PlanoEstudo;
import br.com.muranodesign.model.RegistroDiario;
import br.com.muranodesign.util.StringUtil;




/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos ao registro diario
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("RegistroDiario")
public class RegistroDiarioResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(RegistroDiarioResource.class.getName());
	
	/**
	 * Gets the registro diario.
	 *
	 * @return the registro diario
	 */
	@GET
	//@Produces("text/xml")
	@Produces("application/json")
	public List<RegistroDiario> getRegistroDiario() {
		logger.debug("Listar RegistroDiario ...");
		List<RegistroDiario> resultado;
		 resultado = new RegistroDiarioService().listarTodos();
		 logger.debug("QTD RegistroDiario : " +  resultado.size());
		return resultado;
	}

	/**
	 * Gets the registro diario.
	 *
	 * @param id the id
	 * @return the registro diario
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public RegistroDiario getRegistroDiario(@PathParam("id") int id) {
		
		logger.debug("Lista Evento  por id " + id );
		 List<RegistroDiario> resultado;
		 resultado = new RegistroDiarioService().listarkey(id);
		 RegistroDiario RegistroDiario = resultado.get(0);
		 
		return RegistroDiario;
		
	}

  
	/**
	 * Removes the registro diario.
	 *
	 * @param id the id
	 * @return the string
	 */
	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeRegistroDiario(@PathParam("id") int id) {
		
		logger.debug("Remover RegistroDiario " + id );
		
		List<RegistroDiario> resultado;
		 resultado = new RegistroDiarioService().listarkey(id);
		 RegistroDiario RegistroDiario = resultado.get(0);

		new RegistroDiarioService().deletarRegistroDiario(RegistroDiario);

		return "Deletado";

	}
	
	/**
	 * Listar registro por plano de estudo
	 * @param id
	 * @return list
	 */
	@Path("PlanoEstudo/{id}")
	@GET
	@Produces("application/json")
	public List<RegistroDiario> getRegistroPlanoEstudo(@PathParam("id") int id){
		logger.debug("listar RegistroDiario por id plano estudo" + id );
		List<RegistroDiario> resultado;
		
		resultado = new RegistroDiarioService().listarPlanoEstudo(id);
		return resultado;
	}
	
	/**
	 * Listar o ultimo 
	 * @return list
	 */
	@Path("utimo")
	@GET
	@Produces("application/json")
	public List<RegistroDiario> getUtimo(){
		logger.debug("listar utimo RegistroDiario");
		List<RegistroDiario> resultado;
		
		resultado = new RegistroDiarioService().listarUltimo();
		return resultado;
		
	}
	
	@Path("PlanoEstudoData/{id}/{data}")
	@GET
	@Produces("application/json")
	public List<RegistroDiario> getPlanoEstudoData(@PathParam("id") int id,@PathParam("data") String data){
		StringUtil t = new StringUtil();
		return new RegistroDiarioService().listaPlanoEstudoDara(id, t.converteStringData(data));
	}
	
	
	/**
	 * Listar registros diarios por mes e ano
	 * @param mes
	 * @param ano
	 * @return list
	 * @throws ParseException
	 */
	@Path("RegistroMes/{mes}/{ano}")
	@GET
	@Produces("application/json")
	public List<RegistroDiario> getMes(@PathParam("mes") String mes,@PathParam("ano") String ano) throws ParseException{
		List<RegistroDiario> resultado = new ArrayList<RegistroDiario>();
		
	
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
		
		String ini = "";
		String fim = "";
		Date comeco = new Date();
		Date termino = new Date();
		
		Calendar c = Calendar.getInstance();  
		
		if(mes.equals("01")){
			c.set(Calendar.MONTH,Calendar.JANUARY);
		}else if(mes.equals("02")){
			c.set(Calendar.MONTH,Calendar.FEBRUARY);
		}else if(mes.equals("03")){
			c.set(Calendar.MONTH,Calendar.MARCH);
		}else if(mes.equals("04")){
			c.set(Calendar.MONTH,Calendar.APRIL);
		}else if(mes.equals("05")){
			c.set(Calendar.MONTH,Calendar.MAY);		
		}else if(mes.equals("06")){
			c.set(Calendar.MONTH,Calendar.JUNE);		
		}else if(mes.equals("07")){
			c.set(Calendar.MONTH,Calendar.JULY);
		}else if(mes.equals("08")){
			c.set(Calendar.MONTH,Calendar.AUGUST);
		}else if(mes.equals("09")){
			c.set(Calendar.MONTH,Calendar.SEPTEMBER);
		}else if(mes.equals("10")){
			c.set(Calendar.MONTH,Calendar.OCTOBER);
		}else if(mes.equals("11")){
			c.set(Calendar.MONTH,Calendar.NOVEMBER);
		}else if(mes.equals("11")){
			c.set(Calendar.MONTH,Calendar.DECEMBER);
		}
		 
		ini = String.valueOf(c.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		fim = String.valueOf(c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		ini = ini +"/"+mes+"/"+ano;
		fim = fim +"/"+mes+"/"+ano;
		
		
	
		comeco = (Date)formatter.parse(ini);
		termino = (Date)formatter.parse(fim);
		
	
		
		resultado = new RegistroDiarioService().listarMes(comeco, termino);
		
		return resultado;
	}
	
	/**
	 * Registro diario action.
	 *
	 * @param action the action
	 * @param strid the strid
	 * @param data the data
	 * @param registro the registro
	 * @param planoEstudo the plano estudo
	 * @return the string
	 * @throws ParseException 
	 */
	@POST
	@Produces("text/plain")
	public String RegistroDiarioAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			@FormParam("data") String data,
			@FormParam("registro") String registro,
			@FormParam("planoEstudo") String planoEstudo 
		
			
			) throws ParseException {
		RegistroDiario objRegistroDiario = new RegistroDiario();
		RegistroDiario resultado;
			
		
		// get tipo evento
		List<PlanoEstudo> rsPlanoEstudo;
		rsPlanoEstudo= new PlanoEstudoService().listarkey(Integer.parseInt(planoEstudo));
		PlanoEstudo objPlanoEstudo = rsPlanoEstudo.get(0);
		//TODO: Validar valores.
		
		StringUtil stringUtil = new StringUtil();
		
	
		if (action.equals("create")) {
			logger.info("Criando no  evento");
			//objRegistroDiario.setData(date);
			objRegistroDiario.setData(stringUtil.converteStringData(data));
			objRegistroDiario.setRegistro(registro);
			objRegistroDiario.setPlanoEstudo(objPlanoEstudo);
		
			
			
			resultado = new RegistroDiarioService().criarRegistroDiario(objRegistroDiario);
		}else if (action.equals("update")) {
			int id=Integer.parseInt(strid);
			
			
			
			
			List<RegistroDiario> rsRegistroDiario;
			rsRegistroDiario= new RegistroDiarioService().listarkey(id);
			objRegistroDiario = rsRegistroDiario.get(0);

			objRegistroDiario.setData(stringUtil.converteStringData(data));
			objRegistroDiario.setRegistro(registro);
			objRegistroDiario.setPlanoEstudo(objPlanoEstudo);
		
			
			
			resultado = new RegistroDiarioService().atualizarRegistroDiario(objRegistroDiario);
			
		} else {
			
			logger.info("Erro na URI  " + action);
			return "0";
			
		}
		
		return Integer.toString(resultado.getIdregistroDiario());

	}
	
	
	
	
	
}
