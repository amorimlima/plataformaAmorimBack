package br.com.muranodesign.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.EmprestimoLivroService;
import br.com.muranodesign.model.EmprestimoLivro;
import br.com.muranodesign.util.StringUtil;

@Path("EmprestimosLivro")
public class EmprestimoLivroResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(EmprestimoLivroResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("livro") String livro,
			@FormParam("retirada") String retirada,
			@FormParam("retorno") String retorno,
			@FormParam("aluno") int aluno)
	{
		EmprestimoLivro resultado = new EmprestimoLivro();
		
		StringUtil util = new StringUtil();
		Date dataRetirada = util.converteStringData(retirada);
		Date dataRetorno = util.converteStringData(retorno);
		
		if (action.equals("create")){
			resultado.setLivro(livro);
			resultado.setRetirada(dataRetirada);
			resultado.setRetorno(dataRetorno);
			resultado.setAluno(new AlunoService().listarkey(aluno).get(0));
			
			if (id != 0)
			{
				resultado.setIdEmprestimoLivro(id);
				resultado = new EmprestimoLivroService().update(resultado);
			}
			else
				resultado = new EmprestimoLivroService().criar(resultado);
		}
		else if (action.equals("update"))
		{
			resultado = new EmprestimoLivroService().listarkey(id).get(0);
			resultado.setLivro(livro);
			resultado.setRetirada(dataRetirada);
			resultado.setRetorno(dataRetorno);
			resultado.setAluno(new AlunoService().listarkey(aluno).get(0));
			resultado = new EmprestimoLivroService().update(resultado);
		}
		else if (action.equals("delete"))
			resultado = new EmprestimoLivroService().deletar(new EmprestimoLivroService().listarkey(id).get(0));
		
		return "";
	}
	
	@GET
	@Produces("application/json")
	public List<EmprestimoLivro> getEmprestimoLivro(){
		logger.debug("Listar Emprestimo ...");
		List<EmprestimoLivro> resultado;
		resultado = new EmprestimoLivroService().listarTodos();
		logger.debug("QTD Emprestimo : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<EmprestimoLivro> getEmprestimoLivro(@PathParam("id") int id){
		logger.debug("Listar Emprestimo id: "+id+"...");
		List<EmprestimoLivro> resultado;
		resultado = new EmprestimoLivroService().listarkey(id);
		return resultado;
	}
	
}
