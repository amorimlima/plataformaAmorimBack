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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.MensagensService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.Mensagens;
import br.com.muranodesign.model.Usuario;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos mensagem.
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("Mensagens")
public class MensagensResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(MensagensResource.class.getName());

	/**
	 * Gets the materia.
	 *
	 * @return the materia
	 */
	@GET
	@Produces("application/json")
	public List<Mensagens> getMensagens() {
		logger.info("Listar Mensagens ...");
		List<Mensagens> resultado;
		resultado = new MensagensService().listarTodos();
		logger.info("QTD Mensagens : " + resultado.size());
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
	public Mensagens getEvento(@PathParam("id") int id) {
		logger.info("Lista Mensagens  por id " + id);
		List<Mensagens> resultado;
		resultado = new MensagensService().listarkey(id);
		Mensagens evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Listar por intervalo de id's
	 * @param primeiro
	 * @param ultimo
	 * @return
	 */
	@Path("Intervalo/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<Mensagens> getIntervalo(@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		logger.info("Lista Mensagens  por id intervalo de ids");
		List<Mensagens> retorno = new MensagensService().listIntervalo(primeiro, ultimo);
		return retorno;
	}
	
	/**
	 * Listar por proprietario e imagens
	 * @param idProprietario
	 * @param idMensagem
	 * @return
	 */
	@Path("MensagemByUser/{idProprietario}/{idMensagem}")
	@GET
	@Produces("application/json")
	public List<Mensagens> MensagemByUser(@PathParam("idProprietario") int idProprietario,@PathParam("idMensagem") int idMensagem){
		logger.info("Lista Mensagens  por id de user e id de mensagem");
		List<Mensagens> retorno = new MensagensService().listarMensagemByProprietario(idProprietario, idMensagem);
		return retorno;
	}
	
	
	/**
	 * Gets the evento.
	 *
	 * @param id the id
	 * @return the evento
	 */
	@Path("email/{proprietario}")
	@GET
	@Produces("application/json")
	public List<Mensagens> getMensagemProprietario(@PathParam("proprietario") int proprietario) {
		logger.info("Lista Mensagens  por id " + proprietario);
		
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(proprietario);
		Usuario obj = rsUsuario.get(0);
		
		
		List<Mensagens> resultado;
		resultado = new MensagensService().listarProprietario(obj);
	

		return resultado;

	}
	
	/**
	 * Gets the evento.
	 *
	 * @param id the id
	 * @return the evento
	 */
	@Path("email/{caixa}/{proprietario}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<Mensagens> getMensagemProprietarioCaixa(@PathParam("proprietario") int proprietario , @PathParam("caixa") String caixa,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo) {
		logger.info("Lista Mensagens  por id " + proprietario);
		
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(proprietario);
		Usuario obj = rsUsuario.get(0);
		
		List<Mensagens> resultado;
		resultado = new MensagensService().listarProprietario(obj,caixa,primeiro,ultimo);
	
		if(resultado.isEmpty()){
			return  new MensagensService().listarProprietarioUnica(obj, caixa);
		}

		return resultado;

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("emailHashId/{id}")
	@GET
	@Produces("application/json")
	public List<HashMap<String, String>> getemailHashId(@PathParam("id") int id){
		List<HashMap<String, String>> hash = new ArrayList<HashMap<String,String>>();
				
		
		List<Mensagens> resultado;
		resultado = new MensagensService().listarkey(id);
		
		for (Mensagens mensagens : resultado) {
			HashMap<String, String> aux = new HashMap<String, String>();
			
			String ids = "";
			
			aux.put("idMensagem", Integer.toString(mensagens.getIdmensagens()));
			aux.put("lida", mensagens.getLida());
			aux.put("cxEntrada", mensagens.getCxEntrada());

			aux.put("assunto", mensagens.getAssunto());
			aux.put("mensagem",mensagens.getMensagem());
			aux.put("data",mensagens.getData().toString());
			
			if(mensagens.getRemetente().getPerfil().getIdperfil() == 23){
				aux.put("remetente_perfil", "Aluno");
				aux.put("remetente_nome", mensagens.getRemetente().getAluno().getNome());	
				
			}else {
				aux.put("remetente_perfil", "Professor");
				aux.put("remetente_nome", mensagens.getRemetente().getProfessor().getNome());		
			}
			
				
			aux.put("remetente_idUsuario",  Integer.toString(mensagens.getRemetente().getIdusuario()));	
			aux.put("Destinatarios", mensagens.getDestinatarios());	
			
			String [] arrayUsers =  mensagens.getDestinatarios().split(";");
			for(int i = 0; i < arrayUsers.length; i++){
				
				List<Usuario> destinatariosId = new UsuarioService().listarLogin(arrayUsers[i]);
					for (Usuario usuario : destinatariosId) {
						ids += Integer.toString(usuario.getIdusuario()) + ";";
					}

			}
			
			aux.put("IdDestinatarios", ids);
			ids = "";
			
			hash.add(aux);
		}

		return hash;
	}
	
	/**
	 * 
	 * @param proprietario
	 * @param caixa
	 * @param primeiro
	 * @param ultimo
	 * @return
	 */
	@Path("emailHash/{caixa}/{proprietario}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<HashMap<String, String>> getHashMensagemProprietarioCaixa(@PathParam("proprietario") int proprietario , @PathParam("caixa") String caixa,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo) {
		List<HashMap<String, String>> hash = new ArrayList<HashMap<String,String>>();
		
		logger.info("Lista Mensagens  por id " + proprietario);
		
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(proprietario);
		Usuario obj = rsUsuario.get(0);
		
		
		List<Mensagens> resultado;
		resultado = new MensagensService().listarProprietario(obj,caixa,primeiro,ultimo);
		for (Mensagens mensagens : resultado) {
			HashMap<String, String> aux = new HashMap<String, String>();
			
			String ids = "";
			
			aux.put("idMensagem", Integer.toString(mensagens.getIdmensagens()));
			aux.put("lida", mensagens.getLida());
			aux.put("cxEntrada", mensagens.getCxEntrada());

			aux.put("assunto", mensagens.getAssunto());
			aux.put("mensagem",mensagens.getMensagem());
			aux.put("data",mensagens.getData().toString());
			
			if(mensagens.getRemetente().getPerfil().getIdperfil() == 23){
				aux.put("remetente_perfil", "Aluno");
				aux.put("remetente_nome", mensagens.getRemetente().getAluno().getNome());	
				
			}else{
				aux.put("remetente_perfil", "Professor");
				aux.put("remetente_nome", mensagens.getRemetente().getProfessor().getNome());		
			}
			
				
			aux.put("remetente_idUsuario",  Integer.toString(mensagens.getRemetente().getIdusuario()));	
			aux.put("Destinatarios", mensagens.getDestinatarios());	
			
			String [] arrayUsers =  mensagens.getDestinatarios().split(";");
			for(int i = 0; i < arrayUsers.length; i++){
				
				List<Usuario> destinatariosId = new UsuarioService().listarLogin(arrayUsers[i]);
					for (Usuario usuario : destinatariosId) {
						ids += Integer.toString(usuario.getIdusuario()) + ";";
					}

			}
			
			aux.put("IdDestinatarios", ids);
			ids = "";
			
			hash.add(aux);
		}

		return hash;

	}
	
	
	/**
	 * Removes the Mensagens.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@GET
	@Produces("text/plain")
	public String removeMensagens(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("Mensagens  " + action);
		if ( action.equals("delete")) {
			List<Mensagens> resultado;
			resultado = new MensagensService().listarkey(id);
			Mensagens res = resultado.get(0);
			new MensagensService().deletarMensagens(res);
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Listar mensagens por id de remetente
	 * @param id
	 * @return list
	 */
	@Path("Remetente/{id}")
	@GET
	@Produces("application/json")
	public List<Mensagens> getRemetente(@PathParam("id") int id){
		logger.info("Mensagens do usuario " + id);
		List<Mensagens> rsUsuario;
		rsUsuario = new MensagensService().listarRemetente(id);
		
		return rsUsuario;
	}
	
	/**
	 * Listar mensagens por id de proprietario 
	 * @param id
	 * @return list
	 */ 
	@Path("Proprietario/{id}")
	@GET
	@Produces("application/json")
	public List<Mensagens> getProprietario(@PathParam("id") int id){
		logger.info("Mensagens do usuario " + id);
		List<Mensagens> rsUsuario;
		rsUsuario = new MensagensService().listarProprietario(id);
		
		return rsUsuario;
	}
	
	/**
	 * Count de mensagens por proprietario
	 * @param id
	 * @return int
	 */
	@Path("ProprietarioCount/{id}")
	@GET
	@Produces("application/json")
	public int getProprietarioCount(@PathParam("id") int id){
		logger.info("Mensagens do usuario " + id);
		List<Mensagens> rsUsuario;
		rsUsuario = new MensagensService().listarProprietarioCount(id);
		
		return rsUsuario.size();
	}
	
	/**
	 * Criar e alterar mensagens
	 * @param action
	 * @param strid
	 * @param assunto
	 * @param mensagem
	 * @param lida
	 * @param remetente
	 * @param destinatarios
	 * @return id
	 */
	@POST
	//@Path("upload")
	//@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public String eventoAction(

			@FormParam("action") String action,
			@FormParam("id") String strid,

			@FormParam("assunto") String assunto,
			@FormParam("mensagem") String mensagem,
			@FormParam("lida") String lida,
			@FormParam("remetente") String remetente,
			@FormParam("destinatarios")  List<String>  destinatarios
			

			) {
		
		
		logger.info("eventoAction ...");
		
		Mensagens  resultado = null;
	
		List<Usuario> rsUsuario;
		rsUsuario = new UsuarioService().listarkey(Integer.parseInt(remetente));
		Usuario objRementente = rsUsuario.get(0);
		

		String strDestinatarios = "";
	    Boolean firstDestinatario = true ;
		
		
		for (String dest : destinatarios) {
			
			List<Usuario> rsUsuario2;
			rsUsuario2 = new UsuarioService().listarkey(Integer.parseInt(dest));
			Usuario objRementente2 = rsUsuario2.get(0);
			
			if (firstDestinatario )
			{
				strDestinatarios = objRementente2.getLogin();
				firstDestinatario = false;
			}else {
				strDestinatarios +=  ";" + objRementente2.getLogin();
			}
			
			logger.info("strDestinatarios ..." + strDestinatarios);
		}
		

		if (action.equals("create")) {
			logger.info("Gravando origen da msg ...");
			
			String fileAnexo = null;
				    
			for (String dest : destinatarios) {
				rsUsuario = new UsuarioService().listarkey(Integer.parseInt(dest));
				Mensagens objMensagens = new Mensagens();
		
				objMensagens.setDestinatarios(strDestinatarios);
				objMensagens.setRemetente(objRementente);
				objMensagens.setAssunto(assunto);
				objMensagens.setData(new Date());
				objMensagens.setMensagem(mensagem);
				objMensagens.setLida("N");
				objMensagens.setCxEntrada("S");
				objMensagens.setCxEnviada("N");
				objMensagens.setProprietario(rsUsuario.get(0));
				objMensagens.setAnexo(fileAnexo);
			    resultado = new MensagensService().criarMensagens(objMensagens);
			    
				
			}
		    
			Mensagens objMensagens = new Mensagens();
		    
		    objMensagens.setDestinatarios(strDestinatarios);
			objMensagens.setRemetente(objRementente);
			objMensagens.setAssunto(assunto);
			objMensagens.setData(new Date());
			objMensagens.setMensagem(mensagem);
			objMensagens.setLida("S");
			objMensagens.setCxEntrada("N");
			objMensagens.setCxEnviada("S");
			objMensagens.setProprietario(objRementente);
			objMensagens.setAnexo(fileAnexo);
		    resultado = new MensagensService().criarMensagens(objMensagens);
		        
		        
		} else if (action.equals("update")) {
			Mensagens objMensagens = new Mensagens();
			int id=Integer.parseInt(strid);
			List<Mensagens> rsMensagens;
			rsMensagens= new MensagensService().listarkey(id);
			objMensagens = rsMensagens.get(0);
			
			objMensagens.setLida(lida);
			
			resultado = new MensagensService().atualizarMensagens(objMensagens);
			

		}else {
			logger.info("Erro na URI  " + action);
			return "0";
		}
		
		
		return Integer.toString(resultado.getIdmensagens());
		
	}
	
	/**
	 * update de mensagens lidas
	 * @param strId
	 * @param lida
	 * @return String
	 */
	@Path("update/lida/{id}/{lida}")
	@POST
	public String eventoAction2(

			@PathParam("id") String strId,
			@PathParam("lida") String lida
	
			) {

		String retorno = "";
		
		try{
		Mensagens objMensagens = new Mensagens();
		int id=Integer.parseInt(strId);
		List<Mensagens> rsMensagens;
		rsMensagens= new MensagensService().listarkey(id);
		objMensagens = rsMensagens.get(0);
		
		objMensagens.setLida(lida);
		
		new MensagensService().atualizarMensagens(objMensagens);
		retorno = "alterado";
		
		}catch(Exception e){
			retorno = "Naoalterado";
		}
		
		return retorno;
	

	}

}
