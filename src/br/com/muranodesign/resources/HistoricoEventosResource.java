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

import br.com.muranodesign.business.CalendarioEventosService;
import br.com.muranodesign.business.CalendarioService;
import br.com.muranodesign.business.HistoricoEventosService;
import br.com.muranodesign.business.ImagensService;
import br.com.muranodesign.business.TemplateService;
import br.com.muranodesign.business.TipoEventoService;
import br.com.muranodesign.model.CalendarioEventos;
import br.com.muranodesign.model.Imagens;
import br.com.muranodesign.model.historicoEventos;

/**
 * 
 * @author Kevyn
 *
 */
@Path("HistoricoEventos")
public class HistoricoEventosResource {
	
	private Logger logger = Logger.getLogger(HistoricoEventosResource.class.getName());
	
	/**
	 * Deletar, alterar criar historico de eventos
	 * @param action
	 * @param idHistEventos
	 * @param ano
	 * @param data_inicio
	 * @param data_fim
	 * @param evento
	 * @param descricao
	 * @param feriado
	 * @param aula
	 * @param idcalendario
	 * @param idtipoEvento
	 * @param Imagens
	 * @param idtemplate
	 * @param idCalendarioEventos
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(

			@FormParam("action") String action,
			@FormParam("idHistEventos") int idHistEventos,
			@FormParam("ano") int ano,
			@FormParam("data_inicio") Date data_inicio,
			@FormParam("data_fim") Date data_fim,
			@FormParam("evento") String evento,
			@FormParam("descricao") String descricao,
			@FormParam("feriado") String feriado,
			@FormParam("aula") String aula,
			@FormParam("idcalendario") int idcalendario,
			@FormParam("idtipoEvento") int idtipoEvento,
			@FormParam("Imagens") String Imagens,
			@FormParam("idtemplate") int idtemplate,
			@FormParam("idCalendarioEventos") int idCalendarioEventos

	) {
		
		
		historicoEventos resultado = new historicoEventos();
		Imagens imagens = new Imagens();
		
		if(action.equals("delete")){
			historicoEventos historico = new HistoricoEventosService().listarkey(idHistEventos).get(0);
			resultado = new HistoricoEventosService().deletarhistoricoEventos(historico);
		}
		
		else if(action.equals("create")){
			if(!(idCalendarioEventos == 0)){
				
				String[] img = Imagens.split(";");
				
				CalendarioEventos eventos = new CalendarioEventosService().listarkey(idCalendarioEventos).get(0);
				
				historicoEventos historico = new historicoEventos();
				historico.setEventos(eventos);
			
				historico.setTemplate(new TemplateService().listarkey(idtemplate).get(0));
				
				resultado = new HistoricoEventosService().criarhistoricoEventos(historico);
				
				for(int i =0; i < img.length; i++){
					
					imagens.setImagens(img[i]);
					imagens.setHistorico(resultado);
					
					new ImagensService().criarImagens(imagens);
				}
				
			}else{
				
				String[] img = Imagens.split(";");
				
				historicoEventos historico = new historicoEventos();
				
				historico.setAno(ano);
				historico.setData_inicio(data_inicio);
				historico.setData_fim(data_fim);
				historico.setEvento(evento);
				historico.setDescricao(descricao);
				historico.setFeriado(feriado);
				historico.setAula(aula);
				
				historico.setCalendario(new CalendarioService().listarkey(idcalendario).get(0));
				historico.setTipoEvento(new TipoEventoService().listarkey(idtipoEvento).get(0));
				
				historico.setTemplate(new TemplateService().listarkey(idtemplate).get(0));
				
				resultado = new HistoricoEventosService().criarhistoricoEventos(historico);
				
				for(int i =0; i < img.length; i++){
					
					imagens.setImagens(img[i]);
					imagens.setHistorico(resultado);
					
					new ImagensService().criarImagens(imagens);
					
				}
			}

		}else if(action.equals("update")){
			
				historicoEventos historico = new HistoricoEventosService().listarkey(idHistEventos).get(0);
				
				String[] img = Imagens.split(";");
				
				historico.setAno(ano);
				historico.setAno(ano);
				historico.setData_inicio(data_inicio);
				historico.setData_fim(data_fim);
				historico.setEvento(evento);
				historico.setDescricao(descricao);
				historico.setFeriado(feriado);
				historico.setAula(aula);
				
				if(idtemplate != 0){
					historico.setTemplate(new TemplateService().listarkey(idtemplate).get(0));
				}
				if( idcalendario != 0){
					historico.setCalendario(new CalendarioService().listarkey(idcalendario).get(0));
				}
				if(idtipoEvento != 0){
					historico.setTipoEvento(new TipoEventoService().listarkey(idtipoEvento).get(0));
				}
				
				resultado = new HistoricoEventosService().atualizarhistoricoEventos(historico);
				
				for(int i =0; i < img.length; i++){
					
					imagens.setImagens(img[i]);
					imagens.setHistorico(resultado);
					
					new ImagensService().criarImagens(imagens);
					
				}
			}

			return Integer.toString(resultado.getIdHistEventos());
	}
	
	/**
	 * Listar todos os historicos de eventos
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<historicoEventos> getTemplate() {
		logger.info("Listar historico ...");
		List<historicoEventos> resultado;
		resultado = new HistoricoEventosService().listarTodos();
		logger.info("QTD historico : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Listar historicos de eventos por id
	 * @param id
	 * @return obj historicoEventos
	 */
	@Path("HistoricoEventosid/{id}")
	@GET
	@Produces("application/json")
	public historicoEventos getHistoricoid(@PathParam("id") int id){
		logger.info("Listar historico por id..."+id);
		historicoEventos retorno = new HistoricoEventosService().listarkey(id).get(0);
		return retorno;
	}
	
}
