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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.HistoricoService;
import br.com.muranodesign.business.MenuPerfilService;
import br.com.muranodesign.business.SOService;
import br.com.muranodesign.business.UsuarioService;
import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.MenuPerfil;
import br.com.muranodesign.model.Usuario;


/**
 * Classe tem como objetivo de validar usuario e senha na tela de login
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

@Path("Logar")
public class LoginResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(LoginResource.class.getName());

	/**
	 * Metodo que realiza a atenticação na aplicação.
	 *
	 * @param usuario
	 *            - Usuario
	 * @param senha
	 *            - senha
	 * @return String - Usuario
	 */
	@POST
	@Produces("application/json")
	public Usuario doLogar(@FormParam("usuario") String usuario,
		@FormParam("senha") String senha,@FormParam("versao") String versao, @Context HttpServletRequest req) {
		Usuario ret = null;
		logger.info("Logar Usuario ..." + usuario);
		
		String ip = req.getRemoteAddr();  //pega o ip da requisição
		//String remoteHost = req.getRemoteHost();
		String so, ipescola= "201.91.1.98";


		Historico_conexao historico = new Historico_conexao();
	
		List<Usuario> resultado;
		resultado = new UsuarioService().listarLogin(usuario);
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(), 0, senha.length());
		
			String senhaMD5 = new BigInteger(1, m.digest()).toString(16);

			for (Usuario user : resultado) {
				
				if (user.getSenha().equals(senhaMD5)) {
					/*if(user.getAtivo() == 1){
						
						return null;
						
					}else{*/
					
					//user.setAtivo(1);
					//new UsuarioService().atualizarUsuario(user);
					ret = user;
					logger.info("Usuario " + usuario
							+ " liberado para acessa a aplicação");
					
					
					Date data = Calendar.getInstance().getTime();
	
				
					
					if(ip.equals(ipescola)){
						
						historico.setUsuario(user);
						historico.setData(data);
						historico.setCnx_escola(ip);
					}
					else{
						
						historico.setUsuario(user);
						historico.setData(data);
						historico.setCnx_externo(ip);
					}
					
					HistoricoService histoService = new HistoricoService();
					SOService soService = new SOService();
						
					
					if(versao.contains("Win") &&  versao.contains("95")){
						so = "Windows 95";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					else if(versao.contains("Win") &&  versao.contains("98")){
						so = "Windows 98";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					else if(versao.contains("Win") &&  versao.contains("NT 5.1")){
						so = "Windows NT/XP";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					else if(versao.contains("Win") &&  versao.contains("NT 5.0")){
						so = "Windows 2000";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					else if(versao.contains("Win") && versao.contains("NT 5.2")){
						so = "Windows 2000";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					else if(versao.contains("Win") && versao.contains("NT 6.0")){
						so = "Windows Vista";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					else if(versao.contains("Win") && versao.contains("NT 6.1")){
						so = "Windows 7";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					else if(versao.contains("Win") && versao.contains("NT 6.3")){
						so = "Windows 8";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					
					else if(versao.contains("Mac")){
						so = "Macintosh";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					else if(versao.contains("Unix")){
						so = "Unix";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					else if(versao.contains("Linux")){
						so = "Linux";
						historico.setS_o(soService.listarSO(so).get(0));
					}
					else { 
						so = "Outro";
						historico.setS_o(soService.listarSO(so).get(0));
						
					}
					
					histoService.criarHistorico(historico);
					
					
				//}
			  }
			}

		} catch (NoSuchAlgorithmException e) {
			logger.info("Erro ao realizar login");
		}

		return ret;
	}

	/**
	 * Método para alterar senha do usuario
	 * 
	 * @param login
	 *            atual
	 * @param senhaAnt
	 * @param senha
	 * @return O retorno é uma string informando se a alteração foi realizada ou
	 *         não    
	 *         
	 */

	/**
	 * 
	 * @param login
	 * @param senhaAnt
	 * @param senha
	 * @param senhaNova
	 * @param id
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	@Path("AlterarSenhaFull")
	@POST
	@Produces("text/plain")
	public String alterarSenhaFull(
		@FormParam("senha") String senha,
		@FormParam("id") int id){
		
	
		Usuario user = new Usuario();
		MessageDigest m = null;
		
		String retorno = "";
	
			user = new UsuarioService().listarkey(id).get(0);
			
			if(user != null){
				try {
					m = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(m != null){
					m.update(senha.getBytes(), 0, senha.length());
					String senhaMD5 = new BigInteger(1, m.digest()).toString(16);
					
					user.setSenha(senhaMD5);
					new UsuarioService().updateUser(senhaMD5, id);
					
					retorno = "ok";
				}else{
					retorno = "usuario não existe";
				}
				
			}else{
				retorno = "usuario não existe";
			}
		return retorno;
	}
	
	
	/**
	 * Alterar a senha do usuario
	 * @param login
	 * @param senhaAnt
	 * @param senha
	 * @param senhaNova
	 * @param id
	 * @return id
	 */
	@Path("alterarSenha")
	@POST
	@Produces("text/plain")
	public String alterarSenha(@FormParam("login") String login,
			@FormParam("senhaAnt") String senhaAnt,
			@FormParam("senha") String senha,
			@FormParam("senhaNova") String senhaNova,
			@FormParam("id") int id) {
		logger.info("Alterar Login ..." + login);

		String retorno ="";
		MessageDigest m;
		List<Usuario> resultado;
		resultado = new UsuarioService().listarLogin(login);

		try {
			m = MessageDigest.getInstance("MD5");
			m.update(senhaAnt.getBytes(), 0, senhaAnt.length());
			String senhaMD5 = new BigInteger(1, m.digest()).toString(16);

			for (Usuario user : resultado) {
				if (user.getSenha().equals(senhaMD5)) {
					if (senha.length() <= 6 && senha.equals(senhaNova)) {

						UsuarioService UserService = new UsuarioService();
						m = MessageDigest.getInstance("MD5");
						m.update(senha.getBytes(), 0, senha.length());
						senhaMD5 = new BigInteger(1, m.digest()).toString(16);
						user.setSenha(senhaMD5);
						UserService.atualizarUsuario(user);
						
						retorno = "ok";
					} else {
						logger.info("Tamanhos para senha ou usuario invalidos");
						retorno = "Erro";
					}
				} else {
					logger.info("Senha invalida " + senhaAnt);
					retorno = "Erro";
				}
			}
			

		} catch (NoSuchAlgorithmException e) {
			logger.info("Erro ao realizar login");
		}
		return retorno;
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("html/{id}")
	@GET
	@Produces("application/json")
	public List<MenuPerfil> getMenu(@PathParam("id") int id){
		
		Usuario user = new UsuarioService().listarkey(id).get(0);
		List<MenuPerfil> menu = new MenuPerfilService().listarUser(user.getPerfil().getIdperfil());
		
		
		return menu;
	}
	
	
}
