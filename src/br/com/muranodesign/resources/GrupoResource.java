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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Periodo;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.Tutoria;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandosa grupos
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("Grupo")
public class GrupoResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(GrupoResource.class.getName());

	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	@GET
	@Produces("application/json")
	public List<Grupo> getGrupo() {
		logger.info("Listar Grupo ...");
		List<Grupo> resultado;
		resultado = new GrupoService().listarTodos();
		logger.info("QTD Grupo : " + resultado.size());
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
	public Grupo getEvento(@PathParam("id") int id) {
		logger.info("Lista Grupo  por id " + id);
		List<Grupo> resultado;
		resultado = new GrupoService().listarkey(id);
		Grupo evento = new Grupo();
		if(!resultado.isEmpty()){
			 evento = resultado.get(0);
		}
		return evento;
	}

	/**
	 * Listar se o grupo existe 
	 * @param id
	 * @return String
	 */
	@Path("Verifica/{id}")
	@GET
	@Produces("application/json")
	public String verifica(@PathParam("id") int id) {
		List<Grupo> resultado;
		resultado = new GrupoService().verifica(id);
		String valor = "";
		if(resultado.get(0).getStatus().equals("0")){
			valor = "0";
		}else if(resultado.get(0).getStatus().equals("1")){
			valor = "1";
		}
		return valor;
	}
	
	/**
	 * Listar grupo por ano e periodo
	 * @param ano
	 * @param periodo
	 * @return list
	 */
	@Path("Teste/{ano}/{periodo}")
	@GET
	@Produces("application/json")
	
	public List<Grupo> teste(@PathParam("ano") String ano,@PathParam("periodo") String periodo){
		
		
		return new GrupoService().listarUltimo(ano, periodo);
	}
	
	/**
	 * Listar grupo por id de tutoria
	 * @param id
	 * @return list
	 */
	@Path("GrupoTutoria/{id}")
	@GET
	@Produces("application/json")
	public List<Grupo> getTutoria(@PathParam("id") int id){
		logger.info("Lista Grupo  por id tutoria" + id);
		 
		List<Grupo> resultado  = new GrupoService().listarTutor(id);
		
		resultado.get(0);

		return resultado;
	}
	
	
	/**
	 * Removes the grupo.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("Delete")
	@POST
	@Produces("text/plain")
	public String removeGrupo(@FormParam("action") String action,
			@FormParam("id") int id) {

		logger.info("Grupo  " + action);
		if ( action.equals("delete")) {
			List<Grupo> resultado;
			resultado = new GrupoService().listarkey(id);
			Grupo res = resultado.get(0);
			List<AlunoVariavel> aluno = new AlunoVariavelService().listaGrupo(id);
			for(int i = 0; i < aluno.size(); i++){
				Grupo g = new Grupo();
				aluno.get(i).setGrupo(g);
			}
			
			new GrupoService().deletarGrupo(res);
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Deletar, criar e alterar grupo
	 * @param action
	 * @param strid
	 * @param lider
	 * @param idProfessor
	 * @param anoEstudo
	 * @param periodo
	 * @param idPerido
	 * @param idGrupo
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(

			@FormParam("action") String action,
			@FormParam("id") String strid,
			@FormParam("lider") String lider,
			@FormParam("idProfessor") int idProfessor,
			@FormParam("anoEstudo") String anoEstudo,
			@FormParam("periodo") String periodo,
			@FormParam("idPeriodo") int idPerido,
			@FormParam("idGrupo") int idGrupo,
			@FormParam("ciclo") String ciclo
			

	) {
		
		Grupo objGrupo = new Grupo();
		logger.info("eventoAction ...");
		Grupo resultado = null;
		Tutoria resultadoTutor;
		
		if(action.equals("delete")){
			 resultado = new GrupoService().listarkey(idGrupo).get(0);
			 resultado.setStatus("1");
			 new GrupoService().atualizarGrupo(resultado);
			 return Integer.toString(idGrupo);
		}
		
		Tutoria tutor = new Tutoria();
		TutoriaService tutorSer = new TutoriaService();
		
		Grupo grupo = new Grupo();

		AnoLetivoService anoLetivoSer = new AnoLetivoService();
		ProfessorFuncionarioService professorSer = new ProfessorFuncionarioService();
	
		PeriodoService periodoSer = new PeriodoService();

		Calendar cal = GregorianCalendar.getInstance(); 
		int anotual = cal.get(Calendar.YEAR);
		
		Aluno objAluno = null;
		if (!lider.isEmpty()) {
			List<Aluno> rsAluno;
			rsAluno = new AlunoService().listarkey(Integer.parseInt(lider));
			if (!rsAluno.isEmpty()) {
				objAluno = rsAluno.get(0);
			}
		}

		
		if (action.equals("create")) {
			
			List<AnoLetivo> listaAno;
			listaAno = anoLetivoSer.listarAnoLetivo(Integer.toString(anotual));
			
			List<ProfessorFuncionario> listaPro;
			listaPro = professorSer.listarkey(idProfessor);
			
			List<Periodo> listaPeriodo;
			listaPeriodo = periodoSer.listarkey(idPerido);
			
			
			if(!tutorSer.listarProfessor(listaPro.get(0).getNome()).isEmpty()){
				tutor = tutorSer.listarProfessor(listaPro.get(0).getNome()).get(0);
				objGrupo.setTutoria(tutor);
			}
			else{
			
			tutor.setAnoLetivo(listaAno.get(0));
			tutor.setTutor(listaPro.get(0));
			tutor.setPeriodo(listaPeriodo.get(0));
			tutor.setTutoria(listaPro.get(0).getNome());
			
			resultadoTutor = tutorSer.criarTutoria(tutor);
			objGrupo.setTutoria(resultadoTutor);
			}
			
			int numResult;
			
			List<Grupo> retornoGrupo = new GrupoService().ListarUltimoCiclo(ciclo,periodo);
			if(retornoGrupo.isEmpty()){
				numResult = 1;
			}else{
				
				grupo = retornoGrupo.get(0);
				String num = grupo.getNomeGrupo().substring(3);
			
				
				
				numResult = 1 + Integer.parseInt(num);
			}
				
			
			objGrupo.setNomeGrupo(ciclo+periodo+Integer.toString(numResult));
			objGrupo.setLider(objAluno);
			objGrupo.setStatus("0");
			objGrupo.setCiclo(ciclo);

			resultado = new GrupoService().criarGrupo(objGrupo);
			
		

		} else if (action.equals("update")) {
			int id = Integer.parseInt(strid);
			
			List<Grupo> rsGrupo;
			rsGrupo = new GrupoService().listarkey(id);
			objGrupo = rsGrupo.get(0);
			
			List<AnoLetivo> listaAno;
			listaAno = anoLetivoSer.listarAnoLetivo(Integer.toString(anotual));
			
			List<ProfessorFuncionario> listaPro;
			listaPro = professorSer.listarkey(idProfessor);
			
			List<Periodo> listaPeriodo;
			listaPeriodo = periodoSer.listarkey(idPerido);
			
			if(!tutorSer.listarProfessor(listaPro.get(0).getNome()).isEmpty()){
				tutor = tutorSer.listarProfessor(listaPro.get(0).getNome()).get(0);
				objGrupo.setTutoria(tutor);
			}
			else{
			
			tutor.setAnoLetivo(listaAno.get(0));
			tutor.setTutor(listaPro.get(0));
			tutor.setPeriodo(listaPeriodo.get(0));
			tutor.setTutoria(listaPro.get(0).getNome());
			
			resultadoTutor = tutorSer.criarTutoria(tutor);
			objGrupo.setTutoria(resultadoTutor);
			}
		
			
			
			objGrupo.setLider(objAluno);
			
			
			resultado = new GrupoService().atualizarGrupo(objGrupo);
			
			
		}else {
			return "0";
		}
		return Integer.toString(resultado.getIdgrupo());

	}
	 
	
	/**
	 * Listar lider grupo
	 * @param lider
	 * @param grupo
	 * @return String
	 */
	@Path("liderGrupo")
	@POST
	@Produces("text/plain")
	public String eventoAction2( @FormParam("lider") String lider,
			@FormParam("grupo") String grupo){
		
		  Grupo objGrupo = new Grupo();
		  Aluno objAluno = new Aluno();
	   
	        	List<Grupo> rsGrupo;
	    		rsGrupo = new GrupoService().listarkey(Integer.parseInt(grupo));
	    		if (!rsGrupo.isEmpty()) {
	    			objGrupo= rsGrupo.get(0);
	    			List<Aluno> rsAluno;
		    		rsAluno = new AlunoService().listarkey(Integer.parseInt(lider));
		    		objAluno= rsAluno.get(0);
	    			objGrupo.setLider(objAluno);
	    			 new GrupoService().atualizarGrupo(objGrupo);
	    			
	    		} else {
	    			logger.info("Não foi possivel completar a operacao");
	    			return "false";
	    			
	    		}
	    		
		return "true";
	}
	


	/**
	 * Listar alunos por id de grupo
	 * @param id
	 * @return List
	 */
	@Path("AlunoGrupo/{id}")
	@GET
	@Produces("application/json")
	public /*List<*/List<AlunoVariavel>/*>*/ alunogrupo(@PathParam("id") int id){
		List<AlunoVariavel> rsAluno;
		/*List<AlunoVariavel> listaTotal = null;
		List<List<AlunoVariavel>> full = new ArrayList<List<AlunoVariavel>>();
		
		int quantidade;*/
		rsAluno = new AlunoVariavelService().listaGrupo(id);
		/*
		quantidade = rsAluno.size();
		
		for(int i = 0; i < quantidade; i ++){
			listaTotal = new AlunoVariavelService().listaAnoEstudo(rsAluno.get(i).getAnoEstudo());
			full.add(listaTotal);
		}
		*/
		return rsAluno;
	}
	
	/**
	 * Listar 
	 * @param id
	 * @return list
	 */
	@Path("TutoriaGrupo/{id}")
	@GET
	@Produces("application/json")
	public List<Grupo> tutoriaGrupo(@PathParam("id") int id){

		List<Tutoria> tutor = new TutoriaService().listarProfessorId(id);
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		 grupos = new GrupoService().listarTutor(tutor.get(0).getIdtutoria());
	
		return grupos;
	}
	

}
