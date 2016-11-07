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
@Table(name = "oficina_professor")
@XmlRootElement
public class OficinaProfessor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idoficina_professor")
	 private int Idoficina_professor;
	 
	 @OneToOne
	 private Oficina oficina;
	 
	 @OneToOne
	 private ProfessorFuncionario professor;
	 
	 

	public int getIdoficina_professor() {
		return Idoficina_professor;
	}

	public void setIdoficina_professor(int idoficina_professor) {
		Idoficina_professor = idoficina_professor;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}
	 
	 

}
