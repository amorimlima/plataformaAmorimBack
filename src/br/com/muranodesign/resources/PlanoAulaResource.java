package br.com.muranodesign.resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.BlogService;
import br.com.muranodesign.business.PlanoAulaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.PlanoAula;
import br.com.muranodesign.util.StringUtil;


@Path("PlanoAula")
public class PlanoAulaResource {
	
	private Logger logger = Logger.getLogger(PlanoAulaResource.class.getName());
	
	/**
	 * Criar, deletar e atualizar
	 * @param action
	 * @param id
	 * @param idBlog
	 * @param idProfessor
	 * @param data_ini
	 * @param data_fim
	 * @param objetivos
	 * @param tarefa_casa
	 * @param registro_atividade
	 * @return
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idBlog") int idBlog,
			@FormParam("idProfessor") int idProfessor,
			@FormParam("data_ini") String data_ini,
			@FormParam("data_fim") String data_fim,
			@FormParam("objetivos") String objetivos,
			@FormParam("tarefa_casa") String tarefa_casa,
			@FormParam("registro_atividade") String registro_atividade){
		
		PlanoAula resultado = new PlanoAula();
		StringUtil stringUtil = new StringUtil();
		
		if(action.equals("delete")){
			resultado = new PlanoAulaService().deletarPlanoAula(new PlanoAulaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			PlanoAula plano = new PlanoAula();

			plano.setData_ini(stringUtil.converteStringData(data_ini));
			plano.setData_fim(stringUtil.converteStringData(data_fim));
			plano.setObjetivos(objetivos);
			plano.setTarefa_casa(tarefa_casa);
			plano.setRegistro_atividade(registro_atividade);
			if(idBlog != 0){
				plano.setBlog(new BlogService().listarkey(idBlog).get(0));
			}
			
			plano.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new PlanoAulaService().criarPlanoAula(plano);
			
		}else if(action.equals("update")){
			PlanoAula plano = new PlanoAulaService().listarkey(id).get(0);
			
			plano.setData_ini(stringUtil.converteStringData(data_ini));
			plano.setData_fim(stringUtil.converteStringData(data_fim));
			plano.setObjetivos(objetivos);
			plano.setTarefa_casa(tarefa_casa);
			plano.setRegistro_atividade(registro_atividade);
			if(idBlog != 0){
				plano.setBlog(new BlogService().listarkey(idBlog).get(0));
			}
			plano.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			
			resultado = new PlanoAulaService().atualizarPlanoAula(plano);
		}
		
		return Integer.toString(resultado.getIdplano_aula());
	}
	
	/**
	 * Plano Aula
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<PlanoAula> getPlanoAula() {
		logger.debug("Listar PlanoAula ...");
		List<PlanoAula> resultado;
		 resultado = new PlanoAulaService().listarTodos();
		 logger.debug("QTD PlanoAula : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Listar por id
	 * @param id
	 * @return
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<PlanoAula> getPlanoAula(@PathParam("id") int id) {
		
		return new PlanoAulaService().listarkey(id);
	}
	
	/**
	 * Range Data
	 * @return
	 */
	@Path("RangeData/")
	@GET
	@Produces("application/json")
	public List<PlanoAula> getRange(){
		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
	
		StringUtil stringUtil = new StringUtil();
		
		return new PlanoAulaService().range(stringUtil.converteStringData(data));
	}
	
	/**
	 * Listar ultimo por Professor
	 * @param idProfessor
	 * @return
	 */
	@Path("Ultimo/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanoAula> getUltimo(@PathParam("idProfessor") int idProfessor){
		return new PlanoAulaService().listarUltimo(idProfessor);
	}

	/**
	 * Listar professor Data
	 * @param idProfessor
	 * @return
	 */
	@Path("ProfessorData/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanoAula> getProfessorData(@PathParam("idProfessor") int idProfessor){
		
		
		Calendar c = Calendar.getInstance();
		int anoInt = c.get(Calendar.YEAR);
		String anoString = Integer.toString(anoInt - 2000);
		
		StringUtil  t = new StringUtil();
		
		return new PlanoAulaService().listarProfessor(idProfessor, t.converteStringData(anoString+"-01-01")); 
	}
	
	
}
