package br.com.muranodesign.resources;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import br.com.muranodesign.business.BlogService;
import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.model.Blog;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.OficinaProfessor;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("Blog")
public class BlogResource {
	
	private Logger logger = Logger.getLogger(BlogResource.class.getName());
	
	/**
	 * Criar, deltera e alterar blog
	 * @param action
	 * @param id
	 * @param titulo
	 * @param Descricao
	 * @return
	 * @throws ParseException 
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("oficina") int oficina,
			@FormParam("titulo") String titulo,
			@FormParam("Descricao") String Descricao) throws ParseException{
		
		
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String k = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();  
		Date date = (Date)formatter.parse(k);
		
		
		Blog resultado = new Blog();
		
		
		if(action.equals("delete")){
			resultado = new BlogService().deletarBlog(new BlogService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			Blog blog = new Blog();
			
			blog.setDescricao(Descricao);
			blog.setTitulo(titulo);
			blog.setData(date);
			if (oficina != 0)
				blog.setOficina(new OficinaService().listarkey(oficina).get(0));
			else
				blog.setOficina(null);
			
			resultado = new BlogService().criarBlog(blog);
			
		}else if(action.equals("update")){
			Blog blog = new BlogService().listarkey(id).get(0);
			
			blog.setDescricao(Descricao);
			blog.setTitulo(titulo);
			resultado = new BlogService().atualizarBloga(blog);
		}
		
		return Integer.toString(resultado.getIdblog());
	}
	
	
	
	@POST
	@Path("upload/Blog/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Blog eventoAction(
			@PathParam("id") String strId,
			@FormDataParam("fotoAluno") InputStream uploadedInputStream,
			@FormDataParam("fotoAluno") FormDataContentDisposition fileDetail
			){
		
		Blog blog = new Blog();
		int id = Integer.parseInt(strId);
		List<Blog> Lblog = new BlogService().listarkey(id);
		blog = Lblog.get(0);
		
		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;

		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		String anexo = "http://177.55.99.90/files/" + arquivo;

		blog.setImagem(anexo);
		Blog result = new BlogService().atualizarBloga(blog);
		
		return result;
	}
	
	@GET
	@Path("DeletarImagem/{id}")
	@Produces("text/plain")
	public String deletarImagem(@PathParam("id") int id){
		Blog blog = new BlogService().listarkey(id).get(0);
		
		blog.setImagem(null);
		new BlogService().atualizarBloga(blog);
		
		return Integer.toString(id);
	}
	
	@GET
	@Path("Imagem/{id}")
	@Produces("text/plain")
	public String getImagem(@PathParam("id") int id){
		
		Blog blog = new BlogService().listarkey(id).get(0);
		
		return blog.getImagem();
	}
	
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<Blog> getBlog() {
		logger.debug("Listar Blog ...");
		List<Blog> resultado;
		 resultado = new BlogService().listarTodos();
		 logger.debug("QTD Oficina : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("BlogOficina/{id}")
	@GET
	@Produces("application/json")
	public List<Blog> getBlogOficina(@PathParam("id") int id) {
		logger.debug("Lista Blog por Oficina " + id);
		List<Blog> obj = new BlogService().listarOficina(id);
		return obj;
	}
	
	@Path("BlogProfessor/{id}")
	@GET
	@Produces("application/json")
	public List<Blog> getBlogProfessor(@PathParam("id") int id){
		logger.debug("Lista Blog por Professor " + id);
		List<Blog> resultado = new ArrayList<Blog>();
		List<OficinaProfessor> oficinasProfessor;
		List<Oficina> oficinas = new ArrayList<Oficina>();
		//List<Cores> cores = new ArrayList<Cores>();
		
		oficinasProfessor = new OficinaProfessorService().listarProfessor(id);
		
		for (OficinaProfessor oficinaProfessor : oficinasProfessor) {
			oficinas.add(new OficinaService().listarkey(oficinaProfessor.getOficina().getIdoficina()).get(0));
		}
		
		for (Oficina oficina : oficinas){
			resultado.addAll(new BlogService().listarOficina(oficina.getIdoficina()));
		}
		
		return resultado;
	}

	/**
	 * 
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("upload/imagem/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Blog upload(

	@PathParam("id") String strId,
			@FormDataParam("fotoAluno") InputStream uploadedInputStream,
			@FormDataParam("fotoAluno") FormDataContentDisposition fileDetail

	) {
		
		Blog blog = new Blog();
		int id = Integer.parseInt(strId);
		List<Blog> rsblog;
		rsblog = new BlogService().listarkey(id);
		blog = rsblog.get(0);

		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;

		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		String anexo = "http://177.55.99.90/files/" + arquivo;

		logger.info("anexo" + anexo);

		blog.setImagem(anexo);
		
		Blog resultado = new BlogService().atualizarBloga(blog); //ProducaoAlunoService().atualizarProducaoAluno(prod);

		return resultado;

	}
}
