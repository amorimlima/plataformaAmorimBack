package br.com.muranodesign.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.muranodesign.business.HistoricoService;
import br.com.muranodesign.business.SOService;
import br.com.muranodesign.model.Historico_conexao;
import br.com.muranodesign.model.SO;

/**
 * 
 * @author Kevyn
 *
 */
@Path("Metricas")
public class MetricasResource {

	/**
	 * Listar historico de conexao por mes
	 * @return Hashtable<String, Integer>
	 */
	@Path("/AcessoEscola")
	@GET
	@Produces("application/json")
	public Hashtable<String, Integer> acessoescola(){
		Hashtable<String , Integer> escola = new Hashtable<String, Integer>();
		HistoricoService historicoSer = new HistoricoService();
		
		List<Historico_conexao> historicoEscola = new ArrayList<Historico_conexao>();

		int QuantidadeEscola = 0;

		DateFormat df = new SimpleDateFormat("/yyyy");
		Date data = Calendar.getInstance().getTime();
		String ano = df.format(data);
		
		for(int i = 1; i <= 12; i++){
			
			historicoEscola = historicoSer.listarQtdAcessoEscola("0"+i+ano);
			QuantidadeEscola = historicoEscola.size();
			
			escola.put("0"+i+ano,QuantidadeEscola);
		}
		return escola;
	}
	
	/**
	 * Listar numero de acessos externos por mes
	 * @return Hashtable<String, Integer>
	 */
	@Path("/AcessoExterno")
	@GET
	@Produces("application/json")
	public Hashtable<String, Integer> acessoexterno(){
		Hashtable<String , Integer> externo = new Hashtable<String, Integer>();
		HistoricoService historicoSer = new HistoricoService();
		
		List<Historico_conexao> historicoExterno = new ArrayList<Historico_conexao>();
		
		int QuantidadeExterno = 0;
		
		DateFormat df = new SimpleDateFormat("/yyyy");
		Date data = Calendar.getInstance().getTime();
		String ano = df.format(data);
		
		for(int i = 1; i <= 12; i++){
			
			historicoExterno = historicoSer.listarQtdAcessoExterno("0"+i+ano);
			QuantidadeExterno = historicoExterno.size();
			
			externo.put("0"+i+ano, QuantidadeExterno);
		}
		return externo;
	}
	
	/**
	 * Listar os SO usados para entrar na plataforma
	 * @return Hashtable<String, Integer>
	 */
	@Path("/SO")
	@GET
	@Produces("application/json")
	public Hashtable<String, Integer> so(){
		Hashtable<String, Integer> listaSos = new Hashtable<String,Integer>();
		
		int Quantidade = 0;
		
		@SuppressWarnings("unused")
		SO sistema = new SO();
		
		List<Historico_conexao> hist = new ArrayList<Historico_conexao>();
		HistoricoService historicoSer = new HistoricoService();
		SOService sisSer = new SOService();
		
		Hashtable<Integer, String> versao = new Hashtable<Integer, String>();
		versao.put(1, "Windows 95");
		versao.put(2, "Windows 98");
		versao.put(3, "Windows NT/XP");
		versao.put(4, "Windows 2000");
		versao.put(5, "Macintosh");
		versao.put(6, "Unix");
		versao.put(7, "Outro");
		versao.put(8, "Windows 7");
		versao.put(9, "Windows 8");
		versao.put(10,"Windows 10");
		
		for(int i = 1; i <= versao.size(); i++){
			
			hist = historicoSer.listarSo(sistema = sisSer.listarBySO(i).get(0));
			
			Quantidade = hist.size();
			listaSos.put(versao.get(i), Quantidade);
		}
		
		return listaSos;	
	}	
}
