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


//Alteração que pode mudar
@Entity
@Table(name = "jornada_professor")
@XmlRootElement
public class JornadaProfessor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Idjornada_professor")
	private int Idjornada_professor;
	 
	@Column(name = "horario")
	private int horario;
	
	@Column(name = "extra")
	private int extra;
	
	@OneToOne
	private Semana dia; 
	
	@OneToOne
	private OficinaProfessor oficina_professor;
	
	@OneToOne
	private ProfessorFuncionario professor;
	
	
	@Column(name = "ocupado")
	private int ocupado;

	public int getIdjornada_professor() {
		return Idjornada_professor;
	}

	public void setIdjornada_professor(int idjornada_professor) {
		Idjornada_professor = idjornada_professor;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public int getExtra() {
		return extra;
	}

	public void setExtra(int extra) {
		this.extra = extra;
	}

	public Semana getDia() {
		return dia;
	}

	public void setDia(Semana dia) {
		this.dia = dia;
	}

	public OficinaProfessor getOficina_professor() {
		return oficina_professor;
	}

	public void setOficina_professor(OficinaProfessor oficina_professor) {
		this.oficina_professor = oficina_professor;
	}

	public int getOcupado() {
		return ocupado;
	}

	public void setOcupado(int ocupado) {
		this.ocupado = ocupado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}
}
