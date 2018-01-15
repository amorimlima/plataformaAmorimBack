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

import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.CalendarioService;
import br.com.muranodesign.business.TipoEventoService;
import br.com.muranodesign.model.Calendario;
import br.com.muranodesign.model.TipoEvento;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


/**
 * Classe tem como objetivo disponibilizar os serviços caletario Resource
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

@Path("Calendario")
public class CalendarioResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(CalendarioResource.class.getName());

	/**
	 * Gets the calendario.
	 *
	 * @return the calendario
	 */
	@GET
	@Produces("application/json")
	public List<Calendario> getCalendario() {
		logger.info("Listar Calendario ...");
		List<Calendario> resultado;
		resultado = new CalendarioService().listarTodos();
		logger.info("QTD Calendario : " + resultado.size());
		return resultado;
	}
	
	@Path("CountDiasLetivosAluno/{idAluno}")
	@GET
	@Produces("text/plain")
	public String countdDiasLetivos(@PathParam("idAluno") int idAluno){
		
		StringUtil stringUtil = new StringUtil();
		
		Date inicio = new AlunoVariavelService().listaAluno(idAluno).get(0).getInicio();
		Date fim = stringUtil.converteStringData(Calendar.getInstance().get(Calendar.YEAR) + "-12-31");
		
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(inicio);
		Calendar dataFim = Calendar.getInstance();
		dataFim.setTime(fim);
		if (dataInicio.get(Calendar.YEAR) < dataFim.get(Calendar.YEAR)){
			inicio = stringUtil.converteStringData(Calendar.getInstance().get(Calendar.YEAR) + "-01-01");
			dataInicio.setTime(inicio);
		}
		return Integer.toString(new CalendarioService().diasLetivosCount(dataInicio, dataFim));

	}
	
	@Path("CountDiasLetivosAlunoIntervalo/{dataInicio}/{dataFim}/{idAluno}")
	@GET
	@Produces("text/plain")
	public String countdDiasLetivos(
			@PathParam("idAluno") int idAluno, 
			@PathParam("dataInicio") String dataInicio, 
			@PathParam("dataFim") String dataFim){
		
		StringUtil stringUtil = new StringUtil();
		
		Date inicioAluno = new AlunoVariavelService().listaAluno(idAluno).get(0).getInicio();
		Date inicio = stringUtil.converteStringData(dataInicio);
		Date fim = stringUtil.converteStringData(dataFim);
		
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(inicio);
		Calendar calendarInicioAluno = Calendar.getInstance();
		calendarInicioAluno.setTime(inicioAluno);
		Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(fim);
		if (calendarInicioAluno.getTime().getTime() < calendarInicio.getTime().getTime()){
			calendarInicio = calendarInicioAluno;
		}
		return Integer.toString(new CalendarioService().diasLetivosCount(calendarInicio, calendarFim));

	}
	
	@Path("CountDiasLetivosIntervalo/{dataInicio}/{dataFim}/")
	@GET
	@Produces("text/plain")
	public String countdDiasLetivos(
			@PathParam("dataInicio") String dataInicio, 
			@PathParam("dataFim") String dataFim){
		
		StringUtil stringUtil = new StringUtil();
		
		Date inicio = stringUtil.converteStringData(dataInicio);
		Date fim = stringUtil.converteStringData(dataFim);
		
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(inicio);
		Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(fim);
		return Integer.toString(new CalendarioService().diasLetivosCount(calendarInicio, calendarFim));

	}
	
	@Path("Range")
	@GET
	@Produces("application/json")
	public List<Calendario> getRange(){
		return new CalendarioService().listarRange();
	}
	
	
	/**
	 * Listar calendarios visiveis
	 * @return list
	 */
	@Path("visivel")
	@GET
	@Produces("application/json")
	public List<Calendario> getCalendarioVisivel(){
		logger.info("Listar Calendario visivrl...");
		List<Calendario> resultado;
		resultado = new CalendarioService().listVisivel();
		logger.info("QTD Calendario : " + resultado.size());
		return resultado;
	}
	
	
	/**
	 * Gets the calendario.
	 *
	 * @return the calendario
	 */
	@Path("{dataInicio}/{DataFim}")
	@GET
	@Produces("application/json")
	public List<Calendario> getCalendario(@PathParam("dataInicio") String dataInicio,@PathParam("DataFim") String DataFim) {
		logger.info("Listar Calendario ...");
		List<Calendario> resultado;
		StringUtil stringUtil = new StringUtil();
		resultado = new CalendarioService().listarFiltroData(stringUtil.converteStringData(dataInicio), stringUtil.converteStringData(DataFim));
		logger.info("QTD Calendario : " + resultado.size());
		return resultado;
	}

	
	/**
	 * Gets the calendario.
	 *
	 * @return the calendario
	 */
	@Path("feriados/{ano}")
	@GET
	@Produces("application/json")
	public List<Calendario> getCalendarioFeriados(@PathParam("ano") int ano) {
		logger.info("Listar Calendario ...");
		List<Calendario> resultado;
		
		resultado = new CalendarioService().listaFeriado(ano);
		logger.info("QTD Calendario : " + resultado.size());
		return resultado;
	}

	/**
	 * Listar calendarios por id de evento
	 * @param id
	 * @return list
	 * @throws ParseException
	 */
	@Path("Evento/{id}")
	@GET
	@Produces("application/json")
	public List<Calendario> getByEvento(@PathParam("id") int id) throws ParseException {

		logger.info("Lista Calendario  por id " + id);
		List<Calendario> resultado;
		resultado = new CalendarioService().listarEvento(id);
		
		return resultado;

	}
	
	/**
	 * Listar calendarios por id de evento
	 * @param id
	 * @return list
	 * @throws ParseException
	 */
	@Path("Mes/{mes}/{ano}")
	@GET
	@Produces("application/json")
	public List<Calendario> getByEvento(@PathParam("mes") int mes, @PathParam("ano") int ano) throws ParseException {

		logger.info("Lista Calendario  por ano e mês " + mes + "/" + ano);
		List<Calendario> resultado;
		resultado = new CalendarioService().listarGeralMes(mes, ano);
		
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
	public Calendario getEvento(@PathParam("id") int id) {
		logger.info("Lista Calendario  por id " + id);
		List<Calendario> resultado;
		resultado = new CalendarioService().listarkey(id);
		Calendario evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Delete de calendario
	 * @param id
	 * @return String
	 */
	@Path("delete/{id}")
	@Produces("text/plain")
	public String removeCalendario(@PathParam("id") int id) {

		logger.info("Calendario  " );
		
			List<Calendario> resultado;
			resultado = new CalendarioService().listarkey(id);
			Calendario res = resultado.get(0);
			new CalendarioService().deletarCalendario(res);
			return "true";
		
	}
	
	/**
	 * upload de imagem
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return obj calendario
	 */
	@POST
	@Path("upload/calendario/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Calendario eventoAction(

			@PathParam("id") String strId,
			@FormDataParam("imagem") InputStream uploadedInputStream,
			@FormDataParam("imagem") FormDataContentDisposition fileDetail

	) {

		Calendario objCalendario = new Calendario();
		int id = Integer.parseInt(strId);
		List<Calendario> rsCalendario;
		rsCalendario = new CalendarioService().listarkey(id);
		objCalendario = rsCalendario.get(0);

		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;

		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		String anexo = "http://177.55.99.90/files/" + arquivo;

		logger.info("anexo" + anexo);

		objCalendario.setImagem(anexo);
		Calendario resultado = new CalendarioService().atualizarCalendario(objCalendario);

		return resultado;

	}
	
	
	/**
	 * Serviço reponsavel por cadastra e atualizar dados
	 *
	 * @param action Metodo a ser executado create ou update
	 * @param strid id para update
	 * @param dataInicio  data inicio
	 * @param dataFim  data fim
	 * @param evento  evento
	 * @param descricao  descricao
	 * @param calendario  calendario
	 * @param anoLetivo  ano letivo
	 * @param tipoEvento  tipo evento
	 * @return identificador de controle interno da aplicação
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			
			@FormParam("ano") String ano,
			@FormParam("dataInicio") String dataInicio,
			@FormParam("dataFim") String dataFim,
			@FormParam("descricao") String descricao,
			
			@FormParam("evento") String evento,
			@FormParam("feriado") String feriado,
			@FormParam("aula") String aula,
			@FormParam("tipoEvento") String tipoEvento,
			@FormParam("hora") String hora,
			@FormParam("visivel") int visivel
			
			
			
			) {
		Calendario objCalendario = new Calendario();
		logger.info("eventoAction ...");
		Calendario resultado;
		StringUtil stringUtil = new StringUtil();
		
		
		
		
		List<TipoEvento> rsTipoEvento;
		TipoEvento objTipoEvento=  null;
		if (!tipoEvento.isEmpty()) {
			rsTipoEvento = new TipoEventoService().listarkey(Integer.parseInt(tipoEvento));
			objTipoEvento= rsTipoEvento.get(0);
		}
		
		
		if (action.equals("create")) {
			
		
			objCalendario.setAno(Integer.parseInt(ano));
			objCalendario.setDataInicio(stringUtil.converteStringData(dataInicio));
			objCalendario.setDataFim(stringUtil.converteStringData(dataFim));
			
			objCalendario.setTipoEvento(objTipoEvento);
			objCalendario.setDescricao(descricao);
			objCalendario.setEvento(evento);
			
			objCalendario.setFeriado(Integer.parseInt(feriado));
			objCalendario.setAula(Integer.parseInt(aula));
			objCalendario.setHora(hora);
			objCalendario.setVisivel(visivel);

	
			resultado = new CalendarioService().criarCalendario(objCalendario);
			
		}  else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<Calendario> rsCalendario;
			rsCalendario= new CalendarioService().listarkey(id);
			objCalendario= rsCalendario.get(0);

			objCalendario.setAno(Integer.parseInt(ano));
			objCalendario.setDataInicio(stringUtil.converteStringData(dataInicio));
			objCalendario.setDataFim(stringUtil.converteStringData(dataFim));
			
			objCalendario.setTipoEvento(objTipoEvento);
			objCalendario.setDescricao(descricao);
			objCalendario.setEvento(evento);
			
			objCalendario.setFeriado(Integer.parseInt(feriado));
			objCalendario.setAula(Integer.parseInt(aula));
			objCalendario.setHora(hora);
			objCalendario.setVisivel(visivel);

			
			 resultado =  new CalendarioService().atualizarCalendario(objCalendario);
			
		} else {
			return "0";
		}
	    return Integer.toString(resultado.getIdcalendario());
	
		}

}
