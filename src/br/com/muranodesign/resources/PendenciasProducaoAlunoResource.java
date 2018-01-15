package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import br.com.muranodesign.business.FichaInscricaoService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.PendenciasProducaoAlunoService;
import br.com.muranodesign.business.PlanejamentoRoteiroService;
import br.com.muranodesign.business.ProducaoAlunoService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.PendenciasProducaoAluno;
import br.com.muranodesign.model.PlanejamentoRoteiro;
import br.com.muranodesign.model.Roteiro;

/**
 * 
 * @author Kevyn
 *
 */
@Path("PendenciasProducaoAluno")
public class PendenciasProducaoAlunoResource {
	
	private Logger logger = Logger.getLogger(PendenciasProducaoAlunoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idaluno") int idaluno,
			@FormParam("idroteiro") int idroteiro,
			@FormParam("ano") String anoLetivo){
		
		PendenciasProducaoAluno resultado = new PendenciasProducaoAluno();
		
		if (action.equals("delete"))
		{
			resultado = new PendenciasProducaoAlunoService().deletarPendenciasProducaoAluno(new PendenciasProducaoAlunoService().listarkey(id).get(0));
		}
		else if (action.equals("create"))
		{
			resultado.setAluno(new AlunoService().listarkey(idaluno).get(0));
			resultado.setRoteiro(new RoteiroService().listarkey(idroteiro).get(0));
			String ano;
			if (!(anoLetivo == null))
				ano = anoLetivo;
			else
				ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
			resultado.setAnoLetivo(new AnoLetivoService().listarAnoLetivo(ano).get(0));
			resultado.setPortfolioCompleto(new ProducaoAlunoService().listarFiltro(idaluno, 5, idroteiro).size());
			//Verifica se o roteiro tem ficha de finalização ou não
			//Se tiver o valor fica 0 (size = 1 logo size - 1 = 0), indicando que ela não foi entregue
			//Caso contrário o valor fica -1, indicando que ela não existe.
			resultado.setFichaFinalizacaoCompleta(new FichaInscricaoService().listarkey(idroteiro).size() + new ProducaoAlunoService().listarFiltro(idaluno, 4, idroteiro).size()  - 1);
			resultado.setRoteiroCompleto(0);
			resultado = new PendenciasProducaoAlunoService().criarPendenciasProducaoAluno(resultado);
		}	 
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendencias(){
		logger.debug("Listando Pendencias Producao Aluno...");
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarall();
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendencias(@PathParam("id") int key){
		logger.debug("Listando Pendencias Producao Aluno...");
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarkey(key);
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("EntregarPortfolio/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String entregaPortfolio(
			@PathParam("idaluno") int idaluno,
			@PathParam("idroteiro") int idroteiro
			){
		PendenciasProducaoAluno resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro).get(0);
		
		resultado.setPortfolioCompleto(1);
		
		if (resultado.getFichaFinalizacaoCompleta() != 0)
			resultado.setRoteiroCompleto(1);
		
		resultado = new PendenciasProducaoAlunoService().atualizarPendenciasProducaoAluno(resultado);
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@Path("EntregarFichaFinalizacao/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String entregaFichaFinalizacao(
			@PathParam("idaluno") int idaluno,
			@PathParam("idroteiro") int idroteiro
			){
		PendenciasProducaoAluno resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro).get(0);
		
		resultado.setFichaFinalizacaoCompleta(1);
		
		if (resultado.getPortfolioCompleto() != 0)
			resultado.setRoteiroCompleto(1);
		
		resultado = new PendenciasProducaoAlunoService().atualizarPendenciasProducaoAluno(resultado);
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@Path("PendenciasAluno/{id}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendenciasAluno(@PathParam("id") int id){
		logger.debug("Listando Pendencias Producao Aluno...");
		logger.debug("Aluno: " + id);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAluno(id);
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("PendenciasPassadoAluno/{id}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendenciasAlunoAnoAnterior(@PathParam("id") int id){
		logger.debug("Listando Pendencias Producao Aluno ano Anterior...");
		logger.debug("Aluno: " + id);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAlunoAnoAnterior(id);
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("TotalPendencias/{idaluno}")
	@GET
	@Produces("text/plain")
	public String getTotalPendencias(@PathParam("idaluno") int idaluno)
	{
		logger.debug("Contando Pendencias Producao Aluno...");
		logger.debug("Aluno: " + idaluno);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAluno(idaluno);
		int cont = 0;
		for (PendenciasProducaoAluno pendenciasProducaoAluno : resultado) {
			if (pendenciasProducaoAluno.getRoteiroCompleto() != 1)
			{
				if (pendenciasProducaoAluno.getPortfolioCompleto() == 0)
					cont++;
				if (pendenciasProducaoAluno.getFichaFinalizacaoCompleta() == 0)
					cont++;
			}
		}
		
		return Integer.toString(cont);
	}
	
	@Path("ExistePendencia/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String getPendenciasAlunoRoteiro(@PathParam("idaluno") int idaluno, @PathParam("idroteiro") int idroteiro)
	{
		logger.debug("Listando Pendencias Producao Aluno...");
		logger.debug("Aluno: " + idaluno + " Roteiro: " + idroteiro);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro);
		logger.debug("Existe Pendencias Producao Aluno: " + (resultado.size() > 0));
		return Integer.toString(resultado.size());
	}
	
	@Path("Teste/{idaluno}/{idroteiro}/{ano}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> teste(@PathParam("idaluno") int idaluno, @PathParam("idroteiro") int idroteiro, @PathParam("ano") String ano){
		return new PendenciasProducaoAlunoService().listarAlunoRoteiroAno(idaluno, idroteiro, ano);
	}
	
	@Path("RoteirosSemPortfolioAnoAnterior/{idAluno}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> atribuirPendencias(@PathParam("idAluno") int idAluno){
		List<PendenciasProducaoAluno> result = new ArrayList<PendenciasProducaoAluno>();
		if(new AlunoVariavelService().listarAlunoAno(idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1).size() > 0)
		{
			AlunoVariavel alunoVariavel = new AlunoVariavelService().listarAlunoAno(idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1).get(0);
			List<Roteiro> roteirosAnoAnterior = new RoteiroService().listarAno(alunoVariavel.getAnoEstudo().getIdanoEstudo());
			for (Roteiro roteiro : roteirosAnoAnterior) {
				long objetivosTotais = new ObjetivoService().listarRoteiroTotal(roteiro.getIdroteiro());
				List<PlanejamentoRoteiro> objetivosCompletos = new PlanejamentoRoteiroService().countRoteiroCorrigidos(roteiro.getIdroteiro(), idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1);
				
				PendenciasProducaoAluno pendencia = new PendenciasProducaoAluno();
				pendencia.setAluno(new AlunoService().listarkey(idAluno).get(0));
				pendencia.setAnoLetivo(alunoVariavel.getAnoLetivo());
				pendencia.setRoteiro(roteiro);
				pendencia.setPortfolioCompleto(new ProducaoAlunoService().listarFiltro(idAluno, 5, roteiro.getIdroteiro()).size());
				pendencia.setFichaFinalizacaoCompleta(new FichaInscricaoService().listarkey(roteiro.getIdroteiro()).size() + new ProducaoAlunoService().listarFiltro(idAluno, 4, roteiro.getIdroteiro()).size() - 1);
				pendencia.setRoteiroCompleto(0);
				
				if(objetivosCompletos.size() == objetivosTotais && 
				   !(new PendenciasProducaoAlunoService().listarAlunoRoteiroAno(idAluno, roteiro.getIdroteiro(), alunoVariavel.getAnoLetivo().getAno()).size() > 0) &&
				   (pendencia.getPortfolioCompleto() == 0 || pendencia.getFichaFinalizacaoCompleta() == 0))
				{
					new PendenciasProducaoAlunoService().criarPendenciasProducaoAluno(pendencia);
					result.add(pendencia);	
				}
			}
			
			List <AtribuicaoRoteiroExtra> roteirosExtrasAnoAnterior = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(idAluno).get(0), new AnoLetivoService().listarAnoLetivo(Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1)).get(0));
			for (AtribuicaoRoteiroExtra atribuicaoRoteiroExtra : roteirosExtrasAnoAnterior) {
				
				long objetivosTotais = new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
				List<PlanejamentoRoteiro> objetivosCompletos = new PlanejamentoRoteiroService().countRoteiroCompletos(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro(), idAluno, Calendar.getInstance().get(Calendar.YEAR) - 1);
				
				PendenciasProducaoAluno pendencia = new PendenciasProducaoAluno();
				pendencia.setAluno(new AlunoService().listarkey(idAluno).get(0));
				pendencia.setAnoLetivo(alunoVariavel.getAnoLetivo());
				pendencia.setRoteiro(atribuicaoRoteiroExtra.getRoteiro());
				pendencia.setPortfolioCompleto(new ProducaoAlunoService().listarFiltro(idAluno, 5, atribuicaoRoteiroExtra.getRoteiro().getIdroteiro()).size());
				pendencia.setFichaFinalizacaoCompleta(new FichaInscricaoService().listarkey(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro()).size() + new ProducaoAlunoService().listarFiltro(idAluno, 4, atribuicaoRoteiroExtra.getRoteiro().getIdroteiro()).size() - 1);
				pendencia.setRoteiroCompleto(0);
				
				if(objetivosCompletos.size() == objetivosTotais && 
				   new PendenciasProducaoAlunoService().listarAlunoRoteiroAno(idAluno, atribuicaoRoteiroExtra.getRoteiro().getIdroteiro(), alunoVariavel.getAnoLetivo().getAno()).size() > 0 &&
				   (pendencia.getPortfolioCompleto() == 0 || pendencia.getFichaFinalizacaoCompleta() == 0))
				{
					new PendenciasProducaoAlunoService().criarPendenciasProducaoAluno(pendencia);
					result.add(pendencia);
				}
			}
		}
		return result;
	}
	
}
