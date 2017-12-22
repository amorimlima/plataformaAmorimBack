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
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.ForumQuestaoService;
import br.com.muranodesign.business.ForumRespostaService;
import br.com.muranodesign.business.PerfilService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.ForumQuestao;
import br.com.muranodesign.model.Perfil;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.Usuario;
import br.com.muranodesign.util.CommonsMail;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos usuario
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("Usuario")
public class UsuarioResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(UsuarioResource.class.getName());

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	@GET
	@Produces("application/json")
	public List<Usuario> getUsuario() {
		logger.info("Listar Usuario ...");
		List<Usuario> resultado;
		resultado = new UsuarioService().listarTodos();
		logger.info("QTD Usuario : " + resultado.size());
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
	public Usuario getEvento(@PathParam("id") int id) {
		logger.info("Lista Usuario  por id " + id);
		List<Usuario> resultado;
		resultado = new UsuarioService().listarkey(id);
		Usuario evento = resultado.get(0);

		return evento;
	}
	
	/**
	 * Listar usuario por id
	 * @param id
	 * @return obj usuario
	 */
	@Path("aluno/{id}")
	@GET
	@Produces("application/json")
	public Usuario getAluno(@PathParam("id") int id) {
		logger.info("Lista Usuario  por id " + id);
		List<Usuario> resultado;
		resultado = new UsuarioService().listaAluno(id);
		Usuario evento = resultado.get(0);

		return evento;
	}
	
	@Path("DadosUsuario/{id}")
	@GET
	@Produces("application/json")
	public Hashtable<String, String> getDadosUsuario(@PathParam("id") int id){
		List<Usuario> user = new UsuarioService().listarkey(id);
	
		Hashtable<String, String> retorno = new Hashtable<String, String>();
		if(!(user.get(0).getAluno() == null)){
			
			retorno.put("idAluno", Integer.toString(user.get(0).getAluno().getIdAluno()));
			retorno.put("nome",(user.get(0).getAluno().getNome()));
		}else{
			
			retorno.put("idProfessor", Integer.toString(user.get(0).getProfessor().getIdprofessorFuncionario()));
			retorno.put("nome",(user.get(0).getProfessor().getNome()));
		}
		
		return retorno;
	}
	
	@Path("NomeUsuario/{id}")
	@GET
	@Produces("application/json")
	public Hashtable<String, String> getNomeUsuario(@PathParam("id") int id){
		List<Usuario> user = new UsuarioService().listarkey(id);
	
		Hashtable<String, String> retorno = new Hashtable<String, String>();
		if(!(user.get(0).getAluno() == null)){
			
			retorno.put("nome",(user.get(0).getAluno().getNome()));
		}else{
			
			retorno.put("nome",(user.get(0).getProfessor().getNome()));
		}
		
		return retorno;
	}
	
	
	@Path("ListarObjParte")
	@GET
	@Produces("application/json")
	public List<Hashtable<String, String>> getListarObjParte(){
		List<Usuario> user = new UsuarioService().listarTodos();
		
		
		List<Hashtable<String, String>> lista = new ArrayList<Hashtable<String,String>>();
		
		for (Usuario usuario : user) {
				
			if(!(usuario.getAluno() == null)){
				Hashtable<String, String> retorno = new Hashtable<String, String>();
				
				retorno.put("idUsuario", Integer.toString(usuario.getIdusuario()));
				retorno.put("idAluno", Integer.toString(usuario.getAluno().getIdAluno()));
				retorno.put("nome",(usuario.getAluno().getNome()));
				
				lista.add(retorno);
			}else if(!(usuario.getProfessor() == null)){
				Hashtable<String, String> retorno = new Hashtable<String, String>();
				
				retorno.put("idUsuario", Integer.toString(usuario.getIdusuario()));
				retorno.put("idProfessor", Integer.toString(usuario.getProfessor().getIdprofessorFuncionario()));
				retorno.put("nome",(usuario.getProfessor().getNome()));
				
				lista.add(retorno);
			}
		}
		
		return lista;
	}
	
	
	/**
	 * mata a sessao aberta
	 * @param id
	 */
	@Path("logout/{id}")
	@POST
	@Produces("text/plain")
	public void getLogout(@FormParam("id") int id) {
		Usuario user = new UsuarioService().listarkey(id).get(0);
		user.setAtivo(0);
		new UsuarioService().atualizarUsuario(user);
	}
	
	/**
	 * Listar professor por id
	 * @param id
	 * @return obj usuario
	 */
	@Path("professor/{id}")
	@GET
	@Produces("application/json")
	public Usuario getProfessor(@PathParam("id") int id) {
		logger.info("Lista Usuario  por id " + id);
		List<Usuario> resultado;
		resultado = new UsuarioService().listaProfessor(id);
		Usuario evento = resultado.get(0);

		return evento;
	}
	
	@Path("ListarPerfil/{idPerfil}")
	@GET
	@Produces("application/json")
	public List<Usuario> getPerfil(@PathParam("idPerfil") int idPerfil){
		return new UsuarioService().listarPerfil(idPerfil);
	}
	
	/**
	 * Seta o valor 1 para o campo banner
	 * @param id
	 */
	@Path("CamposObrigatorios/{id}")
	@GET
	@Produces("application/json")
	public void CamposObrigatorios(@PathParam("id") int id){
		Usuario user = new UsuarioService().listarkey(id).get(0);
		user.setBanner(1);
		new UsuarioService().atualizarUsuario(user);
	}
	
	/**
	 * Lista respostas de forum questao
	 * @param idUser
	 * @return
	 */
	@Path("Respostas/{idUser}")
	@GET
	@Produces("application/json")
	public long Respostas(@PathParam("idUser") int idUser){
		
		List<ForumQuestao> questoes = new ForumQuestaoService().listaUser(idUser);
		
		long count = 0;
		if(!questoes.isEmpty()){
			for (ForumQuestao forumQuestao : questoes) {
				long aux = new ForumRespostaService().Total(forumQuestao.getIdforumQuestao());
				count = count + aux;
			}
		}
		
		return count;
		
	}
	
	
	
	/**
	 * recuperar senha
	 * @param email
	 * @return
	 * @throws MalformedURLException
	 */
	@Path("recuperarSenha/{email}")
	@GET
	@Produces("application/json")
	public Usuario getNovoSenha(@PathParam("email") String email) throws MalformedURLException {
		logger.info("Lista Usuario  por id " + email);
		List<Usuario> resultado;
		
		
		resultado = new UsuarioService().listaAlunoEmail(email);
		Usuario resultado2 = null;
		
		UUID uuid = UUID.randomUUID();    
		String myRandom = uuid.toString();    
		String novaSenha = (myRandom.substring(0,6));
		Usuario usuario = resultado.get(0);
		
		String senhaMD5 = null;
		MessageDigest m;
		try {
		m = MessageDigest.getInstance("MD5");
	
		m.update(novaSenha.getBytes(),0,novaSenha.length());
	
		senhaMD5 = new BigInteger(1,m.digest()).toString(16);
		CommonsMail commonsMail = new CommonsMail();
		commonsMail.enviaEmailSimples(usuario.getEmail(), novaSenha, usuario.getLogin());
		usuario.setSenha(senhaMD5);
		resultado2 = new UsuarioService().atualizarUsuario(usuario);
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.warn("Erro de:  "+e);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.warn("Erro de:  "+e);
		}
		

		return resultado2;
	}
		
	
	/**
	 * Removes the usuario.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeUsuario(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("Usuario  " + action);
		if ( action.equals("delete")) {
			List<Usuario> resultado;
			resultado = new UsuarioService().listarkey(id);
			Usuario res = resultado.get(0);
			new UsuarioService().deletarUsuario(res);
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Evento action.
	 *
	 * @param action the action
	 * @param strid the strid
	 * @param login the login
	 * @param senha the senha
	 * @param email the email
	 * @param perfil the perfil
	 * @param aluno the aluno
	 * @param professor the professor
	 * @param moderador the moderador
	 * @return the string
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			@FormParam("login") String login,
			@FormParam("senha") String senha,
			@FormParam("email") String email,
			@FormParam("perfil") String perfil,
			@FormParam("aluno") String aluno,
			@FormParam("professor") String professor,
			@FormParam("moderador") String moderador
			) {
		Usuario objUsuario = new Usuario();
		logger.info("eventoAction ...");
		Usuario  resultado;
		
		
		Aluno objAluno = null;	
		if (!aluno.isEmpty()){
				List<Aluno> rsAluno;
				rsAluno = new AlunoService().listarkey(Integer.parseInt(aluno));
				objAluno = rsAluno.get(0);
			}
				//TODO: Validar valores.
				
			ProfessorFuncionario objProfessorFuncionario = null	;
			if (!professor.isEmpty()){
				// get tipo Professor
				List<ProfessorFuncionario> rsProfessorFuncionario;
				rsProfessorFuncionario = new ProfessorFuncionarioService().listarkey(Integer.parseInt(professor));
				 objProfessorFuncionario= rsProfessorFuncionario.get(0);
				//TODO: Validar valores.
				}
				
				// get tipo Professor
				List<Perfil> rsPerfil;
				rsPerfil = new PerfilService().listarkey(Integer.parseInt(perfil));
				Perfil objPerfil= rsPerfil.get(0);
				
				String senhaMD5 = null;
				if (!senha.isEmpty()) {
				MessageDigest m;
					try {
						m = MessageDigest.getInstance("MD5");
						m.update(senha.getBytes(),0,senha.length());
						
						 senhaMD5 = new BigInteger(1,m.digest()).toString(16);
						
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						logger.warn("Erro de:  "+e);
					}
				}
				
				
				if (action.equals("create")) {
					logger.info("Criando no  Plano de Estudos");
					
					objUsuario.setLogin(login);
					objUsuario.setSenha(senhaMD5);
					objUsuario.setEmail(email);
					objUsuario.setPerfil(objPerfil);
					objUsuario.setAluno(objAluno);
					objUsuario.setProfessor(objProfessorFuncionario);
					objUsuario.setModerador(moderador);
					
					resultado = new UsuarioService().criarUsuario(objUsuario);
					
					
				} else if (action.equals("update")) {
					int id=Integer.parseInt(strid);
					List<Usuario> rsUsuario;
					rsUsuario = new UsuarioService().listarkey(id);
					objUsuario = rsUsuario.get(0);
					objUsuario.setIdusuario(id);
					objUsuario.setLogin(login);
					
					if (!senha.isEmpty()) {
						objUsuario.setSenha(senhaMD5);
					}
					
					objUsuario.setEmail(email);
					objUsuario.setPerfil(objPerfil);
					objUsuario.setAluno(objAluno);
					objUsuario.setProfessor(objProfessorFuncionario);
					objUsuario.setModerador(moderador);
					
					resultado = new UsuarioService().atualizarUsuario(objUsuario);
					
					
				}else {
					logger.info("Erro na URI  " + action);
					return "0";
				}
				
		
				return Integer.toString(resultado.getIdusuario());
	}
	
	@Path("AlterarFoto/{idUsuario}")
	@POST
	@Produces("text/plain")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String updateFoto(
			@PathParam ("idUsuario") int idUsuario, 
			@FormDataParam("foto") InputStream uploadedInputStream,
			@FormDataParam("foto") FormDataContentDisposition fileDetail){
		
		Usuario usuario = new UsuarioService().listarkey(idUsuario).get(0);
		
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		
		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		if (usuario.getAluno() != null)
		{
			if (usuario.getAluno().getFotoAluno() != null)
				upload.deleteFile(usuario.getAluno().getFotoAluno());
			usuario.getAluno().setFotoAluno(uploadedFileLocation);
			new AlunoService().atualizarAluno(usuario.getAluno());
		}
		else
		{
			if (usuario.getProfessor().getFotoProfessorFuncionario() != null)
				upload.deleteFile(usuario.getProfessor().getFotoProfessorFuncionario());
			usuario.getProfessor().setFotoProfessorFuncionario(uploadedFileLocation);
			new ProfessorFuncionarioService().atualizarProfessorFuncionario(usuario.getProfessor());
		}
		
		return "ok";
	}
	
	@Path("ListarUsuarioNome/{login}")
	@GET
	@Produces("application/json")
	public List<Usuario> listarNome(@PathParam("login") String login){
		return new UsuarioService().listarLogin(login);
	}

}
