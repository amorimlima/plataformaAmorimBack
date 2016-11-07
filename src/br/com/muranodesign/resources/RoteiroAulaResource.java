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

import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.RoteiroAulaService;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.OficinaProfessor;
import br.com.muranodesign.model.RoteiroAula;


@Path("RoteiroAula")
public class RoteiroAulaResource {
	
	private Logger logger = Logger.getLogger(RoteiroAulaResource.class.getName());
	
	/**
	 * Criar, deletar e atualizar
	 * @param action
	 * @param id
	 * @param idOficinaProfessor
	 * @param Descricao
	 * @param roteiro
	 * @return
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idOficinaProfessor") int idOficinaProfessor,
			@FormParam("Descricao") String Descricao,
			@FormParam("roteiro") String roteiro){
		
		RoteiroAula resultado = new RoteiroAula();
		
		if(action.equals("delete")){
			resultado = new  RoteiroAulaService().deletarRoteiroAula(new RoteiroAulaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			RoteiroAula roteiroAula = new RoteiroAula();
			
			roteiroAula.setDescricao(Descricao);
			roteiroAula.setRoteiro(roteiro);
			roteiroAula.setOficinaprofessor(new OficinaProfessorService().listarkey(idOficinaProfessor).get(0));
			roteiroAula.setStatus(0);
			
			resultado = new RoteiroAulaService().criarRoteiroAula(roteiroAula);
			
		}else if(action.equals("update")){
			RoteiroAula roteiroAula = new RoteiroAulaService().listarkey(id).get(0);
			
			roteiroAula.setDescricao(Descricao);
			roteiroAula.setRoteiro(roteiro);
			roteiroAula.setOficinaprofessor(new OficinaProfessorService().listarkey(idOficinaProfessor).get(0));
			roteiroAula.setStatus(0);
			
			resultado = new RoteiroAulaService().atualizarRoteiroAula(roteiroAula);
		}
		
		return Integer.toString(resultado.getIdroteiro_aula());
	}
	

	/**
	 * Roteiro Aula
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<RoteiroAula> getRoteiroAula() {
		logger.debug("Listar RoteiroAula ...");
		List<RoteiroAula> resultado;
		 resultado = new RoteiroAulaService().listarTodos();
		 logger.debug("QTD RoteiroAula : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Listar Por Oficina Professor
	 * @param id
	 * @param letra
	 * @return
	 */
	@Path("ListarPorOficinaProfessor/{id}/{letra}")
	@GET
	@Produces("application/json")
	public List<RoteiroAula> getListarPorOficinaProfessor(@PathParam("id") int id,@PathParam("letra") String letra) {
		List<RoteiroAula> retorno = new ArrayList<RoteiroAula>();
		
		if(letra.equals("-")){
			retorno = new RoteiroAulaService().listarOficinaProfessor(id);
		}else{
			retorno = new RoteiroAulaService().listarOficinaProfessorLike(id, letra);
		}
		return retorno;
	}
	
	/**
	 * Listar Não oficina Professor
	 * @param id
	 * @param letra
	 * @return
	 */
	@Path("ListarNaoOficinaProfessor/{id}/{letra}")
	@GET
	@Produces("application/json")
	public List<RoteiroAula> getListarNaoOficinaProfessor(@PathParam("id") int id,@PathParam("letra") String letra) {
		List<RoteiroAula> retorno = new ArrayList<RoteiroAula>();
		
		if(letra.equals("-")){
			retorno = new RoteiroAulaService().listarNaoOficinaProfessor(id);
		}else{
			retorno = new  RoteiroAulaService().listarNaoOficinaProfessorLike(id, letra);
		}
		
		return retorno;
	}
	
	/**
	 * Status
	 * @param id
	 */
	@Path("status")
	@POST
	@Produces("text/plain")
	public void alterarStatus(@FormParam("id") int id){
		RoteiroAula roteiro = new RoteiroAulaService().listarkey(id).get(0);
		
		if(roteiro.getStatus() == 0){
			roteiro.setStatus(1);
		}else if(roteiro.getStatus() == 1){
			roteiro.setStatus(0);
		}
		
		
		new RoteiroAulaService().atualizarRoteiroAula(roteiro);
	}
	
	/**
	 * Lista like Roteiro Aula
	 * @param idOficinaProfessor
	 * @param letras
	 * @return
	 */
	@Path("ListaLikeRoteiroAula/{idOficinaProfessor}/{letras}") 
	@GET
	@Produces("application/json")
	public List<RoteiroAula> ListaLikeRoteiro(@PathParam("idOficinaProfessor") int idOficinaProfessor,@PathParam("letras") String letras){
		List<RoteiroAula> like = new RoteiroAulaService().listarLike(idOficinaProfessor, letras);
		if(!like.isEmpty()){
			return like;
		}else{
			return new RoteiroAulaService().listarTodos();
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public RoteiroAula getRoteiroAula(@PathParam("id") int id) {

		List<RoteiroAula> resultado;
		resultado = new RoteiroAulaService().listarkey(id);
		RoteiroAula sessao = new RoteiroAula();

		if (!resultado.isEmpty()) {
			sessao = resultado.get(0);
		}
		return sessao;

	}
	
	/**
	 * Listar Like Roteiro
	 * @param letra
	 * @return
	 */
	@Path("ListarLikeRoteiro/{letra}")
	@GET
	@Produces("application/json")
	public String ListarLikeRoteiro(@PathParam("letra") String letra){
		String roteiroHTML = "";
		
		List<RoteiroAula> roteiros = new RoteiroAulaService().listarLikeRoteiro(letra);
	
		for (RoteiroAula roteiroAula : roteiros) {
			  roteiroHTML += "<div class="+"item"+">"+
				     "<div class="+"titulo_roteiro"+">"+roteiroAula.getRoteiro()+"</div>"+ 
				     "<span class="+"editar "+"onclick="+"exibirRoteiro("+roteiroAula.getIdroteiro_aula()+")"+"></span>"+
				     "<span class="+"excluir "+"onclick=excluirRoreiroAula("+roteiroAula.getIdroteiro_aula()+")"+"></span>"+
				     "</div>";
		}
	
		
		return roteiroHTML; 
	}
	
	/**
	 * Listar Oficina Ciclo
	 * @param idOficina
	 * @param idCiclo
	 * @param idProfessor
	 * @return
	 */
	@Path("ListarOficinaCiclo/{idOficina}/{idCiclo}/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RoteiroAula> ListarOficinaCiclo(@PathParam("idOficina") int idOficina,@PathParam("idCiclo") int idCiclo,@PathParam("idProfessor") int idProfessor){
		
		List<OficinaProfessor> oficinasProfessores = new ArrayList<OficinaProfessor>();
		List<Oficina> oficinas = new ArrayList<Oficina>();
		
		if(!(idCiclo == 0)){
			oficinas = new OficinaService().listarIdCiclo(idOficina, idCiclo);
		}else{
			oficinas = new OficinaService().listarIdOficina(idOficina);
		}
		
		
		
		List<RoteiroAula> roteiros = new ArrayList<RoteiroAula>();
		
		for (Oficina oficina : oficinas) {
			oficinasProfessores.addAll(new OficinaProfessorService().listarOficina(oficina.getIdoficina(),idProfessor));
		}
		
		for (OficinaProfessor oficinaProfessor : oficinasProfessores) {
			roteiros.addAll(new RoteiroAulaService().listarOficinaProfessor(oficinaProfessor.getIdoficina_professor()));
		}
		
		return roteiros;
		
	}
	
}
