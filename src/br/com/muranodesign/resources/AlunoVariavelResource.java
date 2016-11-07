/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoEstudoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.CicloAnoEstudoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.CicloAnoEstudo;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Periodo;
import br.com.muranodesign.util.StringUtil;


/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos a Aluno Variavel.
 * 
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("AlunoVariavel")
public class AlunoVariavelResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(AlunoVariavelResource.class.getName());

	/**
	 * Serviço reponsavel por Lista todos os alunos variaveis.
	 * 
	 * @return Alunos Variavel
	 */
	@Path("html/{ano}/{periodo}")
	@GET
	@Produces("text/plain")
	public /*List<AlunoVariavel>*/String getAlunoVariavelhtml(@PathParam("ano") int ano, @PathParam("periodo") int periodo) {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaAnoEstudoPeriodo(new AnoEstudoService().listarkey(ano).get(0), new PeriodoService().listarkey(periodo).get(0));
		logger.info("QTD AlunoVariavel : " + resultado.size());
		int tamanho = resultado.size();
		String HtmlContent = "";
		
		for(int i = 0; i < tamanho; i++){
			//carrega professor na combo
			if(resultado.get(i).getGrupo() == null || resultado.get(i).getGrupo().getStatus().equals("1")){
				HtmlContent += "<option value="+resultado.get(i).getIdalunoVariavel()+">"+resultado.get(i).getAluno().getNome()+"</option>";
			}
		}
	
		return HtmlContent;
	}
	
	/**
	 * Lista todos os alunos variaveis 
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getAlunoVariavel() {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarTodos();
		logger.info("QTD AlunoVariavel : " + resultado.size());
		return resultado;
	}
	
	
	@Path("listarCicloPeriodoRange/{idCiclo}/{idPeriodo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public String getlistarCicloPeriodoRange(@PathParam("idCiclo") int idCiclo,@PathParam("idPeriodo") int idPeriodo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		List<CicloAnoEstudo> ciclos = new CicloAnoEstudoService().listCiclo(idCiclo);
		//int ano[] = new int[100];
		List<Integer> anos = new ArrayList<Integer>();
		
		//String html = "";
		
		for(int i = 0; i < ciclos.size(); i++) {
			if(!anos.contains(ciclos.get(i).getAno().getIdanoEstudo())){
				anos.add(ciclos.get(i).getAno().getIdanoEstudo());
				
			}
		}
		
		String html1 = "";
		
		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarCicloAnoPeriodo(anos, idPeriodo, primeiro, ultimo);
		
		for (AlunoVariavel alunoVariavel : retorno) {
		html1 += 	"<div class="+"'Grupo_Aluno_Linha'"+">" +
			 		"<span class="+"'Nome_Aluno'"+">"+alunoVariavel.getAluno().getNome()+"</span>"+
			        "<input type="+"'checkbox'"+" id="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"' class="+"'Aluno_Ano_Check'" +"/>"+
			        "<label for="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"'>"+
			        "<span></span>" + 
					"</label>" +
					"<span class="+"Ano_Aluno"+">"+alunoVariavel.getAnoEstudo().getAno()+"º ano</span>"+
          "</div>";
		}
		
		
		
		
		return html1;		
		
	}
	
	@Path("listarCicloPeriodoRangeObjeto/{idCiclo}/{idPeriodo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getlistarCicloPeriodoRangeObjeto(@PathParam("idCiclo") int idCiclo,@PathParam("idPeriodo") int idPeriodo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		List<CicloAnoEstudo> ciclos = new CicloAnoEstudoService().listCiclo(idCiclo);
		List<Integer> anos = new ArrayList<Integer>();
		
		for(int i = 0; i < ciclos.size(); i++) {
			if(!anos.contains(ciclos.get(i).getAno().getIdanoEstudo())){
				anos.add(ciclos.get(i).getAno().getIdanoEstudo());
				
			}
		}
		
		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarCicloAnoPeriodo(anos, idPeriodo, primeiro, ultimo);
		
		return retorno;		
		
	}
	
	
	@Path("listarCicloRange/{idCiclo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public String getlistarCicloRange(@PathParam("idCiclo") int idCiclo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		List<CicloAnoEstudo> ciclos = new CicloAnoEstudoService().listCiclo(idCiclo);
		//int ano[] = new int[100];
		List<Integer> anos = new ArrayList<Integer>();
		
	//	String html = "";
		
		for(int i = 0; i < ciclos.size(); i++) {
			if(!anos.contains(ciclos.get(i).getAno().getIdanoEstudo())){
				anos.add(ciclos.get(i).getAno().getIdanoEstudo());
				
			}
		}
		
		String html1 = "";
		
		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarCicloAno(anos, primeiro, ultimo);
		
		for (AlunoVariavel alunoVariavel : retorno) {
		html1 += 	"<div class="+"'Grupo_Aluno_Linha'"+">" +
			 		"<span class="+"'Nome_Aluno'"+">"+alunoVariavel.getAluno().getNome()+"</span>"+
			        "<input type="+"'checkbox'"+" id="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"' class="+"'Aluno_Ano_Check'" +"/>"+
			        "<label for="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"'>"+
			        "<span></span>" + 
					"</label>" +
					"<span class="+"Ano_Aluno"+">"+alunoVariavel.getAnoEstudo().getAno()+"º ano</span>"+
          "</div>";
		}
		
				
		
		return html1;		
		
	}
	
	@Path("listarCicloRangeObjeto/{idCiclo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getlistarCicloRangeObjeto(@PathParam("idCiclo") int idCiclo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		List<CicloAnoEstudo> ciclos = new CicloAnoEstudoService().listCiclo(idCiclo);
		
		List<Integer> anos = new ArrayList<Integer>();
		
		for(int i = 0; i < ciclos.size(); i++) {
			if(!anos.contains(ciclos.get(i).getAno().getIdanoEstudo())){
				anos.add(ciclos.get(i).getAno().getIdanoEstudo());
				
			}
		}
				
		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarCicloAno(anos, primeiro, ultimo);
	
		return retorno;		
		
	}
	
	@Path("listarPeriodoRange/{idPeriodo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public String getlistarPeriodoRange(@PathParam("idPeriodo") int idPeriodo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){
		
		String html1 = "";
		
		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarRangePeriodo(idPeriodo, primeiro, ultimo);
		
		for (AlunoVariavel alunoVariavel : retorno) {
		html1 += 	"<div class="+"'Grupo_Aluno_Linha'"+">" +
			 		"<span class="+"'Nome_Aluno'"+">"+alunoVariavel.getAluno().getNome()+"</span>"+
			        "<input type="+"'checkbox'"+" id="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"' class="+"'Aluno_Ano_Check'" +"/>"+
			        "<label for="+"'Aluno_Check_"+alunoVariavel.getIdalunoVariavel()+"'>"+
			        "<span></span>" + 
					"</label>" +
					"<span class="+"Ano_Aluno"+">"+alunoVariavel.getAnoEstudo().getAno()+"º ano</span>"+
          "</div>";
		}
		
		
		return html1;		
		
	}
	
	@Path("listarPeriodoRangeObjeto/{idPeriodo}/{primeiro}/{ultimo}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getlistarPeriodoRangeObjeto(@PathParam("idPeriodo") int idPeriodo,@PathParam("primeiro") int primeiro,@PathParam("ultimo") int ultimo){

		List<AlunoVariavel> retorno = new AlunoVariavelService().ListarRangePeriodo(idPeriodo, primeiro, ultimo);
		
		return retorno;		
		
	}
	
	/**
	 * Serviço reponsavel por Lista todos os alunos variaveis por status.
	 * 
	 * @return Alunos Variavel
	 */
	@Path("listar/{status}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getAlunoVariavel(@PathParam("status") int status) {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarTodos(status);
		logger.info("QTD AlunoVariavel : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Lista aluno variavel por tutoria, ano e periodo 
	 * @param Tutoria
	 * @param Ano
	 * @param Periodo
	 * @return list
	 */
	@Path("listarAlunoAgrupamento/{Tutoria}/{Ano}/{Periodo}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> AlunoAgrupamento(
			@PathParam("Tutoria") int Tutoria,
			@PathParam("Ano") int Ano,
			@PathParam("Periodo") int Periodo){
		
		
		
		List<AlunoVariavel> resultado = new ArrayList<AlunoVariavel>();
		
		List<AlunoVariavel> aluno = new ArrayList<AlunoVariavel>();
		List<Grupo> grupo = new ArrayList<Grupo>();
		
		
		if(Tutoria != 0 && Periodo == 0 & Ano == 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				aluno = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				resultado.addAll(aluno);
			}
			

		}else if(Ano != 0 && Tutoria == 0 && Periodo == 0){
			
			resultado = new AlunoVariavelService().listaAnoEstudo(new AnoEstudoService().listarkey(Ano).get(0));

		}else if(Periodo != 0 && Ano == 0 && Tutoria == 0){
			
			resultado = new AlunoVariavelService().listaPeriodo(new PeriodoService().listarkey(Periodo).get(0));

		}else if(Tutoria != 0 && Ano != 0 && Periodo == 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				aluno = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!aluno.isEmpty()){
					for(int k =0; k < aluno.size(); k++){
						if(aluno.get(k).getAnoEstudo().getIdanoEstudo() == Ano){
							resultado.add(aluno.get(k));
						}
					}
				}
			}

		}else if(Tutoria != 0 && Periodo != 0 && Ano == 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				aluno = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!aluno.isEmpty()){
					for(int k =0; k < aluno.size(); k++){
						if(aluno.get(k).getPeriodo().getIdperiodo() == Periodo){
							resultado.add(aluno.get(k));
						}
					}
				}
			}

		}else if(Periodo != 0 && Ano != 0 && Tutoria == 0){
			resultado = new AlunoVariavelService().listaAnoEstudoPeriodoComgrupo(new AnoEstudoService().listarkey(Ano).get(0), new PeriodoService().listarkey(Periodo).get(0));

		}else if(Tutoria != 0 && Ano != 0 && Periodo != 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				aluno = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!aluno.isEmpty()){
					for(int k =0; k < aluno.size(); k++){
						if(aluno.get(k).getAnoEstudo().getIdanoEstudo() == Ano && aluno.get(k).getPeriodo().getIdperiodo() == Periodo){
							resultado.add(aluno.get(k));
						}
					}
				}
			}
		}
		
		return resultado;
	}
	
	/**
	 * Gerar relatório de aluno variavel
	 * @param Tutoria
	 * @param Ano
	 * @param Periodo
	 * @param Nome
	 * @param Sexo
	 * @param Datanascimento
	 * @param Endereco
	 * @param TelefoneResidencial
	 * @param TelefoneCelular
	 * @param email
	 * @param NomeResponsavel
	 * @param ParentescoResponsavel
	 * @param TelefoneResidencialResponsavel
	 * @param TelefoneCelularResponsavel
	 * @param TelefoneComercialResponsavel
	 * @param emailResponsavel
	 * @param NomeMae
	 * @param EnderecoMae
	 * @param TelefoneCelularMae
	 * @param TelefoneResidencialMae
	 * @param TelefoneComercialMae
	 * @param emailMae
	 * @return list
	 */
	@Path("Relatorio")
	@POST
	@Produces("application/json")
	public List<String> relatorio(
			@FormParam("Tutoria") int Tutoria,
			@FormParam("Ano") int Ano,
			@FormParam("Periodo") int Periodo,
			
			@FormParam("Nome") String Nome,
			@FormParam("Sexo") String Sexo	,
			@FormParam("Datanascimento") String Datanascimento,
			@FormParam("Endereco") String Endereco,
			@FormParam("TelefoneResidencial") String TelefoneResidencial,
			@FormParam("TelefoneCelular") String TelefoneCelular,
			@FormParam("email") String email,
			@FormParam("NomeResponsavel") String NomeResponsavel,
			@FormParam("ParentescoResponsavel") String ParentescoResponsavel,
			@FormParam("TelefoneResidencialResponsavel") String TelefoneResidencialResponsavel,
			@FormParam("TelefoneCelularResponsavel") String TelefoneCelularResponsavel,
			@FormParam("TelefoneComercialResponsavel") String TelefoneComercialResponsavel,
			@FormParam("emailResponsavel") String emailResponsavel,
			@FormParam("NomeMae") String NomeMae,
			@FormParam("EnderecoMae") String EnderecoMae,
			@FormParam("TelefoneCelularMae") String TelefoneCelularMae,
			@FormParam("TelefoneResidencialMae") String TelefoneResidencialMae,
			@FormParam("TelefoneComercialMae") String TelefoneComercialMae,
			@FormParam("emailMae") String emailMae//,
		//	@FormParam("Caminho") String Caminho
			
			
			){
		
			 
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet firstSheet = workbook.createSheet("Relatorio Aluno");
			 
			FileOutputStream fos = null;
			
			String nomeArquivo;
			 
			try {
				
			nomeArquivo = new StringUtil().geraNomeAleatorio("xls", 15);
			fos = new FileOutputStream(new File("/home/tomcat/webapps/files/" + nomeArquivo));
			//fos = new FileOutputStream(new File("C:/Users/Kevyn/Documents/teste/" + nomeArquivo));
						
			// Este trecho obtem uma lista de objetos do tipo CD
			 
			// do banco de dados através de um DAO e itera sobre a lista
			 
			// criando linhas e colunas em um arquivo Excel com o conteúdo
			 
			// dos objetos.
			 
			
			 
			/* 
			for (int i = 0; i < 4; i++) {
			HSSFRow row = firstSheet.createRow(i);
			 
			row.createCell((short) 0).setCellValue("primeiro");
			row.createCell((short) 1).setCellValue("segundo");
			row.createCell((short) 2).setCellValue("terceito");
			row.createCell((short) 3).setCellValue("quarto");
			row.createCell((short) 4).setCellValue("teste");
			
			 
			 
			} // fim do for
			 
		*/
		
		
	
		HSSFRow row = firstSheet.createRow(0);
		
		List<String> retorno = new ArrayList<String>();
		List<List<String>> listRetorno = new ArrayList<List<String>>();
		
		List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
		List<Grupo> grupo = new ArrayList<Grupo>();
		
		//String html = "";
		//String parte = "";
		
		int h = 0;
		row = firstSheet.createRow(h);
		row.createCell((short) 0).setCellValue("Nome");
		row.createCell((short) 1).setCellValue("Sexo");
		row.createCell((short) 2).setCellValue("Datanascimento");
		row.createCell((short) 3).setCellValue("Endereco");
		row.createCell((short) 4).setCellValue("TelefoneResidencial");
		row.createCell((short) 5).setCellValue("TelefoneCelular");
		row.createCell((short) 6).setCellValue("email");
		row.createCell((short) 7).setCellValue("NomeResponsavel");
		row.createCell((short) 8).setCellValue("ParentescoResponsavel");
		row.createCell((short) 9).setCellValue("TelefoneResidencialResponsavel");
		row.createCell((short) 10).setCellValue("TelefoneCelularResponsavel");
		row.createCell((short) 11).setCellValue("TelefoneComercialResponsavel");
		row.createCell((short) 12).setCellValue("emailResponsavel");
		row.createCell((short) 13).setCellValue("NomeMae");
		row.createCell((short) 14).setCellValue("EnderecoMae");
		row.createCell((short) 15).setCellValue("TelefoneCelularMae");
		row.createCell((short) 16).setCellValue("TelefoneResidencialMae");
		row.createCell((short) 17).setCellValue("TelefoneComercialMae");
		row.createCell((short) 18).setCellValue("emailMae");
		
		
		if(Tutoria != 0 && Periodo == 0 & Ano == 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						h++;
						row = firstSheet.createRow(h);
						//row.createCell((short) 0).setCellValue("Relatório");
						
						if(Nome != "" && Nome != null){
							
							
							String valor = alunos.get(k).getAluno().getNome();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								row.createCell((short) 0).setCellValue(valor);
								
							}else{
								retorno.add("não informado");
							}
						
						}
						if(Sexo != "" && Sexo != null){
							
							String valor = alunos.get(k).getAluno().getSexo();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								row.createCell((short) 1).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
						}
						if(Datanascimento  != "" && Datanascimento  != null){
							
							Date valor = alunos.get(k).getAluno().getDataNascimento();
							
							if(valor != null){
								
								DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
								
								String dataS = formatter.format(valor);
								
								//Date data = (Date) formatter.parse(dataS);
								
								retorno.add(dataS);
								
								row.createCell((short) 2).setCellValue(valor);
							}else{
								retorno.add( null);
							}
							
							
						}
						if(Endereco != "" && Endereco != null){
							
							String valor = alunos.get(k).getAluno().getEndereco();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								row.createCell((short) 3).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneResidencial != "" && TelefoneResidencial != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								row.createCell((short) 4).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(TelefoneCelular != "" && TelefoneCelular != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
		
								row.createCell((short) 5).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(email != "" && email != null){
							
							String valor = alunos.get(k).getAluno().getEmail();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
				
								row.createCell((short) 6).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(NomeResponsavel != "" && NomeResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getNomeResponsavel();	
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
			
								row.createCell((short) 7).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getParentescoResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 8).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 9).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						
						}
						if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
				
								row.createCell((short) 10).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								
								row.createCell((short) 11).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(emailResponsavel != "" && emailResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getEmail1Responsavel();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 12).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(NomeMae != "" && NomeMae != null){
							
							String valor = alunos.get(k).getAluno().getNomeMae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 13).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(EnderecoMae != "" && EnderecoMae != null){
							
							String valor = alunos.get(k).getAluno().getEnderecoMae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 14).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 15).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						
						}
						if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 16).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
								
								row.createCell((short) 17).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(emailMae != "" && emailMae != null){
							
							String valor = alunos.get(k).getAluno().getEmail1Mae();
							
							if(valor != null && valor != "-"){
								retorno.add(valor);
							
								row.createCell((short) 18).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
						}
						
						listRetorno.add(retorno);
						
					}
				}
			}
			
			
		}else if(Ano != 0 && Tutoria == 0 && Periodo == 0){
			
			alunos = new AlunoVariavelService().listaAnoEstudo(new AnoEstudoService().listarkey(Ano).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					
					h++;
					row = firstSheet.createRow(h);
					row.createCell((short) 0).setCellValue("Relatório");
					if(Nome != "" && Nome != null){
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							
							DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
							
							String dataS = formatter.format(valor);
							
							//Date data = (Date) formatter.parse(dataS);
							
							retorno.add(dataS);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}		
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
			
		}else if(Periodo != 0 && Ano == 0 && Tutoria == 0){
			
			alunos = new AlunoVariavelService().listaPeriodo(new PeriodoService().listarkey(Periodo).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					h++;
					row = firstSheet.createRow(h);
					//row.createCell((short) 0).setCellValue("Relatório");
					
					if(Nome != "" && Nome != null){
						
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 0).setCellValue(valor);
							
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 1).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							
							DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
							
							String dataS = formatter.format(valor);
							
							//Date data = (Date) formatter.parse(dataS);
							
							retorno.add(dataS);
							row.createCell((short) 2).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 3).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 4).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
	
							row.createCell((short) 5).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
			
							row.createCell((short) 6).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
		
							row.createCell((short) 7).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 8).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 9).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
			
							row.createCell((short) 10).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							
							row.createCell((short) 11).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 12).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 13).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 14).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 15).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 16).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							
							row.createCell((short) 17).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 18).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
			
		}else if(Tutoria != 0 && Ano != 0 && Periodo == 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getAnoEstudo().getIdanoEstudo() == Ano){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									
									DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
									
									String dataS = formatter.format(valor);
									
									//Date data = (Date) formatter.parse(dataS);
									
									retorno.add(dataS);
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
				}
				}
			}
			
		}else if(Tutoria != 0 && Periodo != 0 && Ano == 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getPeriodo().getIdperiodo() == Periodo){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									
									DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
									
									String dataS = formatter.format(valor);
									
									//Date data = (Date) formatter.parse(dataS);
									
									retorno.add(dataS);
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
					}
				}
			}
			
		}else if(Periodo != 0 && Ano != 0 && Tutoria == 0){
			alunos = new AlunoVariavelService().listaAnoEstudoPeriodoComgrupo(new AnoEstudoService().listarkey(Ano).get(0), new PeriodoService().listarkey(Periodo).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					h++;
					row = firstSheet.createRow(h);
					//row.createCell((short) 0).setCellValue("Relatório");
					
					if(Nome != "" && Nome != null){
						
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 0).setCellValue(valor);
							
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 1).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							
							DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
							
							String dataS = formatter.format(valor);
							
							//Date data = (Date) formatter.parse(dataS);
							
							retorno.add(dataS);
							row.createCell((short) 2).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 3).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							row.createCell((short) 4).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
	
							row.createCell((short) 5).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
			
							row.createCell((short) 6).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
		
							row.createCell((short) 7).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 8).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 9).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
			
							row.createCell((short) 10).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							
							row.createCell((short) 11).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 12).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 13).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 14).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 15).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 16).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
							
							row.createCell((short) 17).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null && valor != "-"){
							retorno.add(valor);
						
							row.createCell((short) 18).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
		}else if(Tutoria != 0 && Ano != 0 && Periodo != 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getAnoEstudo().getIdanoEstudo() == Ano && alunos.get(k).getPeriodo().getIdperiodo() == Periodo){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
									
									String dataS = formatter.format(valor);
									
									//Date data = (Date) formatter.parse(dataS);
									
									retorno.add(dataS);
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null && valor != "-"){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
					}
				}
			}
		}
		if(listRetorno.isEmpty()){
		   retorno.add("não há valores");
	       listRetorno.add(retorno);
		}
		
			workbook.write(fos);
			
			
			retorno.add("http://177.55.99.90/files/"+nomeArquivo);
			//retorno.add("http://172.16.31.178/"+nomeArquivo);
			listRetorno.add(retorno);
			return listRetorno.get(0);
			 
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Erro ao exportar arquivo");
				
			} finally {
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		
	}
	

	/**
	 * Lista Aluno Variavel especifico.
	 *
	 * @param id do  Aluno Variavel
	 * @return the Aluno Variavel
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public AlunoVariavel getEvento(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por id " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarkey(id);
		AlunoVariavel evento = resultado.get(0);

		return evento;
	}
	
	/**
	 * Lista Aluno variavel
	 * @param id
	 * @return Obj AlunoVariavel
	 */
	@Path("aluno/{id}")
	@GET
	@Produces("application/json")
	public AlunoVariavel getAluno(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por id " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaAluno(id);
		AlunoVariavel evento = resultado.get(0);

		return evento;
	}
	
	/**
	 * Lista grupo
	 * @param id
	 * @return list
	 */
	@Path("grupo/{id}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getGrupo(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por grupo " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaGrupo(id);
		///AlunoVariavel evento = resultado.get(0);

		return resultado;
	}
	
	
	/**
	 * Removes o aluno variavel.
	 *
	 * @param action  Metodo a ser executado create ou update
	 * @param id  id do aluno variavel
	 * @return true se for removido e false se der erro.
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeAlunoVariavel(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("AlunoVariavel  " + action);
		if ( action.equals("delete")) {
			List<AlunoVariavel> resultado;
			resultado = new AlunoVariavelService().listarkey(id);
			AlunoVariavel res = resultado.get(0);
			new AlunoVariavelService().deletarAlunoVariavel(res);
			return "true";
		} else {
			return "false";
		}
	}
	

	/**
	 * Serviço reponsavel por cadastra e atualizar dados do aluno variavel
	 *
	 * @param action Metodo a ser executado create ou update
	 * @param strid id para update
	 * @param inicio data inicio
	 * @param programaSocial faz parte de algum programa social
	 * @param anoEstudo  ano estudo
	 * @param anoLetivo  ano letivo
	 * @param aluno id do aluno aluno
	 * @param periodo  periodo
	 * @param grupo  grupo
	 * @param ativo  status do aluno 0 deletado 1 valido
	 * @return identificador de controle interno da aplicação
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			
			@FormParam("inicio") String inicio,
			@FormParam("programaSocial") String programaSocial,
			
			@FormParam("anoEstudo") String anoEstudo,
			@FormParam("anoLetivo") String anoLetivo,
			@FormParam("aluno") String aluno,
			@FormParam("periodo") String periodo,
			@FormParam("grupo") String grupo,
			@FormParam("ativo") int ativo
			
			
			) {
		AlunoVariavel objAlunoVariavel = new AlunoVariavel();
		logger.info("eventoAction ...");
		AlunoVariavel resultado;

		
		List<AnoEstudo> rsAnoEstudo;
		AnoEstudo objAnoEstudo;
		
		if(anoEstudo != null){
			rsAnoEstudo = new AnoEstudoService().listarkey(Integer.parseInt(anoEstudo));
			 objAnoEstudo= rsAnoEstudo.get(0);
		}else{
			 objAnoEstudo = new AnoEstudo();
		}
		
		List<AnoLetivo> rsAnoLetivo;
		AnoLetivo objAnoLetivo;
		
		if(anoLetivo != null){
			rsAnoLetivo = new AnoLetivoService().listarkey(Integer.parseInt(anoLetivo));
			 objAnoLetivo= rsAnoLetivo.get(0);
		}else{
			 objAnoLetivo = new AnoLetivo();
		}
		
		List<Periodo> rsPeriodo;
		Periodo objPeriodo;
		
		if(periodo != null){
			rsPeriodo = new PeriodoService().listarkey(Integer.parseInt(periodo));
			 objPeriodo= rsPeriodo.get(0);
		}else{
			 objPeriodo = new Periodo();
		}
		
		
		
		
		List<Aluno> rsAluno;
		Aluno objAluno;
		
		if(aluno != null){
			rsAluno = new AlunoService().listarkey(Integer.parseInt(aluno));
			 objAluno= rsAluno.get(0);
		}else{
			 objAluno = new Aluno();
		}
		
		
		
		
		Grupo objGrupo = new Grupo();
		
		if (!grupo.isEmpty()){
			List<Grupo> rsGrupo;
			rsGrupo = new GrupoService().listarkey(Integer.parseInt(grupo));
			 if(rsGrupo.isEmpty()){
				 objGrupo = null;
			 }else{
				 objGrupo= rsGrupo.get(0);
			 }
		} else {
			objGrupo = null;
		}
		
		
		StringUtil stringUtil = new StringUtil();

		
		if (action.equals("create")) {
			
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setInicio(stringUtil.converteStringData(inicio));
			
			objAlunoVariavel.setAnoEstudo(objAnoEstudo);
			objAlunoVariavel.setAnoLetivo(objAnoLetivo);
			objAlunoVariavel.setPeriodo(objPeriodo);
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setGrupo(objGrupo);
			objAlunoVariavel.setProgramaSocial(programaSocial);
			objAlunoVariavel.setAtivo(ativo);
			
			
			
			resultado = new AlunoVariavelService().criarAlunoVariavel(objAlunoVariavel);
			
		}  else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<AlunoVariavel> rsAlunoVariavel;
			rsAlunoVariavel= new AlunoVariavelService().listarkey(id);
			
			objAlunoVariavel= rsAlunoVariavel.get(0);
		
			if(objAluno != null){
				objAlunoVariavel.setAluno(objAluno);
			}
			
			if(inicio != null){
				objAlunoVariavel.setInicio(stringUtil.converteStringData(inicio));
			}
			
			if(objAnoEstudo != null){
				objAlunoVariavel.setAnoEstudo(objAnoEstudo);
			}
			
			if(objAnoLetivo != null){
				objAlunoVariavel.setAnoLetivo(objAnoLetivo);
			}
			if(objPeriodo != null){
				objAlunoVariavel.setPeriodo(objPeriodo);
			}
			if(objAluno != null){
				objAlunoVariavel.setAluno(objAluno);
			}
			if(objGrupo != null){
				objAlunoVariavel.setGrupo(objGrupo);
			}
			objAlunoVariavel.setAtivo(ativo);
			
			if(programaSocial != null){
				objAlunoVariavel.setProgramaSocial(programaSocial);
			}
			
			
			 resultado =  new AlunoVariavelService().atualizarAlunoVariavel(objAlunoVariavel);
			
		} else {
			return "0";
		}
	    return Integer.toString(resultado.getIdalunoVariavel());
	
		}
	
	
	/**
	 * Servico de divine grupo para uma lista de alunos
	 *
	 * @param lista de alunos separados por ";"
	 * @param id do grupo 
	 * @return retorna true se foi alterado o grupo com sucesso.
	 */
	@Path("alunoGrupo")
	@POST
	@Produces("text/plain")
	public String eventoAction2( @FormParam("alunos") String alunos,
			@FormParam("grupo") String grupo){
		
        logger.info("Buscando Grupo ...");
		
        Grupo objGrupo = new Grupo();
        
        if (!grupo.isEmpty()) {
        	List<Grupo> rsGrupo;
    		rsGrupo = new GrupoService().listarkey(Integer.parseInt(grupo));
    		objGrupo= rsGrupo.get(0);
    		
        }else {
        	objGrupo = null;
        }
        
        String [] arrayAlunos = alunos.split(";");
        List<AlunoVariavel> listAlunos = new ArrayList<AlunoVariavel>();
        for (String string : arrayAlunos) {
			listAlunos.add(new AlunoVariavelService().listarkey(Integer.parseInt(string)).get(0));
		}
		logger.info("QTD Alunos " +  arrayAlunos.length);
			
		List<AlunoVariavel> rsAluno;
		rsAluno = new AlunoVariavelService().listaGrupo(Integer.parseInt(grupo));
		for (AlunoVariavel alunoVariavel : rsAluno) {
			if (listAlunos.contains(alunoVariavel))
			{
				listAlunos.remove(alunoVariavel);
			}
			else
			{
				alunoVariavel.setGrupo(null);
				new AlunoVariavelService().atualizarAlunoVariavel(alunoVariavel);
			}
		}
		for (AlunoVariavel alunoVariavel : listAlunos) {
			new AlunoVariavelService().update(alunoVariavel.getIdalunoVariavel(), objGrupo.getIdgrupo());
		}
		
		/*if(rsAluno.size() < arrayAlunos.length){
			int diferenca =  rsAluno.size();
			   for(int k = diferenca; k <  arrayAlunos.length; k++){
				   int id = Integer.parseInt(arrayAlunos[k]);
				   new AlunoVariavelService().update(id, objGrupo.getIdgrupo());
			   }
		}
		
		
		for(int i = 0; i < rsAluno.size(); i++){
			
			logger.info("Buscando aluno ..." + arrayAlunos[i] );
			AlunoVariavel objAlunoVariavel;
			int id=Integer.parseInt(arrayAlunos[i]);
			//List<AlunoVariavel> rsAlunoVariavel;
			
			objAlunoVariavel= new AlunoVariavelService().getAluno(id); 
			
			if (objAlunoVariavel == null ) {
				
				logger.info("Não foi possivel completar a operacao buscando aluno ..." + arrayAlunos[i] );
				
			}else {
				
				if(Integer.parseInt(arrayAlunos[i]) != rsAluno.get(i).getIdalunoVariavel()){
					
					rsAluno.get(i).setGrupo(null);
					new AlunoVariavelService().atualizarAlunoVariavel(rsAluno.get(i));
					
					new AlunoVariavelService().update(Integer.parseInt(arrayAlunos[i]), objGrupo.getIdgrupo());

				}
			}
		}*/

		return "true";
	}
}