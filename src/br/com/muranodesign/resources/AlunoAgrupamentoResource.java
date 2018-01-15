package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AlunoAgrupamentoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.model.AlunoAgrupamento;

/**
 * 
 * @author Kevyn
 *
 */
@Path("AlunoAgrupamento")
public class AlunoAgrupamentoResource {
	
	private Logger logger = Logger.getLogger(AlunoAgrupamentoResource.class.getName());
	
	/**
	 * Deletar, alterar e criar agrupamentos de alunos
	 * @param action
	 * @param id
	 * @param Alunos
	 * @param idAgrupamento
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("Aluno") int Aluno,
			@FormParam("idAgrupamento") int idAgrupamento){
		
		AlunoAgrupamento resultado = new AlunoAgrupamento();
		
		
		if(action.equals("delete")){
			if (id != 0)
				resultado = new AlunoAgrupamentoService().deletarAlunoAgrupamento(new AlunoAgrupamentoService().listarkey(id).get(0));
			else
				resultado = new AlunoAgrupamentoService().deletarAlunoAgrupamento(new AlunoAgrupamentoService().listarAlunoAgrupamento(Aluno, idAgrupamento).get(0));
		}
		else if(action.equals("create")){
			AlunoAgrupamento agrupamento = new AlunoAgrupamento();
			
			List<AlunoAgrupamento> antigo = new AlunoAgrupamentoService().listarAlunoAgrupamento(Aluno, idAgrupamento);
			
			if(antigo.isEmpty()){
				agrupamento.setAluno(new AlunoVariavelService().listarkey(Aluno).get(0));
				agrupamento.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
					
				resultado = new AlunoAgrupamentoService().criarAlunoAgrupamento(agrupamento);
			}
			
		}else if(action.equals("update")){
			AlunoAgrupamento agrupamento = new AlunoAgrupamentoService().listarkey(id).get(0);
			
			List<AlunoAgrupamento> antigo = new AlunoAgrupamentoService().listarAlunoAgrupamento(Aluno, idAgrupamento);
			
			if(antigo.isEmpty()){
				agrupamento.setAluno(new AlunoVariavelService().listarkey(Aluno).get(0));
				agrupamento.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
				
				resultado = new AlunoAgrupamentoService().atualizarAlunoAgrupamento(agrupamento);
			}
		}
		
		return Integer.toString(resultado.getIdaluno_agrupamento());
	}
	
	/**
	 * Lista agrupamento de alunos por id
	 * @param id
	 * @return list
	 */
	@Path("listarAgrupamento/{id}")
	@GET
	@Produces("application/json")
	public List<AlunoAgrupamento> getlistarAgrupamento(@PathParam("id") int id){
		logger.debug("Listar Alunos Agrupamento ...");
		List<AlunoAgrupamento> resultado;
		 resultado = new AlunoAgrupamentoService().listarAgrupamento(id);
		 logger.debug("QTD Alunos Agrupamento : " +  resultado.size());
		return resultado;
	}
	
	
	
	/**
	 * Lista agrupamento de alunos por id de aluno e id de agrupamento
	 * @param idAluno
	 * @param idAgrupamento
	 * @return list
	 */
	@Path("listarAlunoAgrupamento/{idAluno}/{idAgrupamento}")
	@GET
	@Produces("application/json")
	public List<AlunoAgrupamento> getlistarAlunoAgrupamento(@PathParam("idAluno") int idAluno, @PathParam("idAgrupamento") int idAgrupamento){
		logger.debug("Listar Alunos Agrupamento ...");
		List<AlunoAgrupamento> resultado;
		 resultado = new AlunoAgrupamentoService().listarAlunoAgrupamento(idAluno, idAgrupamento);
		 logger.debug("QTD Alunos Agrupamento : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Lista todos os agrupamentos de alunos
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<AlunoAgrupamento> getAlunoAgrupamento() {
		logger.debug("Listar AlunoAgrupamento ...");
		List<AlunoAgrupamento> resultado;
		resultado = new AlunoAgrupamentoService().listarTodos();
		logger.debug("QTD AlunoAgrupamento : " +resultado.size());
		return resultado;
	}
	
	@Path("AgrupamentosAlunoLike/{nome}")
	@GET
	@Produces("application/json")
	public List<AlunoAgrupamento> getAlunoAgrupamentoLike(@PathParam("nome") String nome){
		logger.debug("Listar AlunoAgrupamento ...");
		List<AlunoAgrupamento> resultado;
		resultado = new AlunoAgrupamentoService().listarLikeAluno(nome);
		logger.debug("QTD AlunoAgrupamento : " +resultado.size());
		return resultado;
	}

}
