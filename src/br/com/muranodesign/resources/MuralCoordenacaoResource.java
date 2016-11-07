package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.MuralCoordenacaoService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.MuralCoordenacao;
import br.com.muranodesign.util.StringUtil;


@Path("MuralCoordenacao")
public class MuralCoordenacaoResource {
	
	private Logger logger = Logger.getLogger(MuralCoordenacaoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("professor") int professor,
			@FormParam("data") String data,
			@FormParam("mensagem") String mensagem,
			@FormParam("hora") String hora) {
		
		MuralCoordenacao resultado = new MuralCoordenacao();
		
		if (action.equals("delete"))
		{
			resultado = new MuralCoordenacaoService().deletarMuralCoordenacao(new MuralCoordenacaoService().listarkey(id).get(0));
		}
		else if (action.equals("create"))
		{
			StringUtil stringUtil = new StringUtil();
			
			resultado.setData(stringUtil.converteStringData(data));
			resultado.setHora(hora);
			resultado.setMensagem(mensagem);
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(professor).get(0));
			
			resultado = new MuralCoordenacaoService().criarMuralCoordenacao(resultado);
		}
		else if (action.equals("update"))
		{
			StringUtil stringUtil = new StringUtil();
			
			resultado = new MuralCoordenacaoService().listarkey(id).get(0);
			resultado.setData(stringUtil.converteStringData(data));
			resultado.setHora(hora);
			resultado.setMensagem(mensagem);
			resultado.setProfessor(new ProfessorFuncionarioService().listarkey(professor).get(0));
			
			resultado = new MuralCoordenacaoService().atualizarMuralCoordenacao(resultado);
		}
		
		return Integer.toString(resultado.getIdMuralCoordenacao());
	}
	
	@GET
	@Produces("application/json")
	public List<MuralCoordenacao> getMuralCoordenacao(){
		logger.debug("Listar Mural Coordenacao...");
		List<MuralCoordenacao> resultado = new MuralCoordenacaoService().listarTodos();
		logger.debug("QTD MuralCoordenacao : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<MuralCoordenacao> getMuralCoordenacao(@PathParam("id") int id){
		logger.debug("Listar Mural Coordenacao...");
		List<MuralCoordenacao> resultado = new MuralCoordenacaoService().listarkey(id);
		return resultado;
	}

}
