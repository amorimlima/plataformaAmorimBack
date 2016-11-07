package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoAgrupamentoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.CiclosService;
import br.com.muranodesign.business.CoresService;
import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.model.AlunoAgrupamento;
import br.com.muranodesign.model.Oficina;
import br.com.muranodesign.model.OficinaProfessor;
import br.com.muranodesign.model.Rotina;


@Path("Oficina")
public class OficinaResource {
	
	private Logger logger = Logger.getLogger(OficinaResource.class.getName());
	

	/**
	 * Deletar, alterar e criar oficina
	 * @param action
	 * @param id
	 * @param nome
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("ciclo") int ciclo,
			@FormParam("anoLetivo") int anoLetivo,
			@FormParam("periodo") int periodo,
			@FormParam("tipo") String tipo,
			@FormParam("nome") String nome){
		
		Oficina resultado = new Oficina();
		
		if(action.equals("delete")){
			resultado = new OficinaService().deletarOficina(new OficinaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			Oficina oficina = new Oficina();
			
			if(tipo.equals("outras")){
				oficina.setNome(nome);
				
				// cor fake, mudar quando for subir pra produção
				oficina.setCor(new CoresService().listarkey(4).get(0));
			}else{
				long tem = new OficinaService().listarNomeOficina(nome);
				
				Oficina ofi = new OficinaService().listarNomeOficinaid(nome).get(0);
				
				oficina.setCor(ofi.getCor());
				
				if(tem == 0){
					oficina.setNome(nome+" -1");
				}else{
					oficina.setNome(nome+" -"+tem);
				}
			}
			

			//oficina.setNome(nome);
			//oficina.setCor(new CoresService().listarkey(cor).get(0));
			oficina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			oficina.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			oficina.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			resultado = new OficinaService().criarOficina(oficina);
			
		}else if(action.equals("update")){
			Oficina oficina = new OficinaService().listarkey(id).get(0);
			
			//oficina.setNome(nome);
			//oficina.setCor(new CoresService().listarkey(cor).get(0));
			oficina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			oficina.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			oficina.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			resultado = new OficinaService().atualizarOficina(oficina);
		}
		
		return Integer.toString(resultado.getIdoficina());
	}
	
	/**
	 * Listar todas as oficinas
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Oficina> getOficina() {
		logger.debug("Listar Oficina ...");
		List<Oficina> resultado;
		 resultado = new OficinaService().listarTodos();
		 logger.debug("QTD Oficina : " +  resultado.size());
		return resultado;
	}
	
	
	@Path("ListarPorAluno/{id}")
	@GET
	@Produces("application/json")
	public List<Hashtable<String, String>> getListarPorAluno(@PathParam("id") int id){
		logger.debug("Listar Oficina por aluno ..."+id);
		
		List<Hashtable<String, String>> lista = new ArrayList<Hashtable<String,String>>();
		List<AlunoAgrupamento> aluAgrup = new AlunoAgrupamentoService().listarAluno(id);
		List<Rotina> rotinas = new ArrayList<Rotina>();
		
		for (AlunoAgrupamento alunoAgrupamento : aluAgrup) {
			rotinas.addAll(new RotinaService().listarPorAgrupamento(alunoAgrupamento.getAgrupamento().getIdagrupamento()));
		}
		
		for(int i = 0; i < rotinas.size(); i++){
			Hashtable<String, String> hash = new Hashtable<String, String>();
			
			hash.put("idOficina", Integer.toString(rotinas.get(i).getOficina().getIdoficina()));
			hash.put("Nome", rotinas.get(i).getOficina().getNome());
			hash.put("CorForte", rotinas.get(i).getOficina().getCor().getForte());
			hash.put("CorFraca", rotinas.get(i).getOficina().getCor().getFraco());
			hash.put("CorMedia", rotinas.get(i).getOficina().getCor().getMedio());
			
			lista.add(hash);
		}
		
		return lista;
	}

	
	@Path("ListaPorProfessor/{id}")
	@GET
	@Produces("application/json")
	public List<Oficina> getListaPorProfessor(@PathParam("id") int id)
	{
		List<OficinaProfessor> resultado;
		List<Oficina> oficinas = new ArrayList<Oficina>();
		//List<Cores> cores = new ArrayList<Cores>();
		
		resultado = new OficinaProfessorService().listarProfessor(id);
		for (OficinaProfessor oficinaProfessor : resultado) {
			oficinas.add(new OficinaService().listarLazyNome(oficinaProfessor.getOficina().getIdoficina()).get(0));
		}
		
		/*for(int i = 0; i < oficinas.size(); i++){
			cores.add(new CoresService().listarkey(oficinas.get(i).getCor().getIdcor()).get(0));
		}*/
		
		return oficinas;
	}
}
