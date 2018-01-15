package br.com.muranodesign.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "aluno_conceito")
@XmlRootElement
public class AlunoConceito {
	private static final long serialVersionUID = 1L;
	
	public enum Conceito{
		S, NS, P
	}
	
	public enum Aprovacao{
		APROVADO, REPROVADO, FORMADO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idAlunoConceito")
	private Integer idAlunoConceito;
	
	@JoinColumn(name = "alunoVariavel", referencedColumnName = "idaluno_variavel")
	@ManyToOne
	private AlunoVariavel alunoVariavel;

	@Column(name = "aprovado")
	@Enumerated(EnumType.ORDINAL)
	private Aprovacao aprovado;

	@Column(name = "port_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito port_1sem;
	
	@Column(name = "port_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito port_2sem;
	
	@Column(name = "ingles_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito ingles_1sem;
	
	@Column(name = "ingles_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito ingles_2sem;
	
	@Column(name = "edFis_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito edFis_1sem;
	
	@Column(name = "edFis_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito edFis_2sem;
	
	@Column(name = "artes_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito artes_1sem;
	
	@Column(name = "artes_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito artes_2sem;
	
	@Column(name = "hist_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito hist_1sem;
	
	@Column(name = "hist_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito hist_2sem;
	
	@Column(name = "geo_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito geo_1sem;
	
	@Column(name = "geo_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito geo_2sem;
	
	@Column(name = "mat_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito mat_1sem;
	
	@Column(name = "mat_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito mat_2sem;
	
	@Column(name = "cien_1sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito cien_1sem;
	
	@Column(name = "cien_2sem")
	@Enumerated(EnumType.ORDINAL)
	private Conceito cien_2sem;
	
	public Integer getIdAlunoConceito() {
		return idAlunoConceito;
	}

	public void setIdAlunoConceito(Integer idAlunoConceito) {
		this.idAlunoConceito = idAlunoConceito;
	}

	public AlunoVariavel getAlunoVariavel() {
		return alunoVariavel;
	}

	public void setAlunoVariavel(AlunoVariavel alunoVariavel) {
		this.alunoVariavel = alunoVariavel;
	}

	public Aprovacao getAprovado() {
		return aprovado;
	}

	public void setAprovado(Aprovacao aprovado) {
		this.aprovado = aprovado;
	}
	
	public Conceito getPort_1sem() {
		return port_1sem;
	}

	public void setPort_1sem(Conceito port_1sem) {
		this.port_1sem = port_1sem;
	}

	public Conceito getPort_2sem() {
		return port_2sem;
	}

	public void setPort_2sem(Conceito port_2sem) {
		this.port_2sem = port_2sem;
	}

	public Conceito getIngles_1sem() {
		return ingles_1sem;
	}

	public void setIngles_1sem(Conceito ingles_1sem) {
		this.ingles_1sem = ingles_1sem;
	}

	public Conceito getIngles_2sem() {
		return ingles_2sem;
	}

	public void setIngles_2sem(Conceito ingles_2sem) {
		this.ingles_2sem = ingles_2sem;
	}

	public Conceito getEdFis_1sem() {
		return edFis_1sem;
	}

	public void setEdFis_1sem(Conceito edFis_1sem) {
		this.edFis_1sem = edFis_1sem;
	}

	public Conceito getEdFis_2sem() {
		return edFis_2sem;
	}

	public void setEdFis_2sem(Conceito edFis_2sem) {
		this.edFis_2sem = edFis_2sem;
	}

	public Conceito getArtes_1sem() {
		return artes_1sem;
	}

	public void setArtes_1sem(Conceito artes_1sem) {
		this.artes_1sem = artes_1sem;
	}

	public Conceito getArtes_2sem() {
		return artes_2sem;
	}

	public void setArtes_2sem(Conceito artes_2sem) {
		this.artes_2sem = artes_2sem;
	}

	public Conceito getHist_1sem() {
		return hist_1sem;
	}

	public void setHist_1sem(Conceito hist_1sem) {
		this.hist_1sem = hist_1sem;
	}

	public Conceito getHist_2sem() {
		return hist_2sem;
	}

	public void setHist_2sem(Conceito hist_2sem) {
		this.hist_2sem = hist_2sem;
	}

	public Conceito getGeo_1sem() {
		return geo_1sem;
	}

	public void setGeo_1sem(Conceito geo_1sem) {
		this.geo_1sem = geo_1sem;
	}

	public Conceito getGeo_2sem() {
		return geo_2sem;
	}

	public void setGeo_2sem(Conceito geo_2sem) {
		this.geo_2sem = geo_2sem;
	}

	public Conceito getMat_1sem() {
		return mat_1sem;
	}

	public void setMat_1sem(Conceito mat_1sem) {
		this.mat_1sem = mat_1sem;
	}

	public Conceito getMat_2sem() {
		return mat_2sem;
	}

	public void setMat_2sem(Conceito mat_2sem) {
		this.mat_2sem = mat_2sem;
	}

	public Conceito getCien_1sem() {
		return cien_1sem;
	}

	public void setCien_1sem(Conceito cien_1sem) {
		this.cien_1sem = cien_1sem;
	}

	public Conceito getCien_2sem() {
		return cien_2sem;
	}

	public void setCien_2sem(Conceito cien_2sem) {
		this.cien_2sem = cien_2sem;
	}
	
}
