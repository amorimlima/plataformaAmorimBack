/**
 *   Este codigo Ã© software livre vocÃª e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.dao;

import br.com.muranodesign.dao.impl.AgendamentoSalaDAOImpl;
import br.com.muranodesign.dao.impl.AgrupamentoDAOImpl;
import br.com.muranodesign.dao.impl.AlunoAgrupamentoDAOImpl;
import br.com.muranodesign.dao.impl.AlunoConceitoDAOImpl;
import br.com.muranodesign.dao.impl.AlunoDAOImpl;
import br.com.muranodesign.dao.impl.AlunoVariavelDAOImpl;
import br.com.muranodesign.dao.impl.AnoEstudoDAOImpl;
import br.com.muranodesign.dao.impl.AnoLetivoDAOImpl;
import br.com.muranodesign.dao.impl.AprovacaoDAOImpl;
import br.com.muranodesign.dao.impl.AtividadeDAOImpl;
import br.com.muranodesign.dao.impl.AtribuicaoRoteiroExtraDAOImpl;
import br.com.muranodesign.dao.impl.AvaliacaoProducaoAlunoDAOImpl;
import br.com.muranodesign.dao.impl.BlogDAOImpl;
import br.com.muranodesign.dao.impl.CalendarioDAOImpl;
import br.com.muranodesign.dao.impl.CalendarioEventosDAOImpl;
import br.com.muranodesign.dao.impl.CategoriaProducaoAlunoDAOImpl;
import br.com.muranodesign.dao.impl.ChamadaDAOImpl;
import br.com.muranodesign.dao.impl.CicloAnoEstudoDAOImpl;
import br.com.muranodesign.dao.impl.CicloDAOImpl;
import br.com.muranodesign.dao.impl.CompensacaoDAOImpl;
import br.com.muranodesign.dao.impl.ComunicadoOficinasDAOImpl;
import br.com.muranodesign.dao.impl.ConceitoDAOImpl;
import br.com.muranodesign.dao.impl.CoresDAOImpl;
import br.com.muranodesign.dao.impl.EmprestimoLivroDAOImpl;
import br.com.muranodesign.dao.impl.FichaInscricaoDAOImpl;
import br.com.muranodesign.dao.impl.ForumGeralQuestaoDAOImpl;
import br.com.muranodesign.dao.impl.ForumGeralRespostaDAOImpl;
import br.com.muranodesign.dao.impl.ForumQuestaoDAOImpl;
import br.com.muranodesign.dao.impl.ForumRespostaDAOImpl;
import br.com.muranodesign.dao.impl.GrupoDAOImpl;
import br.com.muranodesign.dao.impl.HistoricoDAOImpl;
import br.com.muranodesign.dao.impl.HistoricoEventosDAOImpl;
import br.com.muranodesign.dao.impl.ImagensDAOImpl;
import br.com.muranodesign.dao.impl.JeiffPeaDAOImpl;
import br.com.muranodesign.dao.impl.JornadaProfessorDAOImpl;
import br.com.muranodesign.dao.impl.LicensaProfessorDAOImpl;
import br.com.muranodesign.dao.impl.MateriaDAOImpl;
import br.com.muranodesign.dao.impl.MensagensDAOImpl;
import br.com.muranodesign.dao.impl.MenuDAOImpl;
import br.com.muranodesign.dao.impl.MenuPerfilDAOImpl;
import br.com.muranodesign.dao.impl.MuralAlunoDAOImpl;
import br.com.muranodesign.dao.impl.MuralCoordenacaoDAOImpl;
import br.com.muranodesign.dao.impl.MuralDAOImpl;
import br.com.muranodesign.dao.impl.NativeQueryDAOImpl;
import br.com.muranodesign.dao.impl.ObjetivoDAOImpl;
import br.com.muranodesign.dao.impl.OficinaDAOImpl;
import br.com.muranodesign.dao.impl.OficinaProfessorDAOImpl;
import br.com.muranodesign.dao.impl.PendenciasProducaoAlunoDAOImpl;
import br.com.muranodesign.dao.impl.PerfilDAOImpl;
import br.com.muranodesign.dao.impl.PeriodoDAOImpl;
import br.com.muranodesign.dao.impl.PlanejamentoAulaDAOImpl;
import br.com.muranodesign.dao.impl.PlanejamentoRoteiroDAOImpl;
import br.com.muranodesign.dao.impl.PlanoAgrupamentoDAOImpl;
import br.com.muranodesign.dao.impl.PlanoEstudoDAOImpl;
import br.com.muranodesign.dao.impl.PlanoRecursoDAOImpl;
import br.com.muranodesign.dao.impl.PresencaProfessorDAOImpl;
import br.com.muranodesign.dao.impl.ProducaoAlunoDAOImpl;
import br.com.muranodesign.dao.impl.ProfessorFuncionarioDAOImpl;
import br.com.muranodesign.dao.impl.ProfessorFuncionarioVariavelDAOImpl;
import br.com.muranodesign.dao.impl.RecursoAprendizagemDAOImpl;
import br.com.muranodesign.dao.impl.RegistroDiarioDAOImpl;
import br.com.muranodesign.dao.impl.RelatorioTutoriaDAOImpl;
import br.com.muranodesign.dao.impl.RoteiroDAOImpl;
import br.com.muranodesign.dao.impl.RotinaDAOImpl;
import br.com.muranodesign.dao.impl.SODAOImpl;
import br.com.muranodesign.dao.impl.SalasDAOImpl;
import br.com.muranodesign.dao.impl.SemanaDAOImpl;
import br.com.muranodesign.dao.impl.SessaoForumGeralDAOImpl;
import br.com.muranodesign.dao.impl.TemplateDAOImpl;
import br.com.muranodesign.dao.impl.TipoEventoDAOImpl;
import br.com.muranodesign.dao.impl.TipoOficinaDAOImpl;
import br.com.muranodesign.dao.impl.TipoProducaoAlunoDAOImpl;
import br.com.muranodesign.dao.impl.TipoRecursoAprendizagemDAOImpl;
import br.com.muranodesign.dao.impl.TutoriaDAOImpl;
import br.com.muranodesign.dao.impl.UsuarioDAOImpl;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.hibernate.impl.PersistenceContext;





/**
/**
 * Ponto central para criacaoo de DAOs e contextos de persistencia. <br />
 * Idealmente, esta e a unica classe no sistema que devera conhecer como obter
 * implementacoes concretas destes objetos.
 * 
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicaÃ§Ã£o
 */

public class DAOFactory {

	/**
	 * Retorna uma nova instancia do contexto de persistencia.
	 *
	 * @return the persistence context
	 */
	public static PersistenceContext createPersistenceContext() {
		return new HibernatePersistenceContext();
	}

	/**
	 * Gets the aluno dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the aluno dao
	 */
	public static AlunoDAO getAlunoDAO(PersistenceContext persistenceContext) {
		return new AlunoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the aluno variavel dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the aluno variavel dao
	 */
	public static AlunoVariavelDAO getAlunoVariavelDAO(
			PersistenceContext persistenceContext) {
		return new AlunoVariavelDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the ano estudo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the ano estudo dao
	 */
	public static AnoEstudoDAO getAnoEstudoDAO(
			PersistenceContext persistenceContext) {
		return new AnoEstudoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the ano letivo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the ano letivo dao
	 */
	public static AnoLetivoDAO getAnoLetivoDAO(
			PersistenceContext persistenceContext) {
		return new AnoLetivoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the atividade dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the atividade dao
	 */
	public static AtividadeDAO getAtividadeDAO(
			PersistenceContext persistenceContext) {
		return new AtividadeDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the Atribuicao Roteiro
	 * @param persistenceContext
	 * @return
	 */
	public static AtribuicaoRoteiroExtraDAO getAtribuicaoRoteiroExtraDAO(
			PersistenceContext persistenceContext){
		return new AtribuicaoRoteiroExtraDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	

	/**
	 * Gets the avaliacao producao aluno dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the avaliacao producao aluno dao
	 */
	public static AvaliacaoProducaoAlunoDAO getAvaliacaoProducaoAlunoDAO(
			PersistenceContext persistenceContext) {
		return new AvaliacaoProducaoAlunoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the categoria producao aluno dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the categoria producao aluno dao
	 */
	public static CategoriaProducaoAlunoDAO getCategoriaProducaoAlunoDAO(
			PersistenceContext persistenceContext) {
		return new CategoriaProducaoAlunoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the chamada dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the chamada dao
	 */
	public static ChamadaDAO getChamadaDAO(PersistenceContext persistenceContext) {
		return new ChamadaDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	public static CoresDAO getCorDAO(PersistenceContext persistenceContext){
		return new CoresDAOImpl((HibernatePersistenceContext) persistenceContext);
	}


/*
	public static EventosDAO getEventosDAO(PersistenceContext persistenceContext) {
		return new EventosDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
*/
	/**
 * Gets the mensagens dao.
 *
 * @param persistenceContext the persistence context
 * @return the mensagens dao
 */
public static MensagensDAO getMensagensDAO(
			PersistenceContext persistenceContext) {
		return new MensagensDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the planejamento roteiro dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the planejamento roteiro dao
	 */
	public static PlanejamentoRoteiroDAO getPlanejamentoRoteiroDAO(
			PersistenceContext persistenceContext) {
		return new PlanejamentoRoteiroDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the plano estudo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the plano estudo dao
	 */
	public static PlanoEstudoDAO getPlanoEstudoDAO(
			PersistenceContext persistenceContext) {
		return new PlanoEstudoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the producao aluno dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the producao aluno dao
	 */
	public static ProducaoAlunoDAO getProducaoAlunoDAO(
			PersistenceContext persistenceContext) {
		return new ProducaoAlunoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the professor funcionario dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the professor funcionario dao
	 */
	public static ProfessorFuncionarioDAO getProfessorFuncionarioDAO(
			PersistenceContext persistenceContext) {
		return new ProfessorFuncionarioDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the professor funcionario variavel dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the professor funcionario variavel dao
	 */
	public static ProfessorFuncionarioVariavelDAO getProfessorFuncionarioVariavelDAO(
			PersistenceContext persistenceContext) {
		return new ProfessorFuncionarioVariavelDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}



	/**
	 * Gets the relatorio tutoria dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the relatorio tutoria dao
	 */
	public static RelatorioTutoriaDAO getRelatorioTutoriaDAO(
			PersistenceContext persistenceContext) {
		return new RelatorioTutoriaDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the roteiro dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the roteiro dao
	 */
	public static RoteiroDAO getRoteiroDAO(PersistenceContext persistenceContext) {
		return new RoteiroDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * @param persistenceContext
	 * @return
	 */
	public static CicloDAO getCicloDAO(PersistenceContext persistenceContext) {
		return new CicloDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static MenuDAO getMenuDAO(PersistenceContext persistenceContext) {
		return new MenuDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static MenuPerfilDAO getMenuPerfilDAO(PersistenceContext persistenceContext) {
		return new MenuPerfilDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static BlogDAO getBlogDAO(PersistenceContext persistenceContext) {
		return new BlogDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static CicloAnoEstudoDAO getCicloAnoEstudoDAO(PersistenceContext persistenceContext) {
		return new CicloAnoEstudoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	

	/**
	 * Gets the objetivo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the objetivo dao
	 */
	public static ObjetivoDAO getObjetivoDAO(PersistenceContext persistenceContext) {
		return new ObjetivoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	
	/**
	 * Gets the registro diario dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the registro diario dao
	 */
	public static RegistroDiarioDAO getRegistroDiarioDAO(PersistenceContext persistenceContext) {
		return new RegistroDiarioDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static MuralDAO getMuralDAO(PersistenceContext persistenceContext){
		return new MuralDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static PlanejamentoAulaDAO getPlanejamentoAulaDAO(PersistenceContext persistenceContext){
		return new PlanejamentoAulaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the tipo evento dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the tipo evento dao
	 */
	public static TipoEventoDAO getTipoEventoDAO(
			PersistenceContext persistenceContext) {
		return new TipoEventoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the tipo producao aluno dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the tipo producao aluno dao
	 */
	public static TipoProducaoAlunoDAO getTipoProducaoAlunoDAO(
			PersistenceContext persistenceContext) {
		return new TipoProducaoAlunoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the tutoria dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the tutoria dao
	 */
	public static TutoriaDAO getTutoriaDAO(PersistenceContext persistenceContext) {
		return new TutoriaDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}

	/**
	 * Gets the usuario dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the usuario dao
	 */
	public static UsuarioDAO getUsuarioDAO(PersistenceContext persistenceContext) {
		return new UsuarioDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}


	/**
	 * Gets the grupo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the grupo dao
	 */
	public static GrupoDAO getGrupoDAO(PersistenceContext persistenceContext) {
		return new GrupoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * Gets the materia dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the materia dao
	 */
	public static MateriaDAO getMateriaDAO(PersistenceContext persistenceContext) {
		return new MateriaDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the sessao forum geral dao
	 * @param persistenceContext
	 * @return
	 */
	public static SessaoForumGeralDAO getSessaoForumGeralDAO(PersistenceContext persistenceContext){
		return new SessaoForumGeralDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the forum geral questao dao
	 * @param persistenceContext
	 * @return
	 */
	public static ForumGeralQuestaoDAO getForumGeralQuestaoDAO(PersistenceContext persistenceContext){
		return new ForumGeralQuestaoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the forum geral respotas 
	 * @param persistenceContext
	 * @return
	 */
	public static ForumGeralRespostaDAO getForumGeralRespostaDAO(PersistenceContext persistenceContext){
		return new ForumGeralRespostaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}

	
	/**
	 * Gets the perfil dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the perfil dao
	 */
	public static PerfilDAO getPerfilDAO(PersistenceContext persistenceContext) {
		return new PerfilDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * Gets the presenca professor dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the presenca professor dao
	 */
	public static PresencaProfessorDAO getPresencaProfessorDAO(PersistenceContext persistenceContext) {
		return new PresencaProfessorDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * Gets the recurso aprendizagem dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the recurso aprendizagem dao
	 */
	public static RecursoAprendizagemDAO getRecursoAprendizagemDAO(PersistenceContext persistenceContext) {
		return new RecursoAprendizagemDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the tipo recurso aprendizagem dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the tipo recurso aprendizagem dao
	 */
	public static TipoRecursoAprendizagemDAO getTipoRecursoAprendizagemDAO(PersistenceContext persistenceContext) {
		return new TipoRecursoAprendizagemDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the periodo dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the periodo dao
	 */
	public static PeriodoDAO getPeriodoDAO(PersistenceContext persistenceContext) {
		return new PeriodoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the calendario dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the calendario dao
	 */
	public static CalendarioDAO getCalendarioDAO(PersistenceContext persistenceContext) {
		return new CalendarioDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the forum resposta dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the forum resposta dao
	 */
	public static ForumRespostaDAO getForumRespostaDAO(PersistenceContext persistenceContext) {
		return new ForumRespostaDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the forum questao dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the forum questao dao
	 */
	public static ForumQuestaoDAO getForumQuestaoDAO(PersistenceContext persistenceContext) {
		return new ForumQuestaoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * Gets the calendario eventos dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the calendario eventos dao
	 */
	public static CalendarioEventosDAO getCalendarioEventosDAO(PersistenceContext persistenceContext) {
		return new CalendarioEventosDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	

	// / NATIVE QUERY
	/**
	 * Gets the native query dao.
	 *
	 * @param persistenceContext the persistence context
	 * @return the native query dao
	 */
	public static NativeQueryDAO getNativeQueryDAO(
			PersistenceContext persistenceContext) {
		return new NativeQueryDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static HistoricoDAO getNHistoricoDAO(
			PersistenceContext persistenceContext){
		return new HistoricoDAOImpl(
				(HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static SODAO getSodao(PersistenceContext persistenceContext){
		return new SODAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static AgendamentoSalaDAO getAgendamentoSalaDAO(PersistenceContext persistenceContext){
		return new AgendamentoSalaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static AgrupamentoDAO getAgrupamentoDAO(PersistenceContext persistenceContext){
		return new AgrupamentoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static AlunoAgrupamentoDAO getAlunoAgrupamentoDAO(PersistenceContext persistenceContext){
		return new AlunoAgrupamentoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static OficinaDAO getOficinaDAO(PersistenceContext persistenceContext){
		return new OficinaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	//Alteração que pode mudar
	public static JornadaProfessorDAO getJornadaProfessorDAO(PersistenceContext persistenceContext){
		return new JornadaProfessorDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	//Alteração que pode mudar
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static OficinaProfessorDAO getOficinaProfessorDAO(PersistenceContext persistenceContext){
		return new OficinaProfessorDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static RotinaDAO getRotinaDAO(PersistenceContext persistenceContext){
		return new RotinaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static SalasDAO getSalasDAO(PersistenceContext persistenceContext){
		return new SalasDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static SemanaDAO getSemanaDAO(PersistenceContext persistenceContext){
		return new SemanaDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static HistoricoEventosDAO getHistoricoEventosDAO(PersistenceContext persistenceContext){
		return new HistoricoEventosDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static ComunicadoOficinasDAO getComunicadoOficinasDAO(PersistenceContext persistenceContext){
		return new ComunicadoOficinasDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static ImagensDAO getImagensDAO(PersistenceContext persistenceContext){
		return new ImagensDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static TemplateDAO getTemplateDAO(PersistenceContext persistenceContext){
		return new TemplateDAOImpl((HibernatePersistenceContext) persistenceContext);
	}
	
	/**
	 * 
	 * @param persistenceContext
	 * @return
	 */
	public static FichaInscricaoDAO getFichaInscricao(PersistenceContext persistenceContext){
		return new FichaInscricaoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}

	public static MuralCoordenacaoDAO getMuralCoordenacaoDAO(PersistenceContext persistenceContext) {
		return new MuralCoordenacaoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}

	public static PendenciasProducaoAlunoDAO getPendenciasProducaoAlunoDAO(PersistenceContext persistenceContext) {
		return new PendenciasProducaoAlunoDAOImpl((HibernatePersistenceContext) persistenceContext);
	}

	public static MuralAlunoDAO geMuralAlunoDAO(PersistenceContext pc) {
		return new MuralAlunoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static RelatorioAlunoDAO getRelatorioAlunoDAO(PersistenceContext pc) {
		return new RelatorioAlunoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static TipoOficinaDAO getTipoOficinaDAO(PersistenceContext pc) {
		return new TipoOficinaDAOImpl((HibernatePersistenceContext) pc);
	}

	public static JeiffPeaDAO getJeiffPeaDAO(PersistenceContext pc) {
		return new JeiffPeaDAOImpl((HibernatePersistenceContext) pc);
	}
	
	public static PlanoRecursoDAO getPlanoRecursoDAO(PersistenceContext pc){
		return new PlanoRecursoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static PlanoAgrupamentoDAO getPlanoAgrupamentoDAO(PersistenceContext pc) {
		return new PlanoAgrupamentoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static CompensacaoDAO getCompensacaoDAO(PersistenceContext pc) {
		return new CompensacaoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static LicensaProfessorDAO getLicensaProfessorDAO(PersistenceContext pc) {
		return new LicensaProfessorDAOImpl((HibernatePersistenceContext) pc);
	}

	public static EmprestimoLivroDAO getEmprestimoLivroDAO(PersistenceContext pc) {
		return new EmprestimoLivroDAOImpl((HibernatePersistenceContext) pc);
	}

	public static ConceitoDAO getConceitoDAO(PersistenceContext pc) {
		return new ConceitoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static AprovacaoDAO getAprovacaoDAO(PersistenceContext pc) {
		return new AprovacaoDAOImpl((HibernatePersistenceContext) pc);
	}

	public static AlunoConceitoDAO getAlunoConceitoDAO(PersistenceContext pc) {
		return new AlunoConceitoDAOImpl((HibernatePersistenceContext) pc);
	}
	

}
