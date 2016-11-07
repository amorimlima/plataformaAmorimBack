package br.com.muranodesign.resources;

import java.awt.TextArea;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ForumGeralRespostaService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.ForumGeralResposta;

/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos a  ForumGeralResposta
 * @author Kevyn
 *
 */
@Path("ForumGeralResposta")
public class ForumGeralRespostaResource {

	/**
	 * logger
	 */
	private Logger logger = Logger.getLogger(ForumGeralRespostaResource.class.getName());
	
	/**
	 * Listar todos os fóruns gerais de resposta
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<ForumGeralResposta> getForumGeralResposta() {
		logger.debug("Listar Alunos ...");
		List<ForumGeralResposta> resultado;
		resultado = new ForumGeralRespostaService().listarTodos();
		logger.debug("QTD Sessao : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Listar forum geral de resposta por id
	 * @param id
	 * @return obj forum geral resposta
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public ForumGeralResposta getForumGeralResposta(@PathParam("id") int id) {

		List<ForumGeralResposta> resultado;
		resultado = new ForumGeralRespostaService().listarkey(id);
		ForumGeralResposta sessao = null;

		if (!resultado.isEmpty()) {
			sessao = resultado.get(0);
		}
		return sessao;

	}
	
	/**
	 * Deletar forum geral de resposta
	 * @param action
	 * @param id
	 * @return String
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeForumGeralResposta(@PathParam("action") String action, 	@PathParam("id") int id) {
		logger.info("SessaoGeralForum  " + action);
		if ( action.equals("delete")) {
			List<ForumGeralResposta> resultado;
			resultado = new ForumGeralRespostaService().listarkey(id);
			ForumGeralResposta res = resultado.get(0);
			new ForumGeralRespostaService().deletarForumQuestao(res);
			return "true";
		}else{
			return "false";
		}
	}
	
	/**
	 *  Criar e alterar forum geral de resposta
	 * @param action
	 * @param id
	 * @param questao
	 * @param resposta
	 * @param anexo
	 * @param data
	 * @param idAluno
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("questao") TextArea questao,
			@FormParam("resposta") TextArea resposta,
			@FormParam("anexo") String anexo,
			@FormParam("data") Date data,
			@FormParam("aluno") int idAluno
			
			){
		
		ForumGeralResposta forumResposta = new ForumGeralResposta();
		logger.info("eventoAction ...");
		ForumGeralResposta resultado;
		
		if(action.equals("create")){
			forumResposta.setQuestao(questao);
			forumResposta.setResposta(resposta);
			forumResposta.setAnexo(anexo);
			forumResposta.setData(data);
			forumResposta.setUsuario(new UsuarioService().listarkey(idAluno).get(0));
			
			resultado = new ForumGeralRespostaService().criarForumQuestao(forumResposta);
		}else if(action.equals("update")){
			forumResposta.setIdForumResposta(id);
			forumResposta.setQuestao(questao);
			forumResposta.setResposta(resposta);
			forumResposta.setAnexo(anexo);
			forumResposta.setData(data);
			forumResposta.setUsuario(new UsuarioService().listarkey(idAluno).get(0));
			
			resultado = new ForumGeralRespostaService().criarForumQuestao(forumResposta);
		}else {
			return "0";
		}
		
		return Integer.toString(resultado.getIdForumResposta());
	}
}
