package br.com.muranodesign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe model responsável por mapear os dados da tabela associativa plano_recurso
 * @author Pedro Henrique dos Santos
 * @see PlanoAula
 * @see RecursoAprendizagem
 */
@Entity
@Table(name = "plano_recurso")
@XmlRootElement
public class PlanoRecurso {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPlanoRecurso", nullable = false)
	private int idPlanoRecurso;
	
	@ManyToOne
	@JoinColumn(name = "planejamentoAula", referencedColumnName = "Idplanejamento_aula")
	private PlanejamentoAula planejamentoAula;
	
	@Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "arquivo")
    private String arquivo;
    
    @Column(name = "link")
    private String link;
	
    @JoinColumn(name = "tipo_recurso", referencedColumnName = "idtipo_recurso_aprendizagem")
    @ManyToOne
    private TipoRecursoAprendizagem tipoRecurso;
    
    public int getIdPlanoRecurso() {
		return idPlanoRecurso;
	}

	public void setIdPlanoRecurso(int idPlanoRecurso) {
		this.idPlanoRecurso = idPlanoRecurso;
	}

	public PlanejamentoAula getPlanejamentoAula() {
		return planejamentoAula;
	}

	public void setPlanejamentoAula(PlanejamentoAula planejamentoAula) {
		this.planejamentoAula = planejamentoAula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeRecurso) {
		this.nome = nomeRecurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricaoRecurso) {
		this.descricao = descricaoRecurso;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public TipoRecursoAprendizagem getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(
			TipoRecursoAprendizagem tipoRecursoAprendizagem) {
		this.tipoRecurso = tipoRecursoAprendizagem;
	}

	public int getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(int visibilidade) {
		this.visibilidade = visibilidade;
	}

	@Column(name = "visibilidade")
    private int visibilidade;
	
}
