package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.JornadaProfessorService;
import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.SemanaService;
import br.com.muranodesign.model.JornadaProfessor;
import br.com.muranodesign.model.OficinaProfessor;



//Alteração que pode mudar
@Path("JornadaProfessor")
public class JornadaProfessorResource {

	private Logger logger = Logger.getLogger(JornadaProfessorResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("horario") int horario,
			@FormParam("extra") int extra,
			@FormParam("dia") int dia,
			@FormParam("oficina_professor") int oficina_professor,
			@FormParam("professor") int professor,
			@FormParam("ocupado") int ocupado){
		
		JornadaProfessor resultado = new JornadaProfessor();
		
		if(action.equals("delete")){
			resultado = new JornadaProfessorService().deletarJornadaProfessor(new JornadaProfessorService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			JornadaProfessor jornada = new JornadaProfessor();
			
			jornada.setDia(new SemanaService().listarkey(dia).get(0));
			
			if(oficina_professor != 0){
				jornada.setOficina_professor(new OficinaProfessorService().listarkey(oficina_professor).get(0));
			}else{
				jornada.setExtra(extra);
			}
			
			jornada.setProfessor(new ProfessorFuncionarioService().listarkey(professor).get(0));
			jornada.setHorario(horario);
			jornada.setOcupado(0);
			
			resultado = new JornadaProfessorService().criarJornadaProfessor(jornada);
			
		}else if(action.equals("update")){
			JornadaProfessor jornada = new JornadaProfessorService().listarkey(id).get(0);			
			
			jornada.setDia(new SemanaService().listarkey(dia).get(0));
			
			if(oficina_professor != 0){
				jornada.setOficina_professor(new OficinaProfessorService().listarkey(oficina_professor).get(0));
			}else{
				jornada.setExtra(extra);
			}
			
			jornada.setHorario(horario);
			
			resultado = new JornadaProfessorService().atualizarJornadaProfessor(jornada);
			
		}else if(action.equals("ocupar")){
			JornadaProfessor jornada = new JornadaProfessorService().listarkey(id).get(0);		
			
			jornada.setOcupado(1);
			
			resultado = new  JornadaProfessorService().atualizarJornadaProfessor(jornada);
			
		}
		
		return Integer.toString(resultado.getIdjornada_professor());
	}
	
	
	
	@GET
	@Produces("application/json")
	public List<JornadaProfessor> getJornada() {
		logger.debug("Listar jornada ...");
		List<JornadaProfessor> resultado;
		 resultado = new JornadaProfessorService().listarTodos();
		 logger.debug("QTD jornada : " +  resultado.size());
		return resultado;
	}
	
	@Path("Total/{id}/{ciclo}")
	@GET
	@Produces("application/json")
	public long total(@PathParam("id") int id,@PathParam("ciclo") int ciclo){
		
		List<OficinaProfessor> oficina = new OficinaProfessorService().listarProfessor(id);
		List<OficinaProfessor> con = new ArrayList<OficinaProfessor>();
		
		for (OficinaProfessor oficinaProfessor : oficina) {
			if(oficinaProfessor.getOficina().getCiclo().getIdciclos() == ciclo){
				con.add(oficinaProfessor);
			}
		}
		
		
		return new JornadaProfessorService().Total(con.get(0).getProfessor().getIdprofessorFuncionario());
	}
	

	@Path("Disponivel/{id}/{ciclo}")
	@GET
	@Produces("application/json")
	public long disponivel(@PathParam("id") int id,@PathParam("ciclo") int ciclo){
		
		List<OficinaProfessor> oficina = new OficinaProfessorService().listarProfessor(id);
		List<OficinaProfessor> con = new ArrayList<OficinaProfessor>();
		
		for (OficinaProfessor oficinaProfessor : oficina) {
			if(oficinaProfessor.getOficina().getCiclo().getIdciclos() == ciclo){
				con.add(oficinaProfessor);
			}
		}
		
		return new JornadaProfessorService().Disponivel(con.get(0).getProfessor().getIdprofessorFuncionario());
	}
	
	
	@Path("Extra/{id}/{ciclo}")
	@GET
	@Produces("application/json")
	public long Extra(@PathParam("id") int id,@PathParam("ciclo") int ciclo){
		
		List<OficinaProfessor> oficina = new OficinaProfessorService().listarProfessor(id);
		List<OficinaProfessor> con = new ArrayList<OficinaProfessor>();
		
		for (OficinaProfessor oficinaProfessor : oficina) {
			if(oficinaProfessor.getOficina().getCiclo().getIdciclos() == ciclo){
				con.add(oficinaProfessor);
			}
		}
		
		return new JornadaProfessorService().extra(con.get(0).getProfessor().getIdprofessorFuncionario());
	}
	
	
	@Path("CompletaRotina/{id}/{ciclo}")
	@GET
	@Produces("application/json")
	public List<JornadaProfessor> CompletaRotina(@PathParam("id") int id,@PathParam("ciclo") int ciclo){
		
		List<OficinaProfessor> oficina = new OficinaProfessorService().listarProfessor(id);
		List<OficinaProfessor> con = new ArrayList<OficinaProfessor>();
		
		for (OficinaProfessor oficinaProfessor : oficina) {
			if(oficinaProfessor.getOficina().getCiclo().getIdciclos() == ciclo){
				con.add(oficinaProfessor);
			}
		}
		
		List<JornadaProfessor> jornada = new JornadaProfessorService().ListarTodos(con.get(0).getProfessor().getIdprofessorFuncionario());
		

		return jornada;
	}
}
