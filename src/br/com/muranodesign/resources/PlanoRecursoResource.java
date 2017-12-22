package br.com.muranodesign.resources;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.PlanejamentoAulaService;
import br.com.muranodesign.business.PlanoRecursoService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RecursoAprendizagemService;
import br.com.muranodesign.business.TipoRecursoAprendizagemService;
import br.com.muranodesign.model.PlanoRecurso;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/** Classe responsável por disponibilizar os serviços referentes a tabela associativa entre plano de aula e recurso aprendizagem.
 * @author Pedro Henrique dos Santos
 * @see PlanoAulaService
 * @see RecursoAprendizagemService
 */
@Path("PlanoRecurso")
public class PlanoRecursoResource {
	
	private Logger logger = Logger.getLogger(PlanoRecurso.class.getName());
	/** Método responsável por disponibilizar serviços de crud
	 * @author Pedro Henrique dos Santos
	 * @param action Enviado pelo client, define qual operação será realizada
	 * @param id Identificador da tabela plano_recurso
	 * @param idPlanoAula Identificador da tabela plano_aula
	 * @param idRecursoAprendizagem Identificador da tabela recurso_aprendizagem
	 * @return Retorna o identificador do objeto que foi manipulado
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("nome") String nome,
			@FormParam("descricao") String descricao,
			@FormParam("link") String link,
			@FormParam("visibilidade") int visibilidade,
			@FormParam("tipoRecurso") int tipoRecurso,
			@FormParam("idPlanejamentoAula") int idPlanejamentoAula){
		
		PlanoRecurso p = new PlanoRecurso();
		
		if(action.equalsIgnoreCase("delete"))
			p = new PlanoRecursoService().deletarPlanoRecurso(new PlanoRecursoService().listarkey(id).get(0));
		else if(action.equalsIgnoreCase("create")){
			p.setNome(nome);
			p.setDescricao(descricao);
			p.setLink(link);
			p.setVisibilidade(visibilidade);
			p.setTipoRecurso(new TipoRecursoAprendizagemService().listarkey(tipoRecurso).get(0));
			if (idPlanejamentoAula != 0)
				p.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamentoAula).get(0));
			p = new PlanoRecursoService().criarPlanoRecurso(p);
		}
		else if(action.equalsIgnoreCase("update")){
			p = new PlanoRecursoService().listarkey(id).get(0);
			p.setNome(nome);
			p.setDescricao(descricao);
			p.setLink(link);
			p.setVisibilidade(visibilidade);
			p.setTipoRecurso(new TipoRecursoAprendizagemService().listarkey(tipoRecurso).get(0));
			p.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamentoAula).get(0));
			p = new PlanoRecursoService().atualizarPlanoRecurso(p);
		}
		return Integer.toString(p.getIdPlanoRecurso());
	}
	
	@Path("SetPlanejamento")
	@POST
	@Produces("text/plain")
	public String setPlanejamento(
			@FormParam("recursos") String recursos,
			@FormParam("idPlanejamentoAula") int idPlanejamentoAula){
		
		String r = "";
		
		String [] arrayRecursos = recursos.split(";");
		for (String recurso : arrayRecursos) {
			PlanoRecurso resultado = new PlanoRecursoService().listarkey(Integer.parseInt(recurso)).get(0);
			resultado.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamentoAula).get(0));
			resultado = new PlanoRecursoService().atualizarPlanoRecurso(resultado);
			r += resultado.getIdPlanoRecurso() + "; ";
		}
		
		return r;
	}
	
	@Path("Clonar")
	@POST
	@Produces("text/plain")
	public String clonar(
			@FormParam ("id") int id,
			@FormParam ("idPlanejamentoAula") int idPlanejamento){
		
		PlanoRecurso resultado = new PlanoRecurso();
		PlanoRecurso clone = new PlanoRecursoService().listarkey(id).get(0);
		
		resultado.setArquivo(clone.getArquivo());
		resultado.setDescricao(clone.getDescricao());
		resultado.setLink(clone.getLink());
		resultado.setNome(clone.getNome());
		resultado.setPlanejamentoAula(new PlanejamentoAulaService().listarkey(idPlanejamento).get(0));
		resultado.setTipoRecurso(clone.getTipoRecurso());
		resultado.setVisibilidade(clone.getVisibilidade());
		
		resultado = new PlanoRecursoService().criarPlanoRecurso(resultado);
		
		return Integer.toString(resultado.getIdPlanoRecurso());
		
	}
	
	@POST
	@Path("upload/imagem/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public PlanoRecurso eventoAction(
			@PathParam("id") int id,
			@FormDataParam("imagem") InputStream uploadedInputStream,
			@FormDataParam("imagem") FormDataContentDisposition fileDetail) 
	{
		PlanoRecurso resultado = new PlanoRecurso();
		
		resultado = new PlanoRecursoService().listarkey(id).get(0);
		
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		String anexo = "http://177.55.99.90/files/"+ arquivo;
		
		Upload upload = new Upload (); 
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		logger.info("anexo" + anexo);     
		
		resultado.setArquivo(anexo);
		
		resultado =  new PlanoRecursoService().atualizarPlanoRecurso(resultado);

		return resultado;
	}
	
	/** Método que disponibiliza a listagem de registros em plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @return lstPlanoRecurso Lista com registros
	 */
	@GET
	@Produces("application/json")
	public List<PlanoRecurso> getPlanoRecurso(){
		logger.debug("Listar Plano Recurso...");
		List<PlanoRecurso> lstPlanoRecurso;
		lstPlanoRecurso = new PlanoRecursoService().listarTodos();
		logger.debug("Qt PlanoRecurso: " + lstPlanoRecurso.size());
		return lstPlanoRecurso;
	}
	
	/** Método que busca determinado registro de plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @param id Identificador informado pelo usuário
	 * @return Objeto correspondente ao id
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<PlanoRecurso> getById(@PathParam("id") int id){
		return new PlanoRecursoService().listarkey(id);
	}
	
	@Path("BuscarRecurso/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanoRecurso> buscarRecurso(@PathParam("idProfessor") int idProfessor, @QueryParam("nome") String nome, @QueryParam("tipo") int tipo){
		return new PlanoRecursoService().buscarRecurso(nome, tipo, new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
	}
	
	@Path("RecursosPlano/{idPlano}")
	@GET
	@Produces("application/json")
	public List<PlanoRecurso> listarRecursosPlano(@PathParam("idPlano") int idPlano){
		return new PlanoRecursoService().recursoPlano(idPlano);
	}
	
	/** Método que busca determinado registro de plano_recurso
	 * @author Pedro Henrique dos Santos
	 * @param id Identificador informado pelo usuário
	 * @return Objeto correspondente ao id
	 */
	@Path("TodosRecursosPlano/{idPlano}")
	@GET
	@Produces("application/json")
	public List<PlanoRecurso> listarRecursosPlanoTotal(@PathParam("idPlano") int idPlano){
		return new PlanoRecursoService().recursoPlanoTotal(idPlano);
	}
}
