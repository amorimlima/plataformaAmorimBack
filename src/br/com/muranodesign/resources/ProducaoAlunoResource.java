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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.CategoriaProducaoAlunoService;
import br.com.muranodesign.business.MensagensService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.PendenciasProducaoAlunoService;
import br.com.muranodesign.business.ProducaoAlunoService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.business.TipoProducaoAlunoService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.CategoriaProducaoAluno;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.PendenciasProducaoAluno;
import br.com.muranodesign.model.ProducaoAluno;
import br.com.muranodesign.model.Roteiro;
import br.com.muranodesign.model.TipoProducaoAluno;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos producao do aluno 
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */


@Path("ProducaoAluno")
public class ProducaoAlunoResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ProducaoAlunoResource.class.getName());

	/**
	 * Gets the producao aluno.
	 *
	 * @return the producao aluno
	 */
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> getProducaoAluno() {
		logger.info("Listar ProducaoAluno ...");
		List<ProducaoAluno> resultado;
		resultado = new ProducaoAlunoService().listarTodos();
		logger.info("QTD ProducaoAluno : " + resultado.size());
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
	public ProducaoAluno getEvento(@PathParam("id") int id) {
		logger.info("Lista ProducaoAluno  por id " + id);
		List<ProducaoAluno> resultado;
		resultado = new ProducaoAlunoService().listarkey(id);
		ProducaoAluno evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Aluno Portifolio
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	@Path("alunoPortifolio/{id}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> getalunoPortifolio(@PathParam("id") int id) throws ParseException {
		logger.info("Lista ProducaoAluno  por id " + id);
		List<ProducaoAluno> resultado;
		resultado = new ProducaoAlunoService().listarPortifolio(id);    
		return resultado;

	}
	
	/**
	 * Aluno Tipo Producao
	 * @param idAluno
	 * @param idRoteiro
	 * @param idTipo
	 * @return
	 */
	@Path("alunoTipoProducao/{idAluno}/{idRoteiro}/{idTipo}")
	@GET
	@Produces("application/json")
	public Hashtable<Integer, Integer> getAlunoTipoProducao(@PathParam("idAluno") int idAluno,@PathParam("idRoteiro") int idRoteiro,
			@PathParam("idTipo") int idTipo){
		
		Hashtable<Integer, Integer> retorno = new Hashtable<Integer, Integer>();
		List<ProducaoAluno> aux = new ProducaoAlunoService().listaAlunoRoteiroTipo(idAluno, idRoteiro, idTipo);
		
		if(!aux.isEmpty()){
			ProducaoAluno prod = aux.get(0);
			
			if(!(prod.getMensagens() == null)){
				retorno.put(prod.getMensagens().getIdmensagens(), prod.getStatus());
			}else{
				retorno.put(0, prod.getStatus());
			}
			
		}else{
			retorno.put(0,0);
		}
		
		return retorno;
		
	}
	
	/**
	 * Não capa
	 * @param id
	 * @return
	 */
	@Path("NCapa/{id}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> getNCapa(@PathParam("id") int id){
		return new ProducaoAlunoService().NCapa(id);
		
	}
	
	/**
	 * Removes the producao aluno.
	 *
	 * @param action the action
	 * @param id the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeProducaoAluno(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("ProducaoAluno  " + action);
		if ( action.equals("delete")) {
			List<ProducaoAluno> resultado;
			resultado = new ProducaoAlunoService().listarkey(id);
			ProducaoAluno evento = resultado.get(0);
			new ProducaoAlunoService().deletarProducaoAluno(evento);
			return "true";
		} else {
			return "false";
		}

	}
	
	@Path("DeletarProducaoAluno/{idaluno}/{idroteiro}/{tipoproducao}")
	@GET
	@Produces("text/plain")
	public String removerProducaoAluno(@PathParam("idaluno") int idaluno, @PathParam("idroteiro") int idroteiro, @PathParam("tipoproducao") int tipoproducao){
		
		ProducaoAluno producaoAluno = new ProducaoAlunoService().listaAlunoRoteiroTipo(idaluno, idroteiro, tipoproducao).get(0);
		
		PendenciasProducaoAluno pendencias = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro).get(0);
		
		if (tipoproducao == 4)
			pendencias.setFichaFinalizacaoCompleta(0);
		else
			pendencias.setPortfolioCompleto(0);
		
		new PendenciasProducaoAlunoService().atualizarPendenciasProducaoAluno(pendencias);
		
		new ProducaoAlunoService().deletarProducaoAluno(producaoAluno);
		
		return "deleteado";
	}
	
	
	/**
	 * Verifica o status da produção do aluno
	 * @param id
	 * @param status
	 * @param mensagens
	 * @return int
	 */
	@Path("Status/{id}/{status}/{mensagens}")
	@GET
	@Produces("application/json")
	public int status(
			
			@PathParam("id") int id,
			@PathParam("status") int status,
			@PathParam("mensagens") int mensagens){
		
		   ProducaoAluno objProducaoAluno = new ProducaoAlunoService().listarkey(id).get(0);
		   objProducaoAluno.setStatus(status);
		   
		   if(status == 2){
			   objProducaoAluno.setMensagens(new MensagensService().listarkey(mensagens).get(0));
		   }
		   
		   ProducaoAluno resultado =  new ProducaoAlunoService().atualizarProducaoAluno(objProducaoAluno);
		   return resultado.getIdproducaoAluno();
		
	}
	
	
	@Path("OficinaAluno/{idOficina}/{idAluno}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> listarOficina(@PathParam("idOficina") int idOficina, @PathParam("idAluno") int idAluno){
		
		List<ProducaoAluno> resultado = new ProducaoAlunoService().listarOficina(idOficina, idAluno);
		return resultado;
		
	}
	
	/**
	 * Upload de capa
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("upload/capa/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ProducaoAluno upload(

	@PathParam("id") String strId,
			@FormDataParam("fotoAluno") InputStream uploadedInputStream,
			@FormDataParam("fotoAluno") FormDataContentDisposition fileDetail

	) {

		
		
		ProducaoAluno prod = new ProducaoAluno();
		ProducaoAluno resultado = new ProducaoAluno();
		
		int id = Integer.parseInt(strId);
		List<ProducaoAluno> rsProd;
		rsProd = new ProducaoAlunoService().listarkey(id);
		prod = rsProd.get(0);

		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		//String uploadedFileLocation = "C:/Users/Kevyn/Documents/kevyn/"+arquivo;

		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		String anexo = "http://177.55.99.90/files/" + arquivo;

		logger.info("anexo" + anexo);
		if(prod.getCapa() != null)
			upload.deleteFile(prod.getCapa());

		prod.setCapa(anexo);
		
		resultado = new ProducaoAlunoService().atualizarProducaoAluno(prod);

		return resultado;

	}
	

	/**
	 * Listar producao de aluno por filtros 
	 * @param id
	 * @param tipo
	 * @param roteiro
	 * @return list
	 */
	@Path("Filtos/{id}/{tipo}/{roteiro}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> filtro(
			@PathParam("id") int id,
			@PathParam("tipo") int tipo,
			@PathParam("roteiro") int roteiro){
		
		
		return new ProducaoAlunoService().listarFiltro(id, tipo, roteiro);
		
	}
	
	/**
	 * Listar producao do aluno por id de aluno
	 * @param id
	 * @return list
	 */
	@Path("Aluno/{id}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> aluno(
			@PathParam("id") int id){
		
		return new ProducaoAlunoService().listarAluno(id);
		
	}
	
	@Path("AlunoUltimasPostagens/{idAluno}")
	@GET
	@Produces("application/json")
	public List<ProducaoAluno> getUltimasProducoes(@PathParam("idAluno") int idAluno){
		return new ProducaoAlunoService().listarUltimasAluno(idAluno);
	}
	
	/**
	 * Criar e alterar producao do aluno
	 * @param action
	 * @param strid
	 * @param anoLetivo
	 * @param texto
	 * @param arquivo
	 * @param status
	 * @param aluno
	 * @param tipo
	 * @param roteiro
	 * @param categoria
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			
			@FormParam("anoLetivo") String anoLetivo,
			@FormParam("texto") String texto,
			@FormParam("arquivo") String arquivo,
			@FormParam("status") int status,
			
			@FormParam("capa") String capa,
		
			@FormParam("aluno") String aluno,
			@FormParam("tipo") String tipo,
			@FormParam("roteiro") String roteiro,
			@FormParam("oficina") String oficina,
			@FormParam("categoria") String categoria
			
			) throws ParseException {
		ProducaoAluno objProducaoAluno = new ProducaoAluno();
		logger.info("eventoAction ...");
		ProducaoAluno resultado = null;

		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();  
		Date date = (Date)formatter.parse(data);
		
		
		List<Aluno> rsAluno;
		rsAluno = new AlunoService().listarkey(Integer.parseInt(aluno));
		Aluno objAluno= rsAluno.get(0);
		
		
		List<TipoProducaoAluno> rsTipoProducaoAluno;
		rsTipoProducaoAluno = new TipoProducaoAlunoService().listarkey(Integer.parseInt(tipo));
		TipoProducaoAluno objTipoProducaoAluno= rsTipoProducaoAluno.get(0);
		
		int t =Integer.parseInt(tipo);
		

		Roteiro objRoteiro = new Roteiro();
		Oficina objOficina = new Oficina();
		
		if(roteiro != null){
			objRoteiro = new RoteiroService().listarid(Integer.parseInt(roteiro));
		}
		
		if(oficina != null){
			objOficina = new OficinaService().listarkey(Integer.parseInt(oficina)).get(0);
			
		}
		
		List<CategoriaProducaoAluno> rsCategoriaProducaoAluno;
		rsCategoriaProducaoAluno = new CategoriaProducaoAlunoService().listarkey(Integer.parseInt(categoria));
		CategoriaProducaoAluno objCategoriaProducaoAluno= rsCategoriaProducaoAluno.get(0);
		
		
		List<AnoLetivo> rsAnoLetivo;
		rsAnoLetivo = new AnoLetivoService().listarkey(Integer.parseInt(anoLetivo));

		AnoLetivo objAnoLetivo= rsAnoLetivo.get(0);
	
		
		
		if (action.equals("create")) {
		  
		    objProducaoAluno.setTexto(texto);
		    objProducaoAluno.setAnoLetivo(objAnoLetivo);
		    objProducaoAluno.setData(date);
		    objProducaoAluno.setAluno(objAluno);
		    objProducaoAluno.setTipo(objTipoProducaoAluno);	
		    objProducaoAluno.setStatus(1);
		    
		    if(t == 5){
		    	objProducaoAluno.setCapa(capa);
		    }
		    if (t == 7){
		    	objProducaoAluno.setOficina(objOficina);
		    }
		    if(t == 6 ){
		    	objProducaoAluno.setArquivo(arquivo);
		    }
		    if(t == 4 || t == 5){
		    	objProducaoAluno.setRoteiro(objRoteiro);
		    }
		    objProducaoAluno.setCategoria(objCategoriaProducaoAluno);
		
			
			resultado = new ProducaoAlunoService().criarProducaoAluno(objProducaoAluno);
			
		}  else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<ProducaoAluno> rsProducaoAluno;
			rsProducaoAluno= new ProducaoAlunoService().listarkey(id);
			objProducaoAluno= rsProducaoAluno.get(0);
			
			    objProducaoAluno.setTexto(texto);
			    objProducaoAluno.setAnoLetivo(objAnoLetivo);
			    objProducaoAluno.setData(date);
			    objProducaoAluno.setAluno(objAluno);
			    objProducaoAluno.setTipo(objTipoProducaoAluno);
			    objProducaoAluno.setStatus(status);
			    
			    if(t == 5){
			    	objProducaoAluno.setCapa(capa);
			    }
			    if (t == 7){
			    	objProducaoAluno.setOficina(objOficina);
			    }
			    if(t == 4 || t == 5){
			    	objProducaoAluno.setRoteiro(objRoteiro);
			    }
			    objProducaoAluno.setCategoria(objCategoriaProducaoAluno);
			
			
			 resultado =  new ProducaoAlunoService().atualizarProducaoAluno(objProducaoAluno);
			
			
		} else {
			return "0";
		}
	    return Integer.toString(resultado.getIdproducaoAluno());
	
		}


	/**
	 * upload de imagem producao aluno
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return obj producaoAluno
	 */
	@POST
	@Path("upload/producaoAluno/imagem/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ProducaoAluno eventoAction(

			@PathParam("id") String strId,
			@FormDataParam("imagem") InputStream uploadedInputStream,
			@FormDataParam("imagem") FormDataContentDisposition fileDetail

			) {

		ProducaoAluno objProducaoAluno = new ProducaoAluno();
		ProducaoAluno resultado = new ProducaoAluno();
		
		int id=Integer.parseInt(strId);
		List<ProducaoAluno> rsProducaoAluno;
		rsProducaoAluno= new ProducaoAlunoService().listarkey(id);
		objProducaoAluno= rsProducaoAluno.get(0);
		
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		String anexo = "http://177.55.99.90/files/"+ arquivo;
		
		Upload upload = new Upload (); 
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		logger.info("anexo" + anexo);  
		
		objProducaoAluno.setImagem(anexo);
		
		resultado =  new ProducaoAlunoService().atualizarProducaoAluno(objProducaoAluno);

		return resultado;

	}
	
	/**
	 * upload de aquivo producao aluno
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("upload/producaoAluno/arquivo/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public ProducaoAluno eventoAction2(

			@PathParam("id") String strId,
			@FormDataParam("arquivo") InputStream uploadedInputStream,
			@FormDataParam("arquivo") FormDataContentDisposition fileDetail


			) {

		ProducaoAluno objProducaoAluno = new ProducaoAluno();
		ProducaoAluno resultado = new ProducaoAluno();
		
		int id=Integer.parseInt(strId);
		List<ProducaoAluno> rsProducaoAluno;
		rsProducaoAluno= new ProducaoAlunoService().listarkey(id);
		objProducaoAluno= rsProducaoAluno.get(0);
		
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		//String uploadedFileLocation = "C:/Users/Kevyn/Documents/kevyn/"+arquivo;
		String anexo = "http://177.55.99.90/files/"+ arquivo;
		
		Upload upload = new Upload (); 
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		logger.info("anexo" + anexo);     
		
		if(objProducaoAluno.getArquivo() != null)
			upload.deleteFile(objProducaoAluno.getArquivo());
		objProducaoAluno.setArquivo(anexo);
		
		resultado =  new ProducaoAlunoService().atualizarProducaoAluno(objProducaoAluno);

		return resultado;


	}
}