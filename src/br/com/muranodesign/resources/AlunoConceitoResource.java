package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoConceitoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.model.AlunoConceito;
import br.com.muranodesign.model.AlunoConceito.Aprovacao;
import br.com.muranodesign.model.AlunoConceito.Conceito;


@Path("AlunoConceito")
public class AlunoConceitoResource {

	private Logger logger = Logger.getLogger(AlunoConceitoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("id") int id,
			@FormParam("action") String action,
			@FormParam("alunoVariavel") int alunoVariavel,
			@FormParam("aprovado") String aprovado,
			@FormParam("port_1sem") String port_1sem,
			@FormParam("port_2sem") String port_2sem,
			@FormParam("mat_1sem") String mat_1sem,
			@FormParam("mat_2sem") String mat_2sem,
			@FormParam("artes_1sem") String artes_1sem,
			@FormParam("artes_2sem") String artes_2sem,
			@FormParam("cien_1sem") String cien_1sem,
			@FormParam("cien_2sem") String cien_2sem,
			@FormParam("edFis_1sem") String edFis_1sem,
			@FormParam("edFis_2sem") String edFis_2sem,
			@FormParam("geo_1sem") String geo_1sem,
			@FormParam("geo_2sem") String geo_2sem,
			@FormParam("hist_1sem") String hist_1sem,
			@FormParam("hist_2sem") String hist_2sem,
			@FormParam("ingles_1sem") String ingles_1sem,
			@FormParam("ingles_2sem") String ingles_2sem
			){
		
		AlunoConceito resultado = new AlunoConceito();
		
		
		if (action.equals("create")){
			resultado.setAlunoVariavel(new AlunoVariavelService().listarkey(alunoVariavel).get(0));
			
			resultado.setAprovado(Aprovacao.valueOf(aprovado));
			resultado.setArtes_1sem(Conceito.valueOf(artes_1sem));
			resultado.setArtes_2sem(Conceito.valueOf(artes_2sem));
			resultado.setCien_1sem(Conceito.valueOf(cien_1sem));
			resultado.setCien_2sem(Conceito.valueOf(cien_2sem));
			resultado.setEdFis_1sem(Conceito.valueOf(edFis_1sem));
			resultado.setEdFis_2sem(Conceito.valueOf(edFis_2sem));
			resultado.setGeo_1sem(Conceito.valueOf(geo_1sem));
			resultado.setGeo_2sem(Conceito.valueOf(geo_2sem));
			resultado.setHist_1sem(Conceito.valueOf(hist_1sem));
			resultado.setHist_2sem(Conceito.valueOf(hist_2sem));
			resultado.setIngles_1sem(Conceito.valueOf(ingles_1sem));
			resultado.setIngles_2sem(Conceito.valueOf(ingles_2sem));
			resultado.setMat_1sem(Conceito.valueOf(mat_1sem));
			resultado.setMat_2sem(Conceito.valueOf(mat_2sem));
			resultado.setPort_1sem(Conceito.valueOf(port_1sem));
			resultado.setPort_2sem(Conceito.valueOf(port_2sem));

			resultado = new AlunoConceitoService().criar(resultado);
		}
		
		else if (action.equals("update")){
			resultado = new AlunoConceitoService().listarKey(id).get(0);
			resultado.setAlunoVariavel(new AlunoVariavelService().listarkey(alunoVariavel).get(0));
			
			resultado.setAprovado(Aprovacao.valueOf(aprovado));
			resultado.setArtes_1sem(Conceito.valueOf(artes_1sem));
			resultado.setArtes_2sem(Conceito.valueOf(artes_2sem));
			resultado.setCien_1sem(Conceito.valueOf(cien_1sem));
			resultado.setCien_2sem(Conceito.valueOf(cien_2sem));
			resultado.setEdFis_1sem(Conceito.valueOf(edFis_1sem));
			resultado.setEdFis_2sem(Conceito.valueOf(edFis_2sem));
			resultado.setGeo_1sem(Conceito.valueOf(geo_1sem));
			resultado.setGeo_2sem(Conceito.valueOf(geo_2sem));
			resultado.setHist_1sem(Conceito.valueOf(hist_1sem));
			resultado.setHist_2sem(Conceito.valueOf(hist_2sem));
			resultado.setIngles_1sem(Conceito.valueOf(ingles_1sem));
			resultado.setIngles_2sem(Conceito.valueOf(ingles_2sem));
			resultado.setMat_1sem(Conceito.valueOf(mat_1sem));
			resultado.setMat_2sem(Conceito.valueOf(mat_2sem));
			resultado.setPort_1sem(Conceito.valueOf(port_1sem));
			resultado.setPort_2sem(Conceito.valueOf(port_2sem));
		
			resultado = new AlunoConceitoService().update(resultado);
		}
		else if (action.equals("delete")){
			resultado = new AlunoConceitoService().deletar(new AlunoConceitoService().listarKey(id).get(0));
		}
		
		return Integer.toString(resultado.getIdAlunoConceito());
		
	}
	
	@GET
	@Produces("application/json")
	public List<AlunoConceito> getAlunoConceito(){
		logger.debug("Listar Aluno Conceito ...");
		List<AlunoConceito> resultado;
		resultado = new AlunoConceitoService().listarTodos();
		logger.debug("QTD Aluno Conceito : " +  resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<AlunoConceito> getCompensacao(@PathParam("id") int id){
		logger.debug("Listar Aluno Conceito id: "+id+"...");
		List<AlunoConceito> resultado;
		resultado = new AlunoConceitoService().listarKey(id);
		return resultado;
	}
	
}
