package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "planejamento_aula")
@XmlRootElement
public class PlanejamentoAula  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idplanejamento_aula")
	 private int Idplanejamento_aula;
	 
	 @Basic(optional = false)
	 @Column(name = "data")
	 @Temporal(TemporalType.DATE)
	 private Date data;
	 
	 @OneToOne
	 private PlanoAula planoAula;
	 
	 @OneToOne
	 private ObjetivoAula objetivoAula;

	 @OneToOne
	 private ProfessorFuncionario professor;
	 
	 @Column(name = "status")
	 private String status;

	public int getIdplanejamento_aula() {
		return Idplanejamento_aula;
	}

	public void setIdplanejamento_aula(int idplanejamento_aula) {
		Idplanejamento_aula = idplanejamento_aula;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public PlanoAula getPlanoAula() {
		return planoAula;
	}

	public void setPlanoAula(PlanoAula planoAula) {
		this.planoAula = planoAula;
	}

	public ObjetivoAula getObjetivoAula() {
		return objetivoAula;
	}

	public void setObjetivoAula(ObjetivoAula objetivoAula) {
		this.objetivoAula = objetivoAula;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 

}

