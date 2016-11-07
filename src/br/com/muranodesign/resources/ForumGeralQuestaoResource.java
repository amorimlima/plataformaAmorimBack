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

import br.com.muranodesign.business.ForumGeralQuestaoService;
import br.com.muranodesign.business.SessaoForumGeralService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.ForumGeralQuestao;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos a ForumGeralQuestao
 * @author Kevyn
 *
 */
@Path("ForumGeralQuestao")
public class ForumGeralQuestaoResource {

	/**
	 * logger
	 */
	private Logger logger = Logger.getLogger(ForumGeralQuestaoResource.class.getName());
	
	/**
	 * Listar todos os fóruns gerais de questao
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<ForumGeralQuestao> getForumQuestao() {
		logger.debug("Listar Alunos ...");
		List<ForumGeralQuestao> resultado;
		resultado = new ForumGeralQuestaoService().listarTodos();
		logger.debug("QTD Sessao : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Listar forum geral de questao por id
	 * @param id
	 * @return list
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public ForumGeralQuestao getForumGeralQuestao(@PathParam("id") int id) {

		List<ForumGeralQuestao> resultado;
		resultado = new ForumGeralQuestaoService().listarkey(id);
		ForumGeralQuestao sessao = null;

		if (!resultado.isEmpty()) {
			sessao = resultado.get(0);
		}
		return sessao;

	}
	
	/**
	 * Deletar forum geral de questao
	 * @param action
	 * @param id
	 * @return String
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeForumGeralQuestao(@PathParam("action") String action, 	@PathParam("id") int id) {
		logger.info("SessaoGeralForum  " + action);
		if ( action.equals("delete")) {
			List<ForumGeralQuestao> resultado;
			resultado = new ForumGeralQuestaoService().listarkey(id);
			ForumGeralQuestao res = resultado.get(0);
			new ForumGeralQuestaoService().deletarForumQuestao(res);
			return "true";
		}else{
			return "false";
		}
	}
	
	/**
	 * Criar e alterar forum geral de questao
	 * @param action
	 * @param id
	 * @param questao
	 * @param assunto
	 * @param anexo
	 * @param data
	 * @param idSessao
	 * @param idAluno
	 * @return  id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("questao") TextArea questao,
			@FormParam("assunto") String assunto,
			@FormParam("anexo") String anexo,
			@FormParam("data") Date data,
			@FormParam("sessao") int idSessao,
			@FormParam("aluno") int idAluno
			
			){
		
		ForumGeralQuestao forumQuestao = new ForumGeralQuestao();
		logger.info("eventoAction ...");
		ForumGeralQuestao resultado;
		
		if(action.equals("create")){
			forumQuestao.setQuestao(questao);
			forumQuestao.setAssunto(assunto);
			forumQuestao.setAnexo(anexo);
			forumQuestao.setData(data);
			forumQuestao.setSessaoForum(new SessaoForumGeralService().listarkey(idSessao).get(0));
			forumQuestao.setUsuario(new UsuarioService().listarkey(idAluno).get(0));
			
			resultado = new ForumGeralQuestaoService().criarForumQuestao(forumQuestao);
			
		}else if(action.equals("update")){
			forumQuestao.setIdForumQuestao(id);
			forumQuestao.setQuestao(questao);
			forumQuestao.setAssunto(assunto);
			forumQuestao.setAnexo(anexo);
			forumQuestao.setData(data);
			forumQuestao.setSessaoForum(new SessaoForumGeralService().listarkey(idSessao).get(0));
			forumQuestao.setUsuario(new UsuarioService().listarkey(idAluno).get(0));
			
			resultado = new ForumGeralQuestaoService().atualizarForumQuestao(forumQuestao);
		}else {
			return "0";
		}
		
		return Integer.toString(resultado.getIdForumQuestao());
	}
	
}
