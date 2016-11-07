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
@Table(name = "roteiro_aula")
@XmlRootElement
public class RoteiroAula implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idroteiro_aula")
	 private int Idroteiro_aula;
	 
	 @Column(name = "status")
	 private int status;
	 
	 @Column(name = "roteiro")
	 private String roteiro;
	 
	 @Column(name = "Descricao")
	 private String Descricao;
/*
	 @OneToOne
	 private ProfessorFuncionario professor;*/
	 
	 @OneToOne
	 private OficinaProfessor Oficinaprofessor;
	 
	public int getIdroteiro_aula() {
		return Idroteiro_aula;
	}

	public void setIdroteiro_aula(int idroteiro_aula) {
		Idroteiro_aula = idroteiro_aula;
	}

	public String getRoteiro() {
		return roteiro;
	}

	public void setRoteiro(String roteiro) {
		this.roteiro = roteiro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
/*
	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}
	 */

	public OficinaProfessor getOficinaprofessor() {
		return Oficinaprofessor;
	}

	public void setOficinaprofessor(OficinaProfessor oficinaprofessor) {
		Oficinaprofessor = oficinaprofessor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	 
	 
}
