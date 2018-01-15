package br.com.muranodesign.resources;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import br.com.muranodesign.business.JeiffPeaService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.JeiffPea;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("JeiffPea")
public class JeiffPeaResource {

	private Logger logger = Logger.getLogger(AgrupamentoResource.class.getName());
	
	/**
	 * Deletar, alterar e criar Jeiff
	 * @param action
	 * @param id
	 * @param ata
	 * @param periodo
	 * @param data
	 * @return  id
	 * @throws ParseException 
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("professor") int idprofessorFuncionario,
			@FormParam("ata") String ata,
			@FormParam("periodo") int periodo,
			@FormParam("data") String data,
			@FormParam("descricao") String descricao) throws ParseException{
		
		JeiffPea resultado = new JeiffPea(); 
		
		if (action.equals("delete"))
		{
			resultado = new JeiffPeaService().deletarJeiffPea(new JeiffPeaService().listarkey(id).get(0));
		}
		else if(action.equals("create"))
		{
			StringUtil stringUtil = new StringUtil();
			Date dataT = stringUtil.converteStringData(data);
			
			resultado.setProfessorFuncionario(new ProfessorFuncionarioService().listarkey(idprofessorFuncionario).get(0));
			resultado.setAta(ata);
			resultado.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			resultado.setData_reuniao(dataT);
			resultado.setDescricao(descricao);
			
			if (id != 0)
			{
				resultado.setIdJeiffPea(id);
				new JeiffPeaService().atualizarJeiffPea(resultado);
			}
			else
				resultado = new JeiffPeaService().criarJeiffPea(resultado);
		}
		else if(action.equals("update"))
		{
			resultado.setIdJeiffPea(id);
			if (!data.equals(""))
			{
				DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String dataS = formatter.format(data);
				Date dataT = (Date) formatter.parse(dataS);
				resultado.setData_reuniao(dataT);
			}
			if (!ata.equals(""))
			{
				resultado.setAta(ata);
			}
			if (periodo != 0)
			{
				resultado.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			}
			new JeiffPeaService().atualizarJeiffPea(resultado);
		}
		
		return Integer.toString(resultado.getIdJeiffPea());
		
	}
	
	/**
	 * upload arquivo Jeiff/Pea
	 */
	@Path("/upload/{id}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/plain")
	public String uploadJeiffPea(
			@PathParam("id") int id,
			@FormDataParam("anexo") InputStream uploadedInputStream,
			@FormDataParam("anexo") FormDataContentDisposition fileDetail){
		
		JeiffPea jeiff = new JeiffPeaService().listarkey(id).get(0);
		
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(), 50);
		//String uploadedFileLocation = "C:/Users/murano/Desktop/Teste/" + arquivo;
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		
		Upload upload = new Upload();
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		if(jeiff.getArquivo() != null)
			upload.deleteFile(jeiff.getArquivo());
		//jeiff.setArquivo("C:/Users/murano/Desktop/Teste/" + arquivo);
		jeiff.setArquivo("http://177.55.99.90/files/" + arquivo);
		new JeiffPeaService().atualizarJeiffPea(jeiff);
		
		return Integer.toString(id);
		
	}
	
	
	
	/**
	 * Listar Todos JeiffPea
	 */
	@GET
	@Produces("application/json")
	public List<JeiffPea> getJeiffPea(){
		logger.debug("Listar Jeiff/Pea");
		List<JeiffPea> resultado = new JeiffPeaService().listarTodos();
		logger.debug("QTD Jeiff/Pea: " + resultado.size());
		return resultado;
	}
	
	/**
	 * Listar Jeiff/Pea por id
	 * @param id
	 * @return
	 */
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public List<JeiffPea> getJeiffPea(@PathParam("id") int id){
		logger.debug("Listar Jeiff/Pea: " + id);
		List<JeiffPea> resultado = new JeiffPeaService().listarkey(id);
		return resultado;
	}
	
	/**
	 * Listar Jeiff/Pea por id do periodo
	 * @param idperiodo
	 * @return
	 */
	@Path("ListarPeriodo/{idperiodo}")
	@GET
	@Produces("application/json")
	public List<JeiffPea> getJeiffPeaPeriodo(@PathParam("idperiodo") int idperiodo){
		logger.debug("Listar Jeiff/Pea Periodo: " + idperiodo);
		List<JeiffPea> resultado = new JeiffPeaService().listarPeriodo(idperiodo);
		logger.debug("QTD Jeiff/Pea: " + resultado.size());
		return resultado;
	}
	
	/**
	 * listar Anexos de Jeiff/Pea
	 * @return anexos
	 */
	@Path("ListarAnexos")
	@GET
	@Produces("application/json")
	public List<Object> getAnexos(){
		logger.debug("Listar Anexos Jeiff/Pea");
		List<Object> resultado = new JeiffPeaService().listarAnexos();
		logger.debug("QTD Anexos Jeiff/Pea: " + resultado.size());
		return resultado;
	}
	
}
