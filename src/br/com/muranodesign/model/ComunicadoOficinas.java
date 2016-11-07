package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "comunicado_oficinas")
@XmlRootElement
public class ComunicadoOficinas implements Serializable{

	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idComunicado")
	 private int idComunicado;
	 
	 @OneToOne
	 private ProfessorFuncionario professor;
	 
	 @Column(name = "comunicado")
	 private String comunicado;
	 

	public int getIdComunicado() {
		return idComunicado;
	}

	public void setIdComunicado(int idComunicado) {
		this.idComunicado = idComunicado;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}

	public String getComunicado() {
		return comunicado;
	}

	public void setComunicado(String comunicado) {
		this.comunicado = comunicado;
	}
	 
}
