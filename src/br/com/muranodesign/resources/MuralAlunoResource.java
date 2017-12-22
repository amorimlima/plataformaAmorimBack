package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.MuralAlunoService;
import br.com.muranodesign.business.MuralService;
import br.com.muranodesign.model.MuralAluno;

/**
 * 
 * @author Kevyn
 *
 */
@Path("MuralAluno")
public class MuralAlunoResource {
	private Logger logger = Logger.getLogger(MuralAlunoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("alunoVariavel") int alunoVariavel,
			@FormParam("mural") int mural){
		
		MuralAluno resultado = new MuralAluno();
		
		if (action.equals("delete"))
		{
			resultado = new MuralAlunoService().deletarMuralAluno(new MuralAlunoService().listarkey(id).get(0));
			
		}
		else if (action.equals("create"))
		{			
			resultado.setAlunoVariavel(new AlunoVariavelService().listarkey(alunoVariavel).get(0));
			resultado.setMural(new MuralService().listarkey(mural).get(0));
			resultado = new MuralAlunoService().criarMuralAluno(resultado);
		}
		return Integer.toString(resultado.getIdMuralAluno());
	}
	
	@GET
	@Produces("application/json")
	public List<MuralAluno> getMuralAluno(){
		logger.debug("Listando Mural Aluno...");
		List<MuralAluno> resultado = new MuralAlunoService().listarall();
		logger.debug("QTD Mural Aluno: " + resultado.size());
		return resultado;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public List<MuralAluno> getMuralAluno(@PathParam("id") int id){
		logger.debug("Listando Mural Aluno...");
		List<MuralAluno> resultado = new MuralAlunoService().listarkey(id);
		logger.debug("QTD Mural Aluno: " + resultado.size());
		return resultado;
	}
	
	@GET
	@Path("ListarAluno/{idaluno}")
	@Produces("application/json")
	public List<MuralAluno> getMuralAlunoByAluno(@PathParam("idaluno") int idaluno){
		logger.debug("Listando Mural Aluno por Aluno. Id: " + idaluno);
		List<MuralAluno> resultado = new MuralAlunoService().listarAluno(idaluno);
		logger.debug("QTD Mural Aluno: " + idaluno);
		return resultado;
	}	
}
