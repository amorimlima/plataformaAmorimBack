package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "jeiff_pea")
@XmlRootElement
public class JeiffPea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idJeiffPea")
	private int idJeiffPea;
	
	@ManyToOne
	@JoinColumn(name = "professor", referencedColumnName = "idprofessor_funcionario")
	private ProfessorFuncionario professorFuncionario;

	@Column(name = "ata")
	private String ata;
	
	@ManyToOne
	@JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
	private Periodo periodo;
	
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	 
	@Column(name = "arquivo")
	private String arquivo;
	
	@Column(name = "descricao")
	private String descricao;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdJeiffPea() {
		return idJeiffPea;
	}

	public void setIdJeiffPea(int idJeiffPea) {
		this.idJeiffPea = idJeiffPea;
	}
	
	public ProfessorFuncionario getProfessorFuncionario() {
		return professorFuncionario;
	}

	public void setProfessorFuncionario(ProfessorFuncionario professorFuncionario) {
		this.professorFuncionario = professorFuncionario;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Date getData_reuniao() {
		return data;
	}

	public void setData_reuniao(Date data_reuniao) {
		this.data = data_reuniao;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}	 
	
}
