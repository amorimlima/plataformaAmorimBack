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
@Table(name = "licensa_professor")
@XmlRootElement
public class LicensaProfessor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idLicensaProfessor")
	private int idLicensaProfessor;
	
	@ManyToOne
	@JoinColumn(name = "professor", referencedColumnName = "idprofessor_funcionario")
	private ProfessorFuncionario professor;
	
	 @Column(name = "dataInicio")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date dataInicio;

	 @Column(name = "dataFim")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date dataFim;

	public int getIdLicensaProfessor() {
		return idLicensaProfessor;
	}

	public void setIdLicensaProfessor(int idLicensaProfessor) {
		this.idLicensaProfessor = idLicensaProfessor;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	 
}
