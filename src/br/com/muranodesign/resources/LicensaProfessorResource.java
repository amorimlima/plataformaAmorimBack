package br.com.muranodesign.resources;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.LicensaProfessorService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.LicensaProfessor;
import br.com.muranodesign.util.StringUtil;


@Path("LicensaProfessor")
public class LicensaProfessorResource {

	private Logger logger = Logger.getLogger(LicensaProfessorResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(	@FormParam("id") int id,
								@FormParam("inicio") String inicio,
								@FormParam("fim") String fim,
								@FormParam("idProfessor") int idProfesor,
								@FormParam("action") String action)
	{
		LicensaProfessor result = new LicensaProfessor();
		
		if (action.equals("create")){
			
			StringUtil stringUtil = new StringUtil();
			
			Date dataInicio = stringUtil.converteStringData(inicio);
			Date dataFim = stringUtil.converteStringData(fim);
			
			result.setDataInicio(dataInicio);
			result.setDataFim(dataFim);
			result.setProfessor(new ProfessorFuncionarioService().listarkey(idProfesor).get(0));
			
			if (id == 0)
				result = new LicensaProfessorService().criarLicensaProfessor(result);
			else{
				result.setIdLicensaProfessor(id);
				result = new LicensaProfessorService().updateLicensaProfessor(result);
			}
			
			
		}
		
		else if (action.equals("delete")){
			result = new LicensaProfessorService().listarkey(id).get(0);
			result = new LicensaProfessorService().deletarLicensaProfessor(result);
		}
		
		else if (action.equals("update")){
			result = new LicensaProfessorService().listarkey(id).get(0);
			
			StringUtil stringUtil = new StringUtil();
			
			Date dataInicio = stringUtil.converteStringData(inicio);
			Date dataFim = stringUtil.converteStringData(fim);
			
			result.setDataInicio(dataInicio);
			result.setDataFim(dataFim);
			result.setProfessor(new ProfessorFuncionarioService().listarkey(idProfesor).get(0));
			
			result = new LicensaProfessorService().updateLicensaProfessor(result);
		}
		
		return Integer.toString(result.getIdLicensaProfessor());
	}
	
	@Path("listarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<LicensaProfessor> listarProfessor(@PathParam("idProfessor") int idProfessor){
		return new LicensaProfessorService().listarProfessor(idProfessor);
	}
	
	@Path("diasLicencaProfessor/{idProfessor}")
	@GET
	@Produces("text/plain")
	public String diasLicenca(@PathParam("idProfessor") int idProfessor){
		
		List<LicensaProfessor> listaLicenca = new LicensaProfessorService().listarProfessor(idProfessor);
		int dias = 0;
		for (LicensaProfessor licensaProfessor : listaLicenca) {
			dias += TimeUnit.MILLISECONDS.toDays(licensaProfessor.getDataFim().getTime() - licensaProfessor.getDataInicio().getTime());
		}
		
		return Integer.toString(dias);
	}
	
}
