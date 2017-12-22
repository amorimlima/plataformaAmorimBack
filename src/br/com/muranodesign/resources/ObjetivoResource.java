/**
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AtividadeService;
import br.com.muranodesign.business.AtribuicaoRoteiroExtraService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.PlanejamentoRoteiroService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.Atividade;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Objetivo;
import br.com.muranodesign.model.PlanejamentoRoteiro;
import br.com.muranodesign.model.Roteiro;
import br.com.muranodesign.model.Tutoria;

/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos a objetivo
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("Objetivo")
public class ObjetivoResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ObjetivoResource.class.getName());

	/**
	 * Gets the objetivo.
	 *
	 * @return the objetivo
	 */
	@GET
	@Produces("application/json")
	public List<Objetivo> getObjetivo() {
		logger.debug("Listar Objetivo ...");
		List<Objetivo> resultado;
		resultado = new ObjetivoService().listarTodos();
		logger.debug("QTD Objetivo : " + resultado.size());
		return resultado;
	}

	/**
	 * Gets the objetivo.
	 *
	 * @param id
	 *            the id
	 * @return the objetivo
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Objetivo getObjetivo(@PathParam("id") int id) {

		logger.debug("Lista Evento  por id " + id);
		List<Objetivo> resultado;
		resultado = new ObjetivoService().listarkey(id);
		Objetivo Objetivo = resultado.get(0);

		return Objetivo;

	}
	
	/**
	 * Inativar Objetivo
	 * @param id
	 * @return
	 */
	@Path("InativarObjetivo/{id}")
	@GET
	@Produces("text/plain")
	public String deletarObjetivo(@PathParam("id") int id){
		
		List<Atividade> atividades = new AtividadeService().listarObjetivo(id);
		List<PlanejamentoRoteiro> planejamentos = new PlanejamentoRoteiroService().listarObjetivoTotal(id);
		Objetivo  objetivo = new ObjetivoService().listarkey(id).get(0);
		
		String retorno = "";
		
		if(!atividades.isEmpty() || !planejamentos.isEmpty()){
			objetivo.setAtivo(0);
			new ObjetivoService().atualizarObjetivo(objetivo);
			
			retorno = "inativado";
		}else{
			new ObjetivoService().deletarObjetivo(objetivo);
			
			retorno = "deletado";
		}
		
		return retorno;
	}

	/**
	 * Listar obejtivos por id
	 * 
	 * @param id
	 * @return list
	 */
	@Path("ObjetivoAno/{id}")
	@GET
	@Produces("application/json")
	public List<Objetivo> getObjetivoAno(@PathParam("id") int id) {
		logger.debug("Lista Evento  por id " + id);
		List<Objetivo> obj = new ArrayList<Objetivo>();
		List<Objetivo> total = new ArrayList<Objetivo>();
		List<Roteiro> roteiros = new RoteiroService().listarAno(id);

		int quantidade = roteiros.size();
		for (int i = 0; i < quantidade; i++) {
			obj = new ObjetivoService().listarRoteiro(roteiros.get(i).getIdroteiro());
			total.addAll(obj);
		}
		
		return total;
	}

	/**
	 * Listar Objetivo por Roteiro
	 * 
	 * @param id
	 * @return list
	 */
	@Path("ObjetivoRoteiro/{id}")
	@GET
	@Produces("application/json")
	public List<Objetivo> getObjetivoRoteiro(@PathParam("id") int id) {
		logger.debug("Lista Evento  por id roteiro" + id);
		List<Objetivo> obj = new ObjetivoService().listarRoteiro(id);
		return obj;
	}

	/**
	 * Lista quantidade de objetivos de roteiros
	 * 
	 * @param id
	 * @return long
	 */
	@Path("ObjetivoRoteiroTotal/{id}")
	@GET
	@Produces("application/json")
	public long getObjetivoRoteiroTotal(@PathParam("id") int id) {
		logger.debug("Lista Evento  por id roteiro" + id);
		long r = new ObjetivoService().listarRoteiroTotal(id);
		return r;
	}

	/**
	 * Lista objetivos de roteiros por id
	 * 
	 * @param id
	 * @return long
	 */
	@Path("ObjetivoAluno/{id}")
	@GET
	@Produces("application/json")
	public long getObjetivoAluno(@PathParam("id") int id) {
		logger.debug("Lista Evento  por id roteiro" + id);

		List<AlunoVariavel> aluno = new AlunoVariavelService().listarkey(id);
		List<Roteiro> roteiroAno = new RoteiroService().listarAno(aluno.get(0).getAnoEstudo().getIdanoEstudo());
		List<AtribuicaoRoteiroExtra> atribuicao = new AtribuicaoRoteiroExtraService().listarAluno(aluno.get(0).getAluno());

		long totalObjetivos = 0;

		for (int i = 0; i < roteiroAno.size(); i++) {
			totalObjetivos += new ObjetivoService().listarRoteiroTotal(roteiroAno.get(i).getIdroteiro());
		}
		for (int i = 0; i < atribuicao.size(); i++) {
			totalObjetivos += new ObjetivoService().listarRoteiroTotal(atribuicao.get(i).getRoteiro().getIdroteiro());
		}

		return totalObjetivos;
	}

	/**
	 * Criar grafico
	 * 
	 * @param id
	 * @return list
	 * @return
	 */
	@Path("Grafico/{id}")
	@GET
	@Produces("application/json")
	public List<Long> Grafico(@PathParam("id") int id) {

		List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();

		List<Grupo> grupos = new ArrayList<Grupo>();
		List<Integer> anos = new ArrayList<Integer>();

		List<Tutoria> tutorias = new TutoriaService().listarkey(id);
		List<AlunoVariavel> alunosTOTAL = new ArrayList<AlunoVariavel>();

		List<Objetivo> objetivo = new ArrayList<Objetivo>();

		List<PlanejamentoRoteiro> planejamentoRoteirosentregues = new ArrayList<PlanejamentoRoteiro>();
		List<PlanejamentoRoteiro> planejamentoRoteiroscompletos = new ArrayList<PlanejamentoRoteiro>();

		List<PlanejamentoRoteiro> planejamentoRoteirosentreguesTotal = new ArrayList<PlanejamentoRoteiro>();
		List<PlanejamentoRoteiro> planejamentoRoteiroscompletoTotal = new ArrayList<PlanejamentoRoteiro>();

		List<Long> retorno = new ArrayList<Long>();

		long count = 0;
		
		@SuppressWarnings("unused")
		int t = 0;
		long obj = 0;
		int qtdAluno = 0;

		grupos = new GrupoService().listarTutor(tutorias.get(0).getIdtutoria());

		int qtdGrupo = grupos.size();

		for (int j = 0; j < qtdGrupo; j++) {
			alunos = new AlunoVariavelService().listaGrupo(grupos.get(j)
					.getIdgrupo());
			alunosTOTAL.addAll(alunos);
		}

		if (!alunosTOTAL.isEmpty()) {
			qtdAluno = alunosTOTAL.size();
			for (int k = 0; k < qtdAluno; k++) {
				if (!anos.contains(alunosTOTAL.get(k).getAnoEstudo()
						.getIdanoEstudo())) {
					anos.add(alunosTOTAL.get(k).getAnoEstudo().getIdanoEstudo());
				}
			}

			int qtdAno = anos.size();
			for (int l = 0; l < qtdAno; l++) {

				obj = new ObjetivoService().listarGrafico(anos.get(l));
				count = count + obj;

				objetivo = new ObjetivoService().listarEntregues(anos.get(l));

			}
		}

		for (int k = 0; k < count; k++) {
			planejamentoRoteirosentregues = new PlanejamentoRoteiroService()
					.listarObjetivoPendente(objetivo.get(k).getIdobjetivo());
			if (!planejamentoRoteirosentregues.isEmpty()) {
				planejamentoRoteirosentreguesTotal
						.add(planejamentoRoteirosentregues.get(0));
			}
		}

		for (int k = 0; k < count; k++) {
			planejamentoRoteiroscompletos = new PlanejamentoRoteiroService()
					.listarObjetivoCompleto(objetivo.get(k).getIdobjetivo());
			if (!planejamentoRoteiroscompletos.isEmpty()) {
				planejamentoRoteiroscompletoTotal
						.add(planejamentoRoteiroscompletos.get(0));
			}
		}

		retorno.add(0, count);
		retorno.add(1, (long) planejamentoRoteirosentreguesTotal.size());
		retorno.add(2, (long) planejamentoRoteiroscompletoTotal.size());

		alunosTOTAL.clear();
		anos.clear();
		t++;

		
		return retorno;
	}

	/*
	 * @Path("GraficoEntregues/{id}")
	 * 
	 * @GET
	 * 
	 * @Produces("application/json") public long
	 * GraficoEntregues(@PathParam("id") int id) { List<AlunoVariavel> alunos =
	 * new ArrayList<AlunoVariavel>();
	 * 
	 * List<Grupo> grupos = new ArrayList<Grupo>(); List<Integer> anos = new
	 * ArrayList<Integer>();
	 * 
	 * List<Tutoria> tutorias = new TutoriaService().listarkey(id);
	 * List<AlunoVariavel> alunosTOTAL = new ArrayList<AlunoVariavel>();
	 * List<PlanejamentoRoteiro> planejamentoRoteiros = new
	 * ArrayList<PlanejamentoRoteiro>();
	 * 
	 * List<PlanejamentoRoteiro> planejamentoRoteirosTotal = new
	 * ArrayList<PlanejamentoRoteiro>();
	 * 
	 * 
	 * List<Objetivo> objetivo = new ArrayList<Objetivo>();
	 * 
	 * 
	 * long count = 0; int t = 0; long obj = 0; int qtdAluno = 0;
	 * 
	 * grupos = new GrupoService().listarTutor(tutorias.get(0).getIdtutoria());
	 * 
	 * int qtdGrupo = grupos.size();
	 * 
	 * for (int j = 0; j < qtdGrupo; j++) { alunos = new
	 * AlunoVariavelService().listaGrupo(grupos.get(j).getIdgrupo());
	 * alunosTOTAL.addAll(alunos); }
	 * 
	 * if (!alunosTOTAL.isEmpty()) { qtdAluno = alunosTOTAL.size(); for (int k =
	 * 0; k < qtdAluno; k++) { if
	 * (!anos.contains(alunosTOTAL.get(k).getAnoEstudo().getIdanoEstudo())) {
	 * anos.add(alunosTOTAL.get(k).getAnoEstudo().getIdanoEstudo()); } }
	 * 
	 * int qtdAno = anos.size(); for (int l = 0; l < qtdAno; l++) {
	 * 
	 * obj = new ObjetivoService().listarGrafico(anos.get(l)); objetivo = new
	 * ObjetivoService().listarEntregues(anos.get(l)); count = count + obj; }
	 * 
	 * }
	 * 
	 * for(int k = 0; k < count; k++){ planejamentoRoteiros = new
	 * PlanejamentoRoteiroService
	 * ().listarObjetivoPendente(objetivo.get(k).getIdobjetivo());
	 * if(!planejamentoRoteiros.isEmpty()){
	 * planejamentoRoteirosTotal.add(planejamentoRoteiros.get(0)); } }
	 * 
	 * alunosTOTAL.clear(); anos.clear(); t++;
	 * 
	 *
	 * 
	 * }
	 */

	/**
	 * Removes the objetivo.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	@Path("{id}")
	@GET
	@Produces("text/plain")
	public String removeObjetivo(@PathParam("id") int id) {

		logger.debug("Remover Objetivo " + id);

		List<Objetivo> resultado;
		resultado = new ObjetivoService().listarkey(id);
		Objetivo Objetivo = resultado.get(0);

		new ObjetivoService().deletarObjetivo(Objetivo);

		return "Deletado";

	}

	/**
	 * Lista Objetivo Hash
	 * @param idUsuario
	 * @param idRoteiro
	 * @param idPlanoEstudo
	 * @return
	 */
	@Path("ListaObjetivoHash/{idUsuario}/{idRoteiro}/{idPlanoEstudo}")
	@GET
	@Produces("application/json")
	public List<String> ListaObjetivoHash(
			@PathParam("idUsuario") int idUsuario,
			@PathParam("idRoteiro") int idRoteiro,
			@PathParam("idPlanoEstudo") int idPlanoEstudo) {

		List<String> lista = new ArrayList<String>();
		List<Objetivo> objs = new ObjetivoService().listarRoteiro(idRoteiro);
		List<PlanejamentoRoteiro> plano = new ArrayList<PlanejamentoRoteiro>();
		List<PlanejamentoRoteiro> aux = new ArrayList<PlanejamentoRoteiro>();
		List<String> retorno1 = new ArrayList<String>();

		for (int i = 0; i < objs.size(); i++) {
			aux = new PlanejamentoRoteiroService().listarPendente(idUsuario, objs.get(i).getIdobjetivo());
			if(!aux.isEmpty()){
				plano.addAll(aux);
			}else{
				retorno1.add(objs.get(i).getIdobjetivo().toString());
				retorno1.add(objs.get(i).getNumero().toString());
				retorno1.add("0");
			}
			
			lista.addAll(retorno1);
		}
		for (int j = 0; j <plano.size(); j++) {
			
			retorno1.add(plano.get(j).getObjetivo().getIdobjetivo().toString());
			
			
			retorno1.add(plano.get(j).getObjetivo().getNumero().toString());

			if (plano.isEmpty()) {
				
				retorno1.add("0");
			} else {
				if (plano.get(j).getStatus().equals("1") && plano.get(j).getPlanoEstudo().getIdplanoEstudo() != idPlanoEstudo){
					
					retorno1.add("0");

				}else{
					
					retorno1.add(plano.get(j).getStatus());
				}
			}
			lista.addAll(retorno1);
		}
		

		return retorno1;
	}

	/**
	 * Evento action.
	 *
	 * @param action
	 *            the action
	 * @param strid
	 *            the strid
	 * @param nome
	 *            the nome
	 * @param descricao
	 *            the descricao
	 * @param numero
	 *            the numero
	 * @param roteiro
	 *            the roteiro
	 * @return the string
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(

	@FormParam("action") String action, @FormParam("id") String strid,
			@FormParam("nome") String nome,
			@FormParam("descricao") String descricao,
			@FormParam("roteiro") String roteiro,
			@FormParam("ativo") String ativo,
			@FormParam("numero") int numero

	) {
		Objetivo objObjetivo = new Objetivo();
		logger.info("eventoAction ...");
		Objetivo resultado;

		List<Roteiro> rsRoteiro;
		rsRoteiro = new RoteiroService().listarkey(Integer.parseInt(roteiro));
		Roteiro objRoteiro = rsRoteiro.get(0);

		if (action.equals("create")) {

			objObjetivo.setNome(nome);
			objObjetivo.setDescricao(descricao);
			objObjetivo.setRoteiro(objRoteiro);
			objObjetivo.setNumero(numero);
			objObjetivo.setAtivo(Integer.parseInt(ativo));

			resultado = new ObjetivoService().criarObjetivo(objObjetivo);

		} else if (action.equals("update")) {
			
			int id = Integer.parseInt(strid);
			List<Objetivo> rsObjetivo;
			rsObjetivo = new ObjetivoService().listarkey(id);
			objObjetivo = rsObjetivo.get(0);
			objObjetivo.setIdobjetivo(id);
			objObjetivo.setAtivo(Integer.parseInt(ativo));
			objObjetivo.setNome(nome);
			objObjetivo.setDescricao(descricao);
			objObjetivo.setRoteiro(objRoteiro);

			resultado = new ObjetivoService().atualizarObjetivo(objObjetivo);

		} else {
			return "0";
		}

		return Integer.toString(resultado.getIdobjetivo());
	}

}
