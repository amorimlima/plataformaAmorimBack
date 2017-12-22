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
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.AtribuicaoRoteiroExtraService;
import br.com.muranodesign.business.ChamadaService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.business.PlanejamentoRoteiroService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Periodo;
import br.com.muranodesign.model.PlanejamentoRoteiro;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.Roteiro;
import br.com.muranodesign.model.Tutoria;
import br.com.muranodesign.util.Upload;


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
	 * @throws JSONException 
	 */
	@Path("Teste")
	@GET
	@Produces("application/json")
	public String teste(){
		Upload upload = new Upload();
		upload.deleteFile("C://Users/murano/Desktop/teste.png");
		return "ok";
	}
	
	@Path("TutoriaDados/{idprofessor}")
	@GET
	@Produces("application/json")
	public List<Object> tutoriaDados(@PathParam("idprofessor") int id)
	{
		List<Object> resultado = new ArrayList<Object>();
		
		List<Tutoria> tutorias = new TutoriaService().listarProfessorId(id);
		for (Tutoria tutoria : tutorias) {
			int tutoriaId = tutoria.getIdtutoria();
			List<Grupo> grupos = new GrupoService().listarTutor(tutoriaId);
			for (Grupo grupo : grupos) {
				
				List<AlunoVariavel> alunosGrupo = new AlunoVariavelService().listaGrupo(grupo.getIdgrupo());
				
				if (alunosGrupo.size() > 0)
				{
					Hashtable<String, Object> grupoDados = new Hashtable<String, Object>();
					
					grupoDados.put("grupoNome", grupo.getNomeGrupo());
					grupoDados.put("grupoId", grupo.getIdgrupo());
					
					if (grupo.getLider() != null)
						grupoDados.put("lider", grupo.getLider());
					else
						grupoDados.put("lider", "null");
					
					List<Object> alunos = new ArrayList<Object>();
					
					for (AlunoVariavel alunoVariavel : alunosGrupo) {
						
						Hashtable<String, Object> alunoDados = new Hashtable<String, Object>();
						
						alunoDados.put("nome", alunoVariavel.getAluno().getNome());
						alunoDados.put("idAluno", alunoVariavel.getAluno().getIdAluno());
						if (alunoVariavel.getAluno().getFotoAluno() != null)
							alunoDados.put("foto", alunoVariavel.getAluno().getFotoAluno());
						else
							alunoDados.put("foto", "null");
						alunoDados.put("faltas", new ChamadaService().countFaltas(alunoVariavel.getAluno().getIdAluno()));
						alunoDados.put("presencas", new ChamadaService().listaPrecenca(alunoVariavel.getAluno(), 1).size());
						
						float totalObjetivosAno = 0;
						float totalObjetivosPendentes = 0;
						float totalObjetivosFuturos = 0;
						
						float ObjetivosAnoCompletos = 0;
						float ObjetivosPendentesCompletos = 0;
						float ObjetivosFuturosCompletos = 0;
						
						float ObjetivosAnoCorrigidos = 0;
						float ObjetivosPendentesCorrigidos = 0;
						float ObjetivosFuturosCorrigidos = 0;
						
						try{
							int anoAluno = Integer.parseInt(alunoVariavel.getAnoEstudo().getAno());
							
							List<Roteiro> roteirosAno = new RoteiroService().listarAno(alunoVariavel.getAnoEstudo().getIdanoEstudo());
							for (Roteiro roteiro : roteirosAno) {
								totalObjetivosAno += new ObjetivoService().listarRoteiroTotal(roteiro.getIdroteiro());
							}
							List<AtribuicaoRoteiroExtra> roteirosPendentes = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(alunoVariavel.getAluno().getIdAluno()).get(0), new AnoLetivoService().listarkey(alunoVariavel.getAnoLetivo().getIdanoLetivo()).get(0));
							for (AtribuicaoRoteiroExtra atribuicaoRoteiroExtra : roteirosPendentes) {
								
								int anoRoteiro = Integer.parseInt(atribuicaoRoteiroExtra.getRoteiro().getAnoEstudo().getAno());
								
								if (anoRoteiro < anoAluno)
									totalObjetivosPendentes += new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
								else
									totalObjetivosFuturos += new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
							}
							
							List<PlanejamentoRoteiro> planejamentosCompletos = new PlanejamentoRoteiroService().listarAlunoCompletosLista(alunoVariavel.getAluno().getIdAluno());
							for (PlanejamentoRoteiro planejamentoRoteiro : planejamentosCompletos) {
								int planejamentoRoteiroAno = Integer.parseInt(planejamentoRoteiro.getObjetivo().getRoteiro().getAnoEstudo().getAno());
								if (planejamentoRoteiroAno < anoAluno)
									ObjetivosPendentesCompletos++;
								else if (planejamentoRoteiroAno > anoAluno)
									ObjetivosFuturosCompletos++;
								else
									ObjetivosAnoCompletos++;
							}
							
							List<PlanejamentoRoteiro> planejamentosCorrigidos = new PlanejamentoRoteiroService().listarAlunoCorrigidosLista(alunoVariavel.getAluno().getIdAluno());
							for (PlanejamentoRoteiro planejamentoRoteiro : planejamentosCorrigidos) {
								int planejamentoRoteiroAno = Integer.parseInt(planejamentoRoteiro.getObjetivo().getRoteiro().getAnoEstudo().getAno());
								if (planejamentoRoteiroAno < anoAluno)
									ObjetivosPendentesCorrigidos++;
								else if (planejamentoRoteiroAno > anoAluno)
									ObjetivosFuturosCorrigidos++;
								else
									ObjetivosAnoCorrigidos++;
							}
						}
						
						catch(Exception e){
							List<AtribuicaoRoteiroExtra> roteirosPendentes = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(alunoVariavel.getAluno().getIdAluno()).get(0), new AnoLetivoService().listarkey(alunoVariavel.getAnoLetivo().getIdanoLetivo()).get(0));
							for (AtribuicaoRoteiroExtra atribuicaoRoteiroExtra : roteirosPendentes) {
								totalObjetivosAno += new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
							}
							
							List<PlanejamentoRoteiro> planejamentosCompletos = new PlanejamentoRoteiroService().listarAlunoCompletosLista(alunoVariavel.getAluno().getIdAluno());
							List<PlanejamentoRoteiro> planejamentosCorrigidos = new PlanejamentoRoteiroService().listarAlunoCorrigidosLista(alunoVariavel.getAluno().getIdAluno());
							ObjetivosAnoCompletos =  planejamentosCompletos.size();
							ObjetivosAnoCorrigidos =  planejamentosCorrigidos.size();
						}
						
						Hashtable<String, Float> objetivosAnoAtual = new Hashtable<String, Float>();
						Hashtable<String, Float> objetivosPendentes = new Hashtable<String, Float>();
						Hashtable<String, Float> objetivosFuturo = new Hashtable<String, Float>();
						
						if (totalObjetivosAno > 0)
						{
							objetivosAnoAtual.put("completos", (ObjetivosAnoCompletos + ObjetivosAnoCorrigidos) / totalObjetivosAno);
							objetivosAnoAtual.put("corrigidos", ObjetivosAnoCorrigidos / totalObjetivosAno);
						}
						
						else
						{
							objetivosAnoAtual.put("completos", 0.0f);
							objetivosAnoAtual.put("corrigidos", 0.0f);
						}
						
						if (totalObjetivosPendentes > 0)
						{
							objetivosPendentes.put("completos", (ObjetivosPendentesCompletos + ObjetivosPendentesCorrigidos) / totalObjetivosPendentes );
							objetivosPendentes.put("corrigidos", ObjetivosPendentesCorrigidos / totalObjetivosPendentes);
						}
						else
						{
							objetivosPendentes.put("completos", 0.0f);
							objetivosPendentes.put("corrigidos", 0.0f);
						}
						
						if (totalObjetivosFuturos > 0)
						{
							objetivosFuturo.put("completos", (ObjetivosFuturosCompletos + ObjetivosFuturosCorrigidos) / totalObjetivosFuturos);
							objetivosFuturo.put("corrigidos", ObjetivosFuturosCorrigidos / totalObjetivosFuturos);
						}
						else
						{
							objetivosFuturo.put("completos", 0.0f);
							objetivosFuturo.put("corrigidos", 0.0f);
						}
						
						
						alunoDados.put("objetivosAnoAtual", objetivosAnoAtual);
						alunoDados.put("objetivosPendentes", objetivosPendentes);
						alunoDados.put("objetivosFuturo", objetivosFuturo);
									
						alunos.add(alunoDados);
					}
					
					grupoDados.put("grupoAlunos", alunos);
					resultado.add(grupoDados);
				}
			}
		}
				
		return resultado;
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
			List<AlunoVariavel> alunos = new AlunoVariavelService().listaGrupo(id);
			for (AlunoVariavel alunoVariavel : alunos) {
				alunoVariavel.setGrupo(null);
				new AlunoVariavelService().atualizarAlunoVariavel(alunoVariavel);
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
		
//		if(action.equals("delete")){
//			 resultado = new GrupoService().listarkey(idGrupo).get(0);
//			 resultado.setStatus("1");
//			 new GrupoService().atualizarGrupo(resultado);
//			 return Integer.toString(idGrupo);
//		}
		
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
			objGrupo.setPeriodo(listaPeriodo.get(0));

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
	
	@Path("GrupoAluno/{nomeAluno}")
	@GET
	@Produces("application/json")
	public List<Grupo> getAlunoGrupo(@PathParam("nomeAluno") String nomeAluno){
		
		logger.debug("Buscando Grupo por nome Aluno: " + nomeAluno);
		
		List<Aluno> alunos = new AlunoService().listAllLike(nomeAluno);
		List<Grupo> grupos = new ArrayList<Grupo>();
		if (!alunos.isEmpty())
		{
			for (Aluno aluno : alunos) {
				List<AlunoVariavel> alunoVariavel = new AlunoVariavelService().listaAluno(aluno.getIdAluno());
				if (!alunoVariavel.isEmpty() &&
					alunoVariavel.get(0).getGrupo() != null &&
					!grupos.contains(alunoVariavel.get(0).getGrupo()))
						grupos.add(alunoVariavel.get(0).getGrupo());
			}
		}
		
		logger.debug("QTD Grupos: " + grupos.size());
		
		return grupos;	
		
	}
	

}
