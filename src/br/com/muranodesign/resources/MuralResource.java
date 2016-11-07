package br.com.muranodesign.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AlunoAgrupamentoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoEstudoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.MuralAlunoService;
import br.com.muranodesign.business.MuralService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.AlunoAgrupamento;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Mural;
import br.com.muranodesign.model.MuralAluno;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.Rotina;
import br.com.muranodesign.model.Tutoria;


@Path("Mural")
public class MuralResource {
	
	private Logger logger = Logger.getLogger(MuralResource.class.getName());
	
	/**
	 * create, update e delete de mural
	 * @param action
	 * @param id
	 * @param mensagem
	 * @param idProfessor
	 * @param idAgrupamento
	 * @param idOficina
	 * @param tutor
	 * @param ano
	 * @param idAluno
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("mensagem") String mensagem,
			@FormParam("idProfessor") int idProfessor,
			@FormParam("agrupamento") int idAgrupamento,
			@FormParam("oficina") int idOficina,
			@FormParam("tutoria") int tutor,
			@FormParam("grupo") int grupo,
			@FormParam("ano") String ano,
			@FormParam("idAluno") int idAluno) throws ParseException{
		
		
		if(action.equals("delete")){
			
			List<MuralAluno> muralAlunoList = new MuralAlunoService().listarMural(id);
			for (MuralAluno muralAluno : muralAlunoList) {
				new MuralAlunoService().deletarMuralAluno(muralAluno);
			}
			new MuralService().deletarMural(new MuralService().listarkey(id).get(0));
			return Integer.toString(id);
			
		}
		else if (action.equals("update")){
			Mural mural = new MuralService().listarkey(id).get(0);
			mural.setMensagem(mensagem);
			new MuralService().atualizarMural(mural);
			
			return Integer.toString(mural.getIdmural());
			
		}
		else if(action.equals("create")){
			
			Date dataT = new Date();
			
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			String dataS = formatter.format(dataT);
			
			Date data = (Date) formatter.parse(dataS);
			
			
			Mural mural = new Mural();
			mural.setMensagem(mensagem);
			mural.setData(data);
			mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			if (tutor != 0)
				mural.setTutoria(1);
			else if (grupo != 0)
				mural.setGrupo(new GrupoService().listarkey(grupo).get(0));
			if (idOficina != 0)
				mural.setOficina(new OficinaService().listarkey(idOficina).get(0));
			else if (idAgrupamento != 0)
				mural.setAgrupamento(new AgrupamentoService().listarkey(idAgrupamento).get(0));
			new MuralService().criarMural(mural);
			
			ProfessorFuncionario verificarCoordena = new ProfessorFuncionarioService().listarkey(idProfessor).get(0);
			if(verificarCoordena.getPerfil() == 26){
				
				  List<AlunoVariavel> listAluno = new ArrayList<AlunoVariavel>();
				  listAluno.addAll(new AlunoVariavelService().listaAnoEstudo(new AnoEstudoService().listarkey(Integer.parseInt(ano)).get(0)));			  
				  
				  for(int k = 0; k < listAluno.size(); k++){
					  MuralAluno muralAluno = new MuralAluno();
					  muralAluno.setAlunoVariavel(listAluno.get(k));
					  muralAluno.setMural(mural);
					  new MuralAlunoService().criarMuralAluno(muralAluno);
				  }
				
				  return Integer.toString(mural.getIdmural());
				
			}
			else {
				if (tutor != 0){
					Tutoria profTutor = new TutoriaService().listarProfessorId(idProfessor).get(0);
				
					List<Grupo> grupos = new GrupoService().listarTutor(profTutor.getIdtutoria());
				
					List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
				
					for (Grupo g : grupos) {
						alunos.addAll(new AlunoVariavelService().listaGrupo(g.getIdgrupo()));
					}
				
					for (AlunoVariavel alunoVariavel : alunos) {
						MuralAluno muralAluno = new MuralAluno();
						muralAluno.setAlunoVariavel(alunoVariavel);
						muralAluno.setMural(mural);
						new MuralAlunoService().criarMuralAluno(muralAluno);
					}
				
					return Integer.toString(mural.getIdmural());
				
				}
				else if(grupo != 0){
					List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
					alunos.addAll(new AlunoVariavelService().listaGrupo(grupo));
				
					for (AlunoVariavel alunoVariavel : alunos) {
						MuralAluno muralAluno = new MuralAluno();
						muralAluno.setAlunoVariavel(alunoVariavel);
						muralAluno.setMural(mural);
						new MuralAlunoService().criarMuralAluno(muralAluno);
					}
				
					return Integer.toString(mural.getIdmural());
				}
				if (idOficina != 0){
					List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
					List<AlunoAgrupamento> alunoAgrupamentos = new ArrayList<AlunoAgrupamento>();
					List<Agrupamento> agrupamentos = new ArrayList<Agrupamento>();
					List<Rotina> rotinas = new RotinaService().listarPorOficina(idOficina);
					for (Rotina rotina : rotinas) {
						agrupamentos.add(rotina.getAgrupamento());
					}
					for (Agrupamento agrupamento : agrupamentos) {
						alunoAgrupamentos.addAll(new AlunoAgrupamentoService().listarAgrupamento(agrupamento.getIdagrupamento()));
					}
					for (AlunoAgrupamento alunoAgrupamento : alunoAgrupamentos) {
						alunos.add(alunoAgrupamento.getAluno());
					}
					for (AlunoVariavel a: alunos) {
						MuralAluno muralAluno = new MuralAluno();
						muralAluno.setAlunoVariavel(a);
						muralAluno.setMural(mural);
						new MuralAlunoService().criarMuralAluno(muralAluno);
					}
					return Integer.toString(mural.getIdmural());
				}
				else if(idAgrupamento != 0){
					List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
					List<AlunoAgrupamento> alunoAgrupamento = new ArrayList<AlunoAgrupamento>();
					alunoAgrupamento = new AlunoAgrupamentoService().listarAgrupamento(idAgrupamento);
					for (AlunoAgrupamento a : alunoAgrupamento) {
						alunos.add(a.getAluno());
					}
					for (AlunoVariavel a: alunos) {
						MuralAluno muralAluno = new MuralAluno();
						muralAluno.setAlunoVariavel(a);
						muralAluno.setMural(mural);
						new MuralAlunoService().criarMuralAluno(muralAluno);
					}
					
					return Integer.toString(mural.getIdmural());
				}
			}
		}
		return "erro";
	}
	
	/**
	 * Listar all
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<Mural> getMural() {
		logger.debug("Listar Mural ...");
		List<Mural> resultado;
		 resultado = new MuralService().listarTodos();
		 logger.debug("QTD Mural : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * Listar por professor
	 * @param idProfessor
	 * @return
	 */
	@Path("ListarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<Mural> getRangeProfessor(@PathParam("idProfessor") int idProfessor){
		Date dataHoje = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dataHoje); 
		c.add(Calendar.DATE, 1);
		dataHoje = c.getTime();
		
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
		
		int mes = c.get(Calendar.MONTH);
		String data2;
		if(mes > 3){
			mes = mes - 3;
			String quebra[] = data.split("-");
			
			if(mes > 10){
				data2 = quebra[0]+"-"+Integer.toString(mes)+"-"+quebra[2];
			}else{
				data2 = quebra[0]+"-"+"0"+Integer.toString(mes)+"-"+quebra[2];
			}
			
		}else{
			int aux = 12;
			mes = mes - 3;
			aux = aux + mes;
			
			String quebra[] = data.split("-");
			int ano = Integer.parseInt(quebra[0]);
			ano = ano - 1;
			
			data2 = Integer.toString(ano)+"-"+Integer.toString(aux)+"-"+quebra[2];
			
		}
		
		return new MuralService().Range(data2,data,idProfessor);
	}
		
	/**
	 * Listar por range de data
	 * @param idAluno
	 * @return
	 */
	/*@Path("ListarAluno/{idAluno}")
	@GET
	@Produces("application/json")
	public List<Mural> getRangeAluno(@PathParam("idAluno") int idAluno){
		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
		
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH);
		String data2;
		if(mes > 3){
			mes = mes - 3;
			String quebra[] = data.split("-");
			
			if(mes > 10){
				data2 = quebra[0]+"-"+Integer.toString(mes)+"-"+quebra[2];
			}else{
				data2 = quebra[0]+"-"+"0"+Integer.toString(mes)+"-"+quebra[2];
			}
			
		}else{
			int aux = 12;
			mes = mes - 3;
			aux = aux + mes;
			
			String quebra[] = data.split("-");
			int ano = Integer.parseInt(quebra[0]);
			ano = ano - 1;
			
			data2 = Integer.toString(ano)+"-"+Integer.toString(aux)+"-"+quebra[2];
			
		}
		
		return new MuralService().RangeAluno(data2,data,idAluno);
	}*/
	
}
