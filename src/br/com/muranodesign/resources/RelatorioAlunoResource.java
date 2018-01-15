package br.com.muranodesign.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RelatorioAlunoService;
import br.com.muranodesign.model.RelatorioAluno;
import br.com.muranodesign.util.StringUtil;

/**
 * 
 * @author Kevyn
 *
 */
@Path("RelatorioAluno")
public class RelatorioAlunoResource {
	
	private Logger logger = Logger.getLogger(RelatorioAlunoResource.class.getName());

	/**
	 * Deletar, alterar e criar Relatorio Aluno
	 * @throws ParseException 
	 * 
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("relatorio") String relatorio,
			@FormParam("alunoVariavel") int aluno,
			@FormParam("anoLetivo") String anoLetivo,
			@FormParam("professor") int professor,
			@FormParam("agrupamento") int agrupamento,
			@FormParam("oficina") int oficina) throws ParseException{
		
		RelatorioAluno resultado = new RelatorioAluno();
		
		if (action.equals("delete"))
		{
			resultado = new RelatorioAlunoService().deletarOficina(new RelatorioAlunoService().listarkey(id).get(0));
		}
		
		else if (action.equals("create"))
		{
			Date dataT = new Date();		
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");		
			String dataS = formatter.format(dataT);
			Date data = (Date)formatter.parse(dataS);
			
			resultado.setRelatorio(relatorio);
			if (aluno != 0)
				resultado.setAlunoVariavel(new AlunoVariavelService().listarkey(aluno).get(0));
			if (agrupamento != 0)
				resultado.setAgrupamento(new AgrupamentoService().listarkey(agrupamento).get(0));
			resultado.setOficina(new OficinaService().listarkey(oficina).get(0));
			resultado.setAnoLetivo(new AnoLetivoService().listarAnoLetivo(anoLetivo).get(0));
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(professor).get(0));
			resultado.setData(data);
			resultado = new RelatorioAlunoService().criarRelatorioAluno(resultado);
		}
		else if (action.equals("update"))
		{
			resultado.setIdrelatorioAluno(id);
			resultado.setRelatorio(relatorio);
			if (aluno != 0)
				resultado.setAlunoVariavel(new AlunoVariavelService().listarkey(aluno).get(0));
			if (agrupamento != 0)
				resultado.setAgrupamento(new AgrupamentoService().listarkey(agrupamento).get(0));
			resultado.setOficina(new OficinaService().listarkey(oficina).get(0));
			resultado.setAnoLetivo(new AnoLetivoService().listarAnoLetivo(anoLetivo).get(0));
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(professor).get(0));
			resultado.setData(new RelatorioAlunoService().listarkey(id).get(0).getData());
			resultado = new RelatorioAlunoService().atualizarRelatorioAluno(resultado);
		}
		
		
		return Integer.toString(resultado.getIdrelatorioAluno());
	}
	
	/**
	 * Lista Relatorio Aluno
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getRelatorioAluno(){
		
		logger.debug("listar Relatorio Aluno...");
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarTodos();
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		
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
	public List<RelatorioAluno> getRelatorioAluno(@PathParam("id") int id){
		logger.debug("listar Relatorio Aluno ID: " + id);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarkey(id);
		return resultado;
	}
	
	/**
	 * Listar Aluno
	 * @param aluno
	 * @return
	 */
	@Path("ListarAluno/{idAluno}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getByAluno(@PathParam("idAluno") int aluno){
		logger.debug("listar Relatorio Aluno... Listando por aluno ID: " + aluno);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarAluno(aluno);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("RelatoriosAlunoProfessor/{idAluno}/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> relatoriosAlunoProfessor(@PathParam("idAluno") int idAluno, @PathParam("idProfessor") int idProfessor){
		logger.debug("listar Relatorio Aluno... Listando por professor ID: " + idProfessor + " aluno ID: "+idAluno);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().relatoriosAlunoProfessor(idAluno, idProfessor);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	/**
	 * Listar Por Professor
	 * @param professor
	 * @return
	 */
	@Path("ListarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getByProfessor(@PathParam("idProfessor") int professor){
		logger.debug("listar Relatorio Aluno... Listando por professor ID: " + professor);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarProfessor(professor);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("ListarProfessorAluno/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getAlunoByProfessor(@PathParam("idProfessor") int professor){
		logger.debug("listar Relatorio Aluno... Listando aluno por professor ID: " + professor);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarAlunoProfessor(professor);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("ListarAgrupamentoAluno/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getAgrupamentoByProfessor(@PathParam("idProfessor") int professor){
		logger.debug("listar Relatorio Aluno... Listando aluno por professor ID: " + professor);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarAgrupamentoProfessor(professor);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("ListarAgrupamento/{idAgrupamento}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> getByAgrupamento(@PathParam("idAgrupamento") int agrupamento){
		logger.debug("listar Relatorio Aluno... Listando por agrupamento ID: " + agrupamento);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().listarAgrupamento(agrupamento);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("RelatoriosAgrupamentoProfessor/{idAgrupamento}/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> relatoriosAgrupamentoProfessor(@PathParam("idAgrupamento") int idAgrupamento, @PathParam("idProfessor") int idProfessor){
		logger.debug("listar Relatorio Aluno... Listando por professor ID: " + idProfessor + " agrupamento ID: "+idAgrupamento);
		List<RelatorioAluno> resultado = new RelatorioAlunoService().relatoriosAgrupamentoProfessor(idAgrupamento, idProfessor);
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
	@Path("RelatoriosAlunoData/{idAluno}/{Oficinas}/{inicio}/{fim}/{tipo}")
	@GET
	@Produces("application/json")
	public List<RelatorioAluno> relatoriosAlunoData(
		@PathParam("idAluno") int idAluno,
		@PathParam("Oficinas") String oficinas,
		@PathParam("inicio") String inicio,
		@PathParam("fim") String fim,
		@PathParam("tipo") int tipo){
		logger.debug("listar Relatorio Aluno...");
		
		StringUtil stringUtil = new StringUtil();
		
		String[] oficinasString = oficinas.split(",");
		Integer[] idOficinas = new Integer[oficinasString.length];
		for (int i = 0; i < idOficinas.length; i++) {
			idOficinas[i] = Integer.parseInt(oficinasString[i]);
		}
		
		List<RelatorioAluno> resultado = new ArrayList<RelatorioAluno>();
		
		if (tipo % 2 == 0)
			resultado.addAll(new RelatorioAlunoService().RelatoriosAlunoData(
				new AlunoVariavelService().listarkey(idAluno).get(0),
				idOficinas,
				stringUtil.converteStringData(inicio),
				stringUtil.converteStringData(fim)));
		
		if (tipo % 3 == 0)
			resultado.addAll(new RelatorioAlunoService().RelatoriosAgrupamentoData(
				new AlunoVariavelService().listarkey(idAluno).get(0),
				idOficinas,
				stringUtil.converteStringData(inicio),
				stringUtil.converteStringData(fim)));
		
		logger.debug("QTD Relatorio Aluno:" + resultado.size());
		return resultado;
	}
	
}

