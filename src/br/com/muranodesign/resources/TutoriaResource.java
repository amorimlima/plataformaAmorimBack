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
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Tutoria;

/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos tipo de tutoria
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("Tutoria")
public class TutoriaResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(TutoriaResource.class.getName());

	/**
	 * Gets the tutoria.
	 *
	 * @return the tutoria
	 */
	@GET
	@Produces("application/json")
	public List<Tutoria> getTutoria() {
		logger.info("Listar Tutoria ...");
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarTodos();
		logger.info("QTD Tutoria : " + resultado.size());
		return resultado;
	}

	/**
	 * Listar os tutores por ano 
	 * @param ano
	 * @return String com html
	 */
	@Path("html/{ano}")
	@GET
	@Produces("text/plain")
	public String getProfessorhtml(@PathParam("ano") String ano) {
	 	logger.info("Listar ProfessorFuncionario ...");
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarAno(ano);
		int tamanho = resultado.size();
		String HtmlContent = "";
		for(int i = 0; i < tamanho; i++){
			HtmlContent += "<option value="+resultado.get(i).getTutor().getIdprofessorFuncionario()+">"+resultado.get(i).getTutor().getNome()+"</option>";
		}
		
		return HtmlContent;
	}
	

	
	@Path("ListarDadosPertinentes")
	@GET
	@Produces("application/json")
	public List<Hashtable<String, String>> getListarDadosPertinentes(){
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		List<Tutoria> tutores = new TutoriaService().listarAno(ano);		
		List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
		
		for (Tutoria tutoria : tutores) {
			Hashtable<String, String> hash = new Hashtable<String, String>();
			hash.put("tutoria", tutoria.getTutoria());
			hash.put("idprofessor_funcionario", Integer.toString(tutoria.getTutor().getIdprofessorFuncionario()));
			
			list.add(hash);
		}
		
		return list;
	}
	
	/**
	 * Lista os tutores por ano 
	 * @param ano
	 * @return  String com html
	 */
	@Path("htmlTutoria/{ano}")
	@GET
	@Produces("text/plain")
	public String getTutorhtml(@PathParam("ano") String ano) {
		logger.info("Listar ProfessorFuncionario ...");
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarAno(ano);
		int tamanho = resultado.size();
		String HtmlContent = "";
		for(int i = 0; i < tamanho; i++){
			HtmlContent += "<option value="+resultado.get(i).getIdtutoria()+">"+resultado.get(i).getTutor().getNome()+"</option>";
		}
		
		return HtmlContent;
	}
	
	
	/**
	 * Listar tutoria por ano
	 * @param ano
	 * @return list
	 */
	@Path("TutoriaAno/{ano}")
	@GET
	@Produces("application/json")
	public List<Tutoria> getTutoriaAno(@PathParam("ano") String ano) {
		AnoLetivo anoLetivo = new AnoLetivoService().listarAnoLetivo(ano).get(0);
		List<Tutoria> tutores = new TutoriaService().listarAnoid(anoLetivo.getIdanoLetivo());
		return tutores;
	}
	
	/**
	 * Listar tutoria por periodo
	 * @param id
	 * @return list
	 */
	@Path("TutoriaPerido/{id}")
	@GET
	@Produces("application/json")
	public List<Tutoria> getTutoriaPeriodo(@PathParam("id") int id){
	   return new TutoriaService().listarPeriodo(id);	
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
	public Tutoria getEvento(@PathParam("id") int id) {
		logger.info("Lista Tutoria  por id " + id);
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarkey(id);
		Tutoria evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Lista alunos por tutor
	 * @param id
	 * @return String com html
	 */
	@Path("AlunoHtml/{id}")
	@GET
	@Produces("application/json")
	public String getAlunosHtml(@PathParam("id") int id){
		logger.info("Lista alunos Tutoria  por id " + id);
		List<Grupo> grupos;
		List<AlunoVariavel> aluno = new ArrayList<AlunoVariavel>();
		List<Tutoria> tutor = new TutoriaService().listarProfessorId(id);
		
		int qtdGrupo = 0;
		String html = "";
		
		grupos = new GrupoService().listarTutor(tutor.get(0).getIdtutoria());
		qtdGrupo = grupos.size();
		
		for(int i = 0; i < qtdGrupo; i++){
			aluno = new AlunoVariavelService().listaGrupo(grupos.get(i).getIdgrupo());
	        if(!aluno.isEmpty()){
	        	for(int k =0; k < aluno.size(); k++){
		        String nome = aluno.get(k).getAluno().getNome();
				int idaluno = aluno.get(k).getAluno().getIdAluno();
				String anoAluno = aluno.get(k).getAnoEstudo().getAno();
				String PeriodoAluno = aluno.get(k).getPeriodo().getPeriodo();
				
				html +=  "<tr id="+"aluno"+" onClick="+"editarAluno"+"("+idaluno+")"+">"+
						 "<td class="+"alunoNome"+">"+nome+"</td>"+
						 "<td class="+"alunoAno"+">"+anoAluno+"º"+"</td>"+
						 "<td class="+"alunoPeriodo"+">"+PeriodoAluno+"</td>"+
						 "</tr>";
	        	}
	        }
		}
		
		
		return html;
	}
	
	/**
	 * Lista alunos por tutor
	 * @param id
	 * @return
	 */
	@Path("AlunoId/{id}")
	@GET
	@Produces("application/json")
	public List<Integer> getAlunosId(@PathParam("id") int id){
		logger.info("Lista alunos Tutoria  por id " + id);
		List<Grupo> grupos;
		List<AlunoVariavel> aluno = new ArrayList<AlunoVariavel>();
		List<Tutoria> tutor = new TutoriaService().listarProfessorId(id);
		List<Integer> ids = new ArrayList<Integer>();
		int qtdGrupo = 0;
		
		
		grupos = new GrupoService().listarTutor(tutor.get(0).getIdtutoria());
		qtdGrupo = grupos.size();
		
		for(int i = 0; i < qtdGrupo; i++){
			aluno = new AlunoVariavelService().listaGrupo(grupos.get(i).getIdgrupo());
	        if(!aluno.isEmpty()){
	        	for(int k =0; k < aluno.size(); k++){
				int idaluno = aluno.get(k).getAluno().getIdAluno();
				ids.add(idaluno);
				
	        	}
	        }
		}
		
		return ids;
	}
	
	
	
	/**
	 * Listar tutoria por id e ano
	 * @param id
	 * @param ano
	 * @return
	 */
	@Path("Professor/{id}/{ano}")
	@GET
	@Produces("application/json")
	public List<Tutoria> getProfessor(@PathParam("id") int id,@PathParam("ano") String ano){
		logger.info("Lista Tutoria  por id Professor" + id);
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarProfessorId(id,ano);
		
		return resultado;
	}
	
	/**
	 * Removes the tutoria.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeTutoria(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("Tutoria  " + action);
		if ( action.equals("delete")) {
			List<Tutoria> resultado;
			resultado = new TutoriaService().listarkey(id);
			Tutoria evento = resultado.get(0);
			new TutoriaService().deletarTutoria(evento);
			return "true";
		} else {
			return "false";
		}

	}
	
	@GET	
	@Path("TutoriaGrupos/{idtutoria}")
	@Produces("application/json")
	public List<Hashtable<String, String>> listarGruposProfessor(@PathParam("idtutoria") int idtutoria)
	{
		logger.info("Tutoria Professor: " + idtutoria);
		
		List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
		
		List<Grupo> grupos = new GrupoService().listarTutor(idtutoria);
		for (Grupo grupo : grupos) {
			Hashtable<String, String> objeto = new Hashtable<String, String>();
			objeto.put("GrupoId", Integer.toString(grupo.getIdgrupo()));
			objeto.put("NomeGrupo", grupo.getNomeGrupo());
			List<AlunoVariavel> alunos = new AlunoVariavelService().listaGrupo(grupo.getIdgrupo());
			String nomeAlunos = "";
			for (int i = 0; i < alunos.size(); i++) {
				nomeAlunos += alunos.get(i).getAluno().getNome();
				if (i < alunos.size() - 1)
					nomeAlunos += ", ";
			}
			objeto.put("alunos", nomeAlunos);
			objeto.put("nomeTutor", grupo.getTutoria().getTutoria());
			if (nomeAlunos.length() > 0)
				list.add(objeto);
			else
			{
				grupo.setStatus("1");
				new GrupoService().atualizarGrupo(grupo);
			}
		}
		
		return list;
		
	}
	
	@GET	
	@Path("ListarTodas/")
	@Produces("application/json")
	public List<Tutoria> listarTodas(){
		logger.info("Listar Tutoria ...");
		List<Tutoria> resultado;
		resultado = new TutoriaService().listarTodasAnos();
		logger.info("QTD Tutoria : " + resultado.size());
		return resultado;
	}

}
