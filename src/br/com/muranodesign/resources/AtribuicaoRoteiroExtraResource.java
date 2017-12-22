package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.AtribuicaoRoteiroExtraService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.PlanejamentoRoteiroService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.PlanejamentoRoteiro;
import br.com.muranodesign.model.Roteiro;

/**
 * 
 * @author Kevyn
 *
 */
@Path("AtribuicaoRoteiroExtra")
public class AtribuicaoRoteiroExtraResource {
	
	
	private Logger logger = Logger.getLogger(AtribuicaoRoteiroExtraResource.class.getName());
	
	/**
	 * Criar e alterar atribuicao de roteiros extras 
	 * @param action
	 * @param id
	 * @param idaluno
	 * @param idroteiro
	 * @param motivo
	 * @param idano_letivo
	 * @return  id
	 */
	@POST
	@Produces("application/json")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idaluno") int idaluno,
			@FormParam("idroteiro") int idroteiro,
			@FormParam("motivo") String motivo,
			@FormParam("idano_letivo") int idano_letivo){
		
		logger.info("eventoAction ...");
		
		AtribuicaoRoteiroExtra atribuicao = new AtribuicaoRoteiroExtra();
		AtribuicaoRoteiroExtra result = new AtribuicaoRoteiroExtra();
		AtribuicaoRoteiroExtraService atribuicaoSer = new AtribuicaoRoteiroExtraService();
		if(action.equals("create")){
			atribuicao.setAluno(new AlunoService().listarkey(idaluno).get(0));
			atribuicao.setRoteiro(new RoteiroService().listarkey(idroteiro).get(0));
			atribuicao.setMotivo(motivo);
			atribuicao.setAnoLetivo(new AnoLetivoService().listarkey(idano_letivo).get(0));
			
			result = atribuicaoSer.criarRoteiroExtra(atribuicao);
			
		}else if(action.equals("update")){
			atribuicao.setIdatribuicaoRoteiroExtra(id);
			atribuicao.setAluno(new AlunoService().listarkey(idaluno).get(0));
			atribuicao.setRoteiro(new RoteiroService().listarkey(idroteiro).get(0));
			atribuicao.setMotivo(motivo);
			atribuicao.setAnoLetivo(new AnoLetivoService().listarkey(idano_letivo).get(0));
			
			result = atribuicaoSer.alterarRoteiro(atribuicao);
		}else {
			return "0";
		}
		
		
		return Integer.toString(result.getIdatribuicaoRoteiroExtra());
	}
	
	
	/**
	 * Deletar atribuicao de roteiros extras 
	 * @param id
	 * @return String
	 */
	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeRoteiroExtra(@PathParam("id") int id){
		
		logger.debug("Remover Roteiro Extra " + id );
		List<AtribuicaoRoteiroExtra> resultado;
		resultado = new AtribuicaoRoteiroExtraService().listarid(id);
		AtribuicaoRoteiroExtra atribuicao = resultado.get(0);
		
		new AtribuicaoRoteiroExtraService().deletarRoteiroExtra(atribuicao);
		
		return "Deletado";
	}
	
	/**
	 * Lista todas as atribui��es de roteiros extras
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<AtribuicaoRoteiroExtra> getAtribuicaoRoteiroExtra(){
		
		logger.debug("Listar Roteiro Extra ...");
		List<AtribuicaoRoteiroExtra> resultado;
		resultado = new AtribuicaoRoteiroExtraService().listarTodos();
		logger.debug("QTD Roteiros por ano : " +  resultado.size());
		
		return resultado;
	}
	
	/**
	 * Lista atribui��es de roteiros extras por id de aluno e id de roteiro
	 * @param idaluno
	 * @param idroteiro
	 * @return int
	 */
	@Path("/RoteiroAluno/{idaluno}/{idroteiro}")
	@GET
	@Produces("application/json")
	public int getRoteiroAluno(@PathParam("idaluno") int idaluno, @PathParam("idroteiro") int idroteiro){
		logger.debug("Listar Roteiro Extra ...");
		List<AtribuicaoRoteiroExtra> resultado;
		resultado = new AtribuicaoRoteiroExtraService().listarkey(new AlunoService().listarkey(idaluno).get(0), new RoteiroService().listarkey(idroteiro).get(0));
		int result = 0, quantidade = resultado.size();
		if(quantidade == 0){
			 result = 0;
		}else if(quantidade >= 1){
			result = 1;
		}
		
		logger.debug("QTD Roteiros por ano : " +  resultado.size());
		
		return result;
	}
	
	/**
	 * Lista roteiro por id de aluno
	 * @param id
	 * @return list
	 */
	@Path("aluno/{id}")
	@GET
	@Produces("application/json")
	public List<Roteiro> getAluno(@PathParam("id") int id){
		logger.debug("Listar Roteiro Extra aluno...");
		List<AtribuicaoRoteiroExtra> resultado;
		List<Roteiro> roteiro = new ArrayList<Roteiro>();
		resultado = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(id).get(0));
		int quantidade = resultado.size();
		
		for(int i = 0; i < quantidade; i++){
			roteiro.add(resultado.get(i).getRoteiro());
		}
		
		
		
		return roteiro;
	}
	
	@Path("Teste/{roteiro}/{idAluno}/{ano}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoRoteiro> teste(@PathParam("roteiro") int roteiro, @PathParam("idAluno") int aluno, @PathParam("ano") int ano){
		return new PlanejamentoRoteiroService().countRoteiroCompletos(roteiro, aluno, ano);
	}
	
	@Path("AtribuirRoteirosIncompletosAnoAnterior/{idAluno}")
	@GET
	@Produces("application/json")
	public List<AtribuicaoRoteiroExtra> atribuirExtras(@PathParam("idAluno") int idAluno){
		List<AtribuicaoRoteiroExtra> result = new ArrayList<AtribuicaoRoteiroExtra>();
		AlunoVariavel alunoAno = new AlunoVariavelService().listarAlunoAno(idAluno, Calendar.getInstance().get(Calendar.YEAR)).get(0);
		if(new AlunoVariavelService().listarAlunoAno(idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1).size() > 0)
		{
			AlunoVariavel alunoVariavel = new AlunoVariavelService().listarAlunoAno(idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1).get(0);
			List<Roteiro> roteirosAnoAnterior = new RoteiroService().listarAno(alunoVariavel.getAnoEstudo().getIdanoEstudo());
			for (Roteiro roteiro : roteirosAnoAnterior) {
				long objetivosTotais = new ObjetivoService().listarRoteiroTotal(roteiro.getIdroteiro());
				List<PlanejamentoRoteiro> objetivosCompletos = new PlanejamentoRoteiroService().countRoteiroCompletos(roteiro.getIdroteiro(), idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1);
				if(objetivosCompletos.size() < objetivosTotais)
				{
					AtribuicaoRoteiroExtra atribuicao = new AtribuicaoRoteiroExtra();
					atribuicao.setAluno(new AlunoService().listarkey(idAluno).get(0));
					atribuicao.setRoteiro(roteiro);
					atribuicao.setMotivo("Roteiro não foi completado no ano anterior");
					atribuicao.setAnoLetivo(new AnoLetivoService().listarAnoLetivo(Integer.toString(Calendar.getInstance().get(Calendar.YEAR))).get(0));
					new AtribuicaoRoteiroExtraService().criarRoteiroExtra(atribuicao);
					result.add(atribuicao);
					for (PlanejamentoRoteiro planejamentoRoteiro : objetivosCompletos) {
						PlanejamentoRoteiro clonePlanejamento = new PlanejamentoRoteiro();
						clonePlanejamento.setDataStatusPlanejado(Calendar.getInstance().getTime());
						clonePlanejamento.setDataStatusEntregue(planejamentoRoteiro.getDataStatusEntregue());
						clonePlanejamento.setDataStatusVisto(planejamentoRoteiro.getDataStatusVisto());
						clonePlanejamento.setObjetivo(planejamentoRoteiro.getObjetivo());
						clonePlanejamento.setStatus(planejamentoRoteiro.getStatus());
						clonePlanejamento.setPlanoEstudo(null);
						clonePlanejamento.setIdAluno(idAluno);
						new PlanejamentoRoteiroService().criarPlanejamentoRoteiro(clonePlanejamento);
					}
				}
			}
			List <AtribuicaoRoteiroExtra> roteirosExtrasAnoAnterior = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(idAluno).get(0), new AnoLetivoService().listarAnoLetivo(Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1)).get(0));
			for (AtribuicaoRoteiroExtra atribuicaoRoteiroExtra : roteirosExtrasAnoAnterior) {
				long objetivosTotais = new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
				List<PlanejamentoRoteiro> objetivosCompletos = new PlanejamentoRoteiroService().countRoteiroCompletos(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro(), idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1);
				if(	objetivosCompletos.size() < objetivosTotais && 
					atribuicaoRoteiroExtra.getRoteiro().getAnoEstudo().getIdanoEstudo() != alunoAno.getAnoEstudo().getIdanoEstudo()){
						
					AtribuicaoRoteiroExtra atribuicao = new AtribuicaoRoteiroExtra();
					atribuicao.setAluno(new AlunoService().listarkey(idAluno).get(0));
					atribuicao.setRoteiro(atribuicaoRoteiroExtra.getRoteiro());
					atribuicao.setMotivo("Roteiro n�o foi completado no ano anterior");
					atribuicao.setAnoLetivo(new AnoLetivoService().listarAnoLetivo(Integer.toString(Calendar.getInstance().get(Calendar.YEAR))).get(0));
					new AtribuicaoRoteiroExtraService().criarRoteiroExtra(atribuicao);
					result.add(atribuicao);
					for (PlanejamentoRoteiro planejamentoRoteiro : objetivosCompletos) {
						PlanejamentoRoteiro clonePlanejamento = new PlanejamentoRoteiro();
						clonePlanejamento.setDataStatusPlanejado(Calendar.getInstance().getTime());
						clonePlanejamento.setDataStatusEntregue(planejamentoRoteiro.getDataStatusEntregue());
						clonePlanejamento.setDataStatusVisto(planejamentoRoteiro.getDataStatusVisto());
						clonePlanejamento.setObjetivo(planejamentoRoteiro.getObjetivo());
						clonePlanejamento.setStatus(planejamentoRoteiro.getStatus());
						clonePlanejamento.setPlanoEstudo(null);
						clonePlanejamento.setIdAluno(idAluno);
						new PlanejamentoRoteiroService().criarPlanejamentoRoteiro(clonePlanejamento);
					}
				}
				if (atribuicaoRoteiroExtra.getRoteiro().getAnoEstudo() == alunoAno.getAnoEstudo()){
					for (PlanejamentoRoteiro planejamentoRoteiro : objetivosCompletos) {
						PlanejamentoRoteiro clonePlanejamento = new PlanejamentoRoteiro();
						clonePlanejamento.setDataStatusPlanejado(Calendar.getInstance().getTime());
						clonePlanejamento.setDataStatusEntregue(planejamentoRoteiro.getDataStatusEntregue());
						clonePlanejamento.setDataStatusVisto(planejamentoRoteiro.getDataStatusVisto());
						clonePlanejamento.setObjetivo(planejamentoRoteiro.getObjetivo());
						clonePlanejamento.setStatus(planejamentoRoteiro.getStatus());
						clonePlanejamento.setPlanoEstudo(null);
						clonePlanejamento.setIdAluno(idAluno);
						new PlanejamentoRoteiroService().criarPlanejamentoRoteiro(clonePlanejamento);
					}
				}
			}
		}
		alunoAno.setVerificarRoteiros(0);
		new AlunoVariavelService().atualizarAlunoVariavel(alunoAno);
		return result;
	}

}
