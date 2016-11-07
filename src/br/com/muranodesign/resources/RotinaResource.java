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

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AlunoAgrupamentoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.business.SemanaService;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.AlunoAgrupamento;
import br.com.muranodesign.model.Rotina;

@Path("Rotina")
public class RotinaResource {
	
	private Logger logger = Logger.getLogger(RotinaResource.class.getName());
	
	
	/**
	 * 
	 * @param action
	 * @param id
	 * @param hora
	 * @param idOficina
	 * @param ididAgrupamento
	 * @param idDia
	 * @param idSala
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idOficina") int idOficina,
			@FormParam("Hora") long Hora,
			@FormParam("idAgrupamento") int idAgrupamento,
			@FormParam("idDia") int idDia,
			@FormParam("anoLetivo") int anoLetivo,
			@FormParam("idSala") int idSala){
		
		
		
		Rotina resultado = new Rotina();
		
		
		if(action.equals("delete")){
			resultado = new RotinaService().deletarRotina(new RotinaService().listarkey(id).get(0));
		}else if(action.equals("create")){
			
			 
			
			Rotina rotina = new Rotina();
			
			List<Rotina> validação = new RotinaService().listarInconsistencia(Hora, idDia/*, idSala*/);
			
			List<AlunoAgrupamento> agrupamentoAlunos = new ArrayList<AlunoAgrupamento>();
			Agrupamento agrupamentoNovo = new AgrupamentoService().listarkey(idAgrupamento).get(0);
			
			
			if(!validação.isEmpty()){
				for(Rotina rotina2 : validação) {
					agrupamentoAlunos.addAll(new AlunoAgrupamentoService().listarAgrupamento(rotina2.getAgrupamento().getIdagrupamento()));
				}
				
				List<AlunoAgrupamento> agrupamentoAlunosNovo = new ArrayList<AlunoAgrupamento>();
				agrupamentoAlunosNovo.addAll(new AlunoAgrupamentoService().listarAgrupamento(agrupamentoNovo.getIdagrupamento()));
				
				for(int i = 0; i < agrupamentoAlunos.size();i++){
					for(int j = 0; j < agrupamentoAlunosNovo.size();j++){
						//if(agrupamentoAlunos.get(i).getAluno().getIdalunoVariavel() == agrupamentoAlunosNovo.get(j).getAluno().getIdalunoVariavel()){
						if(agrupamentoAlunos.get(i).getAluno().equals(agrupamentoAlunosNovo.get(j).getAluno())){
							return "Erro: O aluno de id "+agrupamentoAlunosNovo.get(j).getAluno().getIdalunoVariavel()+"já tem uma rotina para esse horario";
						}
					}
				}	
				//return "Erro: Já existe uma rotina para essa data, hora e sala. Favor escolher outra";
			}else{
			
				rotina.setHora(Hora);
				rotina.setDia(new SemanaService().listarkey(idDia).get(0));
				rotina.setOficina(new OficinaService().listarkey(idOficina).get(0));
				//rotina.setSala(new SalasService().listarkey(idSala).get(0));
				rotina.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
				rotina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
				
				resultado = new RotinaService().criarRotina(rotina);
			}
			
		}else if(action.equals("update")){
			
			Rotina rotina = new RotinaService().listarkey(id).get(0);
			
			List<Rotina> validação = new RotinaService().listarInconsistencia(Hora, idDia/*, idSala*/);
			
			List<AlunoAgrupamento> agrupamentoAlunos = new ArrayList<AlunoAgrupamento>();
			Agrupamento agrupamentoNovo = new AgrupamentoService().listarkey(idAgrupamento).get(0);
			
			
			if(!validação.isEmpty()){
				for(Rotina rotina2 : validação) {
					agrupamentoAlunos.addAll(new AlunoAgrupamentoService().listarAgrupamento(rotina2.getAgrupamento().getIdagrupamento()));
				}
				
				List<AlunoAgrupamento> agrupamentoAlunosNovo = new ArrayList<AlunoAgrupamento>();
				agrupamentoAlunosNovo.addAll(new AlunoAgrupamentoService().listarAgrupamento(agrupamentoNovo.getIdagrupamento()));
				
				for(int i = 0; i < agrupamentoAlunos.size();i++){
					for(int j = 0; j < agrupamentoAlunosNovo.size();j++){
						if(agrupamentoAlunos.get(i).getAluno().equals(agrupamentoAlunosNovo.get(j).getAluno())){
							return "Erro: O aluno de id "+agrupamentoAlunosNovo.get(j).getAluno().getIdalunoVariavel()+"já tem uma rotina para esse horario";
						}
					}
				}
						
			}else{
			
				rotina.setHora(Hora);
				rotina.setDia(new SemanaService().listarkey(idDia).get(0));
				rotina.setOficina(new OficinaService().listarkey(idOficina).get(0));
				rotina.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
				rotina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
				
				resultado = new RotinaService().atualizarRotina(rotina);
			}
		}
		
		return Integer.toString(resultado.getIdrotina());
	}
	
	/**
	 * Lista todos as rotinas 
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Rotina> getRotina() {
		logger.debug("Listar Rotina ...");
		List<Rotina> resultado;
		resultado = new RotinaService().listarTodos();
		logger.debug("QTD Rotina : " +resultado.size());
		return resultado;
	}
	
	/**
	 * Lista as rotinas que tem hora e dia da semana já registrados
	 * @param Hora
	 * @param idSemana
	 * @return list
	 */
	@Path("listarInconsistencia/{Hora}/{idSemana}")
	@GET
	@Produces("application/json")
	public List<Rotina> getlistarInconsistencia(@PathParam("Hora") long Hora, @PathParam("idSemana") int idSemana){
		logger.debug("Listar Rotina ...");
		List<Rotina> resultado;
		 resultado = new RotinaService().listarInconsistencia(Hora, idSemana);
		 logger.debug("QTD Rotina : " +  resultado.size());
		return resultado;
	}

	/**
	 * Listar por id
	 * @param idRotina
	 * @return
	 */
	@Path("listarId/{idRotina}")
	@GET
	@Produces("application/json")
	public List<Rotina> getListaPorId(@PathParam("idRotina") int idRotina){
		return new RotinaService().listarkey(idRotina);
	}
}
